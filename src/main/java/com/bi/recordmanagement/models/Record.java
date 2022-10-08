package com.bi.recordmanagement.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

//@Entity
//@Getter
//@Setter
//@Table(name = "file")
//@Audited
public class Record {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @JsonView({PublisherView.PublisherBasicView.class,OauthUserDetailedView.class})
//    private Long id;
//	
//	@OneToOne()
//    private RecordFile file;
}
