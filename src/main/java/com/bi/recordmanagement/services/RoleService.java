package com.bi.recordmanagement.services;

import java.util.List;

import com.bi.recordmanagement.models.Role;


public interface RoleService {
    Role getRole(String role);

    List<Role> getRole(List<String> roles);

	List<Role> getRoles() ;
}
