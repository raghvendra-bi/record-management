package com.bi.recordmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.bi.recordmanagement.models.RecordFile;

@Repository
public interface RecordFileRepository extends JpaRepository<RecordFile, String>, QuerydslPredicateExecutor<RecordFile> {

}
