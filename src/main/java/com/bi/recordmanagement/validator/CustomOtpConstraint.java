package com.bi.recordmanagement.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CustomOtpValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomOtpConstraint {
	String message() default "OTP for Email or phone both can not be null";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
