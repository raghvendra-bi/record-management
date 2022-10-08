package com.bi.recordmanagement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.authentication.BadCredentialsException;

import com.bi.recordmanagement.services.PhoneAndEmailValidator;

public class PhoneNumberAndEmailValidator implements ConstraintValidator<PhoneAndEmailValidator, Object> {

	private String fieldName;
	private String dependFieldName;

	@Override
	public void initialize(PhoneAndEmailValidator annotation) {
		fieldName = annotation.fieldName();
		dependFieldName = annotation.dependFieldName();
	}

	@Override
	public boolean isValid(Object phoneNumber, ConstraintValidatorContext context) {
			try {
				BeanWrapper wrapper = new BeanWrapperImpl(phoneNumber);
				if (null != wrapper.getPropertyValue(fieldName) && (wrapper.getPropertyValue(fieldName).toString().contains("@"))) {
					return true;
				} else if (null == wrapper.getPropertyValue(fieldName)) {
					return false;
				}
				String[]  strArray = wrapper.getPropertyValue(fieldName).toString().split("-");
				if(strArray == null || strArray.length != 2) {
	    			return false;
	    		}
				String fieldValue = strArray[1];
				String dependFieldValue = "IN";
				if (null != wrapper.getPropertyValue(dependFieldName)) {
					dependFieldValue = wrapper.getPropertyValue(dependFieldName).toString();
				}
//				PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
//				return phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(fieldValue, dependFieldValue));
				return true;

			} catch (Exception ex) {
				return false;
			}
		
	}

}