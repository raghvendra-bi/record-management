package com.bi.recordmanagement.enums;

public enum RmExceptionEnum {

	USER_NOT_VERIFIED(1001),
	FACEBOOK_USER_NOT_FOUND(1004),
	USER_WEAK_CREDENTIALS(1003),
	BAD_CREDENTIALS(1002),
	USER_2FACTOR_SMS_AUTHENTICATION_FAIL(1005);


	int code;
	
	private RmExceptionEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
