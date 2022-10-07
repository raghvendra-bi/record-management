package com.bi.recordmangement.services;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import com.lfs.auth.exception.EntityNotFoundException;
//import com.lfs.auth.model.QUser;
//import com.lfs.auth.model.Role;
//import com.lfs.auth.model.User;

public class UserServiceImpl {
	
//	@Override
//	public StringBuilder addScope(String userName, Set<String> scopeList) {
//		StringBuilder scopeBuilder = new StringBuilder();
//		User user = userRepository.findOne(QUser.user.loginName.eq(userName).or(QUser.user.email.eq(userName))).orElseThrow( ()->new EntityNotFoundException(messageByLocaleService.getMessage("err.user.userName.incorrect") ));
//		List<Role> rolesList = (List<Role>) user.getRoles();
//		Iterator<String> itr = scopeList.iterator();
//		while (itr.hasNext()) {
//			String clientScope = itr.next();
//			for (Role role : rolesList) {
//				if(clientScope.equals(role.getName())) {
//					scopeBuilder = scopeBuilder.append(role.getName()).append(" ");
//					break;
//				}
//			}
//		}
//		return scopeBuilder;
//	}
}
