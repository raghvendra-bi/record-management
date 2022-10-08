package com.bi.recordmanagement.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "file_row")
@Audited
public class RecordRow {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonView({PublisherView.PublisherBasicView.class,OauthUserDetailedView.class})
    private Long id;
	
	@Column(name = "recordRow")
	private String row;
    
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fileId", foreignKey = @ForeignKey(name = "fk_t_file_row_fileId_t_record_file_id", value = ConstraintMode.CONSTRAINT))
    private RecordFile file;
}
