package com.bi.recordmanagement.services;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bi.recordmanagement.services.ISearchable;
import com.bi.recordmanagement.models.Searchable;
import com.bi.recordmanagement.utils.GMConstant;
import com.bi.recordmanagement.utils.PaginationConfig;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanOperation;
import com.querydsl.core.types.dsl.Expressions;

@Service
public abstract class SearchServiceImpl<T, R extends QuerydslPredicateExecutor<T> &  PagingAndSortingRepository<T, ?>> implements SearchService<T, R>{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Autowired
	R repo;
 	
	@Override
	public Iterable<T> search(String searchText, Class<T> clazz, PaginationConfig paginationConfig) {
		return search(searchText, clazz, paginationConfig, null, null);
	}

	@Override
	public Iterable<T> search(String searchText, Class<T> clazz[], PaginationConfig paginationConfig) {
		return search(searchText, clazz, paginationConfig, null, null);
	}
	
	public static Direction getSortDirection(String ascDescStr) {
		if (ascDescStr.equalsIgnoreCase(GMConstant.ASC)) {
			return Direction.ASC;
		} else if (ascDescStr.equalsIgnoreCase(GMConstant.DESC)) {
			return Direction.DESC;
		} else {
			return Direction.ASC;
		}
	}
	
	/**
	   * Get all fields of a class.
	   * 
	   * @param clazz The class.
	   * @return All fields of a class.
	   */
	  public static Collection<Field> getFields(Class<?> clazz) {
//	    if (log.isDebugEnabled()) {
	  //    log.debug("getFields(Class<?>) - start");
	    //}

	    Map<String, Field> fields = new HashMap<String, Field>();
	    while (clazz != null) {
	      for (Field field : clazz.getDeclaredFields()) {
	        if (!fields.containsKey(field.getName())) {
	          fields.put(field.getName(), field);
	        }
	      }

	      clazz = clazz.getSuperclass();
	    }

	    Collection<Field> returnCollection = fields.values();
	  //  if (log.isDebugEnabled()) {
      //  log.debug("getFields(Class<?>) - end");
	  //  }
	    return returnCollection;
	  }

	/**
	 * just support for class[]
 	 * @param classes
	 * @return
	 */
	public static Collection<Field> getFields(Class<?> classes[]) {
//	    if (log.isDebugEnabled()) {
		//    log.debug("getFields(Class<?>) - start");
		//}

		Map<String, Field> fields = new HashMap<String, Field>();
		for(Class clazz:classes) {
			while (clazz != null) {
				for (Field field : clazz.getDeclaredFields()) {
					if (!fields.containsKey(field.getName())) {
						fields.put(field.getName(), field);
					}
				}

				clazz = clazz.getSuperclass();
			}
		}

		Collection<Field> returnCollection = fields.values();
		//  if (log.isDebugEnabled()) {
		//  log.debug("getFields(Class<?>) - end");
		//  }
		return returnCollection;
	}

	@Override
	public List<T> search(String searchText, Class<T> clazz, PaginationConfig paginationConfig,List<String> filteredNode,BooleanOperation booleanEx) {
		PageRequest pageRequest = null;
		if(paginationConfig.getPageNumber() != null && paginationConfig.getPageSize() != null && paginationConfig.getSortDirection() == null && paginationConfig.getSortOn() == null) {
			pageRequest = PageRequest.of(paginationConfig.getPageNumber(), paginationConfig.getPageSize());
		} else if(paginationConfig.getPageNumber() != null && paginationConfig.getPageSize() != null && paginationConfig.getSortDirection() != null && paginationConfig.getSortOn() != null) {
			pageRequest = PageRequest.of(paginationConfig.getPageNumber(), paginationConfig.getPageSize(), getSortDirection(paginationConfig.getSortDirection()), paginationConfig.getSortOn());
		}
		Path<T> path = null;
		List<String> traverseList = new ArrayList<>();
		BooleanOperation ops = null;
	    ops = traversNode(ops,path,clazz,searchText,filteredNode,traverseList);
		
	 	if(booleanEx != null) {

			if(ops != null) {
			    ops = Expressions.predicate(Ops.AND, ops, booleanEx);
			} else {
				ops = booleanEx;	
			}
		}
			if(ops != null) {
			
				Iterable<T> iter = repo.findAll(ops, pageRequest);
				//TODO: Fix this. Currently lazy initialization is not loaded. Since the entity is generic we aren't aware what
				//properties should be dynamically eager loaded.
				List<T> result = new ArrayList<>();
				for (T t : iter) {
					result.add(t);
				}
				return result;
			} else {
			
				Iterable<T> iter = repo.findAll(pageRequest);
				List<T> result = new ArrayList<>();
				for (T t : iter) {
					result.add(t);
				}
				return result;
			}
	}

