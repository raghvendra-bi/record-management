package com.bi.recordmanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "file_progress")
@Audited
public class RecordProgress {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonView({PublisherView.PublisherBasicView.class,OauthUserDetailedView.class})
    private Long id;

//    @ApiModelProperty(notes = "Name of Role")
//    @NotBlank(message = "{err.role.name.empty}")
//    @Size(min = 2, max = 15, message = "{err.role.name.length}")
//    @JsonView({PublisherView.PublisherBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    private String progress;
    
    @OneToOne()
    private RecordFile file;
}
