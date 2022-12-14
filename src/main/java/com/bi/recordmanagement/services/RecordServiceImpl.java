package com.bi.recordmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bi.recordmanagement.exception.RMServiceException;
import com.bi.recordmanagement.helper.Helper;
import com.bi.recordmanagement.models.RecordColumn;
import com.bi.recordmanagement.models.RecordFile;
import com.bi.recordmanagement.models.RecordRow;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.repository.RecordColumnRepository;
import com.bi.recordmanagement.repository.RecordFileCustomRepository;
import com.bi.recordmanagement.repository.RecordFileRepository;
import com.bi.recordmanagement.repository.RecordProgressRepository;
import com.bi.recordmanagement.repository.RecordRowRepository;
import com.bi.recordmanagement.utils.ContextUtil;
import com.bi.recordmanagement.vo.RecordVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordFileRepository recordFileRepo;
    
    @Autowired
    private RecordProgressRepository recordProgressRepo;
    
    @Autowired
    private RecordColumnRepository recordColumnRepo;
    
    @Autowired
    private RecordRowRepository recordRowRepository;
    
    @Autowired
    private RecordFileCustomRepository recordFileRepository;
    
    @Autowired
    ContextUtil contextUtil;

    @Override
    public void save(MultipartFile file) {

        try {
            RecordFile recordFile = Helper.getRecordFileName(file);
            recordFile = this.recordFileRepo.save(recordFile);
            List<RecordRow> recordRows = Helper.getRows(file.getInputStream());
            for(RecordRow recordRow:recordRows) {
            	recordRow.setFile(recordFile);
            }
            List<RecordColumn> recordColumns = Helper.getColumns(file.getInputStream());
            for(RecordColumn recordColumn:recordColumns) {
            	recordColumn.setFile(recordFile);
            }
            recordColumns = this.recordColumnRepo.saveAll(recordColumns);
            recordRows = this.recordRowRepository.saveAll(recordRows);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Map<Long, List<Map<String, String>>> getAllRecords() {
    	Map<Long, List<RecordColumn>> mapColumn = recordFileRepository.getMapOfFileAndColumns();
    	Map<Long, List<RecordRow>> mapRow = recordFileRepository.getMapOfFileAndRows();
    	System.out.println(mapColumn);
    	System.out.println(mapRow);
    	Map<Long, List<Map<String, String>>> recordsWithHeaders = new LinkedHashMap<>();
    	for (Map.Entry<Long,List<RecordColumn>> entry : mapColumn.entrySet()) {
    		List<String> cols  = entry.getValue().stream().map(col-> col.getColumn()).collect(Collectors.toList());
    		
    		List<String> rows = mapRow.get(entry.getKey()).stream().map(row-> row.getRow()).collect(Collectors.toList());
    		Map<String, String> singleRecord = new LinkedHashMap<>();
    		List<Map<String, String>> records = new ArrayList<>();
    		int i=0;
    		for(String row:rows) {
    			if(i<cols.size()) {
    				singleRecord.put(cols.get(i), row);
    				
    			} else {
    				i=0;
    				records.add(singleRecord);
    				singleRecord = new LinkedHashMap<>();
    				singleRecord.put(cols.get(i), row);
    			}
    			i++;
    		}
    		records.add(singleRecord);
    		recordsWithHeaders.put(entry.getKey(), records);
    		
    	}
    	
    	return recordsWithHeaders;
    }
    
    @Override
    public Boolean reviewRecords(List<Long> ids) {
    	Optional<User> loggedInUser = contextUtil.getUser();
    	if(loggedInUser.isPresent()) {
    		long updated = this.recordFileRepository.updateModifiedByAndTime(String.valueOf(loggedInUser.get().getId()), ids);
            if(updated>0) {
            	return true;
            } else {
            	return false;
            }
    	} else {
    		throw new RMServiceException(HttpStatus.UNAUTHORIZED, "You are not authorized");
    	}
        
    }
    
    @Override
    public void deleteRecordFileWithRecordData(Long recordFileId) {
//    	this.recordFileRepo.getOne(recordFileId)
    	Optional<RecordFile> recordFile = this.recordFileRepo.findById(recordFileId);
    	if(recordFile.isPresent()) {
    		this.recordFileRepo.deleteById(recordFileId);
    	} else {
    		throw new RMServiceException(HttpStatus.UNPROCESSABLE_ENTITY, "incorrect id");
    	}
    }


}
