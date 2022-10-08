package com.bi.recordmanagement.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "user_passwords")
@Getter@Setter
public class UserPassword {
	@Id
	private Long id;
    private String password;

}
