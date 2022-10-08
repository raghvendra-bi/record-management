package com.bi.recordmanagement.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bi.recordmanagement.models.RecordFile;
import com.bi.recordmanagement.vo.RecordVo;


public interface RecordService {
	void save(MultipartFile file);

	Map<Long, List<Map<String, String>>> getAllRecords();
    
    public Boolean reviewRecords(List<Long> ids);
    
    void deleteRecordFileWithRecordData(Long recordFileId);
}
