package com.bi.recordmangement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

//@Entity
//@Getter
//@Setter
//@Table
public class User {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
	@JsonView({})
	private Long id;
	
	@JsonView({})
//	@NotEmpty(message = "{err.user.loginName.empty}")
	private String email;
		
	@JsonView({})
//	@NotEmpty(message = "{err.user.loginName.empty}")
	private String password;
}
