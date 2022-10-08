package com.bi.recordmanagement.models;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "record_file")
@Audited
public class RecordFile {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    @JsonView({PublisherView.PublisherBasicView.class,OauthUserDetailedView.class})
	    private Long id;

//	    @ApiModelProperty(notes = "Name of Role")
//	    @NotBlank(message = "{err.role.name.empty}")
//	    @Size(min = 2, max = 15, message = "{err.role.name.length}")
//	    @JsonView({PublisherView.PublisherBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
	    private String name;
	    
	    @Enumerated(EnumType.STRING)
	    private FileUploadStatus status;
	    
		private String reviewedBy;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date reviewedOn;
		
		@NotAudited
		@OneToMany(mappedBy = "file", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
		private List<RecordRow> rows;
		
		@NotAudited
		@OneToMany(mappedBy = "file", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
		private List<RecordColumn> columns;
		
}
