package com.bi.recordmanagement.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.bi.recordmanagement.models.QRecordColumn;
import com.bi.recordmanagement.models.QRecordFile;
import com.bi.recordmanagement.models.QRecordRow;
import com.bi.recordmanagement.models.RecordColumn;
import com.bi.recordmanagement.models.RecordFile;
import com.bi.recordmanagement.models.RecordRow;
import com.querydsl.core.group.GroupBy;

@Repository
public class RecordFileCustomRepository  extends QuerydslRepositorySupport{
	
	public RecordFileCustomRepository() {
		super(RecordFile.class);
		// TODO Auto-generated constructor stub
	}

	public Map<Long, List<RecordColumn>> getMapOfFileAndColumns() {
//		return from(QLineup.lineup).innerJoin(QLineup.lineup.contest, QContest.contest)
//				.innerJoin(QLineup.lineup.createdBy, QUser.user).where(QContest.contest.id.eq(contestId))
//				.groupBy(QUser.user.id)
//				.transform(GroupBy.groupBy(QLineup.lineup.createdBy.id).as(QLineup.lineup.createdBy.id.count()));
		
//		return from(Q)
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
}
