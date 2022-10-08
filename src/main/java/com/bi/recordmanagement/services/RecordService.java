package com.bi.recordmanagement.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bi.recordmanagement.models.RecordFile;


public interface RecordService {
	void save(MultipartFile file);

    List<RecordFile> getAllRecords();
    
    List<RecordFile> reviewRecords(List<RecordFile> recordFiles);
    
    void deleteRecordFileWithRecordData(Long recordFileId);
}
