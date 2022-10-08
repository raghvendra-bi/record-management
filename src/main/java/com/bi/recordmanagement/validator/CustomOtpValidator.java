package com.bi.recordmanagement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.bi.recordmanagement.models.User;


public class CustomOtpValidator   implements ConstraintValidator<CustomOtpConstraint, User>{

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
			if(user != null) {
			return !(StringUtils.isEmpty(user.getOtpForEmail()) && StringUtils.isEmpty(user.getOtpForPhone()));
			}
					
		
		return false;
	}

}
