package com.bi.recordmanagement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.bi.recordmanagement.models.User;

public class CustomValidator implements ConstraintValidator<CustomConstraint, User>{

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
			if(user != null) {
			return !(StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPhone()));
			
			}
				
		return false;
	}

}
