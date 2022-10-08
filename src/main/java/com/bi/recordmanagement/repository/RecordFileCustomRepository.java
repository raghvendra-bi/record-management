package com.bi.recordmanagement.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bi.recordmanagement.models.QRecordColumn;
import com.bi.recordmanagement.models.QRecordFile;
import com.bi.recordmanagement.models.QRecordRow;
import com.bi.recordmanagement.models.RecordColumn;
import com.bi.recordmanagement.models.RecordFile;
import com.bi.recordmanagement.models.RecordRow;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAUpdateClause;

@Repository
public class RecordFileCustomRepository  extends QuerydslRepositorySupport{
	
	public RecordFileCustomRepository() {
		super(RecordFile.class);
	}

	public Map<Long, List<RecordColumn>> getMapOfFileAndColumns() {
		return from(QRecordFile.recordFile)
				.innerJoin(QRecordFile.recordFile.columns, QRecordColumn.recordColumn)
				.orderBy(QRecordColumn.recordColumn.id.asc())
				.transform(GroupBy.groupBy(QRecordFile.recordFile.id).as(GroupBy
						.list(QRecordColumn.recordColumn)));
	}
	
	public Map<Long, List<RecordRow>> getMapOfFileAndRows() {

		return from(QRecordFile.recordFile)
				.innerJoin(QRecordFile.recordFile.rows, QRecordRow.recordRow)
				.orderBy(QRecordRow.recordRow.id.asc())
				.transform(GroupBy.groupBy(QRecordFile.recordFile.id).as(GroupBy
						.list(QRecordRow.recordRow)));
	}
	
	@Transactional
	public long updateModifiedByAndTime(String modifiedById, List<Long> ids) {
		UpdateClause<JPAUpdateClause> s = update(QRecordFile.recordFile)
				.where(QRecordFile.recordFile.id.in(ids));
		s.set(QRecordFile.recordFile.reviewedOn, new Date());
		s.set(QRecordFile.recordFile.reviewedBy, modifiedById);
		long noOfRowsAffected=s.execute();
		return noOfRowsAffected;
	}
}
