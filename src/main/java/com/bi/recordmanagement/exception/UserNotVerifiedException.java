package com.bi.recordmanagement.exception;

import org.springframework.security.core.AuthenticationException;

import com.bi.recordmanagement.enums.RmExceptionEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotVerifiedException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 3963292047226151164L;

    private final int code;
    private final String message;
    
    public UserNotVerifiedException(String message) {
        super(message);
        this.code = RmExceptionEnum.USER_NOT_VERIFIED.getCode();
        this.message = message;
    }
    
    public UserNotVerifiedException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
