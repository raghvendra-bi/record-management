package com.bi.recordmanagement.vo;

import java.util.List;

import com.bi.recordmanagement.views.UserViews;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVo {

    @JsonView(UserViews.UserBasicView.class)
    private Long id;
    @JsonView(UserViews.UserBasicView.class)
    private String loginName;
    @JsonView(UserViews.UserBasicView.class)
    private String password;
    @JsonView(UserViews.UserBasicView.class)
    private String phone;
    @JsonView(UserViews.UserBasicView.class)
    private String email;
    @JsonView(UserViews.UserBasicView.class)
    private List<String> roles;
    
    private String domain;
    
    @JsonView(UserViews.UserBasicView.class)
    private String firstName;
    
    @JsonView(UserViews.UserBasicView.class)
	private String lastName;
    
    private Boolean isPhoneVerfied = Boolean.FALSE;

    private Boolean isEmailVerfied = Boolean.FALSE;
    
    @JsonView(UserViews.UserBasicView.class)
    private String phoneCode;
    
    
    private String clientId;
}
