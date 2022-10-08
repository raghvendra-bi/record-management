package com.bi.recordmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bi.recordmanagement.helper.Helper;
import com.bi.recordmanagement.models.RecordColumn;
import com.bi.recordmanagement.models.RecordFile;
import com.bi.recordmanagement.models.RecordRow;
import com.bi.recordmanagement.repository.RecordColumnRepository;
import com.bi.recordmanagement.repository.RecordFileRepository;
import com.bi.recordmanagement.repository.RecordProgressRepository;
import com.bi.recordmanagement.repository.RecordRowRepository;

import java.io.IOException;
import java.util.List;

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
    public List<RecordFile> getAllRecords() {
        return this.recordFileRepo.findAll();
    }
    
    @Override
    public List<RecordFile> reviewRecords(List<RecordFile> recordFiles) {
        return this.recordFileRepo.findAll();
    }
    
    @Override
    public void deleteRecordFileWithRecordData(Long recordFileId) {
    }


}
