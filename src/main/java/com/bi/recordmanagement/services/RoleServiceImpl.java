package com.bi.recordmanagement.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bi.recordmanagement.models.QRole;
import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.repository.RoleRepository;
import com.bi.recordmanagement.services.MessageByLocaleService;
import com.bi.recordmanagement.services.RoleService;
import com.bi.recordmanagement.utils.PaginationConfig;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepo;
    @Autowired
    MessageByLocaleService messageByLocaleService;

    @Override
    public Role getRole(String role) {
        Role roleEx = new Role();
        roleEx.setName(role);
        return roleRepo.findOne(Example.of(roleEx)).orElseThrow(() -> new EntityNotFoundException(
                messageByLocaleService.getMessage("err.role.not.exists", new String[]{role})));
    }

    @Override
    public List<Role> getRole(List<String> role) {
    	
        return  (List<Role>) roleRepo.findAll(QRole.role.name.in(role));

    }

	@Override
	public List<Role> getRoles() {
		return roleRepo.findAll();
	}

}	