	@Override
	public List<T> search(String searchText, Class<T> classes[], PaginationConfig paginationConfig,List<String> filteredNode,BooleanOperation booleanEx) {
		PageRequest pageRequest = null;
		if(paginationConfig.getPageNumber() != null && paginationConfig.getPageSize() != null && paginationConfig.getSortDirection() == null && paginationConfig.getSortOn() == null) {
			pageRequest = PageRequest.of(paginationConfig.getPageNumber(), paginationConfig.getPageSize());
		} else if(paginationConfig.getPageNumber() != null && paginationConfig.getPageSize() != null && paginationConfig.getSortDirection() != null && paginationConfig.getSortOn() != null) {
			pageRequest = PageRequest.of(paginationConfig.getPageNumber(), paginationConfig.getPageSize(), getSortDirection(paginationConfig.getSortDirection()), paginationConfig.getSortOn());
		}
		Path<T> path = null;
		List<String> traverseList = new ArrayList<>();
		BooleanOperation ops = null;
		ops = traversNode(ops,path,classes,searchText,filteredNode,traverseList);

		if(booleanEx != null) {

			if(ops != null) {
				ops = Expressions.predicate(Ops.AND, ops, booleanEx);
			} else {
				ops = booleanEx;
			}
		}
		if(ops != null) {

			Iterable<T> iter = repo.findAll(ops, pageRequest);
			//TODO: Fix this. Currently lazy initialization is not loaded. Since the entity is generic we aren't aware what
			//properties should be dynamically eager loaded.
			List<T> result = new ArrayList<>();
			for (T t : iter) {
				result.add(t);
			}
			return result;
		} else {

			Iterable<T> iter = repo.findAll(pageRequest);
			List<T> result = new ArrayList<>();
			for (T t : iter) {
				result.add(t);
			}
			return result;
		}
	}
	
	
	private BooleanOperation traversNode(BooleanOperation ops,Path<T> path , Class<T> clazz,String searchText,List<String> filteredNode,List<String> traverseList) {
		Expression<String> constant = Expressions.constant("%"+searchText+"%");

		if(path != null) {
			path = Expressions.path(clazz, path,StringUtils.uncapitalize(clazz.getSimpleName()));	
		} else {
		    path = Expressions.path(clazz,StringUtils.uncapitalize(clazz.getSimpleName()));	
		}
		try {

			Collection<Field> fields = getFields(clazz);
			for (Field field : fields) {
				if(field.isAnnotationPresent(Searchable.class) && isFieldAvaliableForSerch(field,clazz,filteredNode,traverseList)) {
					BooleanOperation op;
					traverseList.add(clazz.getSimpleName()+"."+field.getName());
					if(!field.getType().isAssignableFrom(String.class) && !field.getType().isAssignableFrom(Integer.class)
							&& !field.getType().isAssignableFrom(Long.class) && !field.getType().isAssignableFrom(Double.class)  && !field.getType().isAssignableFrom(Float.class)) {
						
						ops = traversNode(ops, path,  (Class<T>) field.getType(), searchText, filteredNode, traverseList);	
					} 
					else if(searchText != null && !"".equals(searchText)) {

						op = Expressions.predicate(Ops.LIKE, Expressions.path(field.getType(), path, field.getName()), constant);
						if(ops == null) {
							ops = op; 
						} else {
							ops = Expressions.predicate(Ops.OR, ops, op);
						}
					}
				}
			}
		}
	 catch (Exception e) {
		logger.error("error searching data for model {}", clazz, e);
	}
		return ops;
	}

	private BooleanOperation traversNode(BooleanOperation ops,Path<T> path , Class<T> classes[],String searchText,List<String> filteredNode,List<String> traverseList) {
		Expression<String> constant = Expressions.constant("%" + searchText + "%");

		for (Class clazz : classes) {
			if (path != null) {
				path = Expressions.path(clazz, path, StringUtils.uncapitalize(clazz.getSimpleName()));
			} else {
				path = Expressions.path(clazz, StringUtils.uncapitalize(clazz.getSimpleName()));
			}
		try {

			Collection<Field> fields = getFields(clazz);
			for (Field field : fields) {
				if (field.isAnnotationPresent(ISearchable.class) && isFieldAvaliableForSerch(field, clazz, filteredNode, traverseList)) {
					BooleanOperation op;
					traverseList.add(clazz.getSimpleName() + "." + field.getName());
					if (!field.getType().isAssignableFrom(String.class) && !field.getType().isAssignableFrom(Integer.class)
							&& !field.getType().isAssignableFrom(Long.class) && !field.getType().isAssignableFrom(Double.class) && !field.getType().isAssignableFrom(Float.class)) {

						ops = traversNode(ops, path, (Class<T>) field.getType(), searchText, filteredNode, traverseList);
					} else if (searchText != null && !"".equals(searchText)) {

						op = Expressions.predicate(Ops.LIKE, Expressions.path(field.getType(), path, field.getName()), constant);
						if (ops == null) {
							ops = op;
						} else {
							ops = Expressions.predicate(Ops.OR, ops, op);
						}
					}
				}
			}
		} catch (Exception e) {
				logger.error("error searching data for model {}", clazz, e);
			}
		}
		return ops;
	}

	private boolean isFieldAvaliableForSerch(Field field, Class<T> clazz, List<String> filteredNode,List<String> traverseList) {
		return !traverseList.contains(clazz.getSimpleName()+"."+field.getName()) && 
		(filteredNode == null || filteredNode.isEmpty() || filteredNode.contains(field.getName()));
	}

}