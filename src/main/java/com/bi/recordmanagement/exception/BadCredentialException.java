package com.bi.recordmanagement.exception;

import com.bi.recordmanagement.enums.RmExceptionEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadCredentialException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 3963292047226151164L;

    private final int code;
    private final String message;
    
    public BadCredentialException(String message) {
        super();
        this.code = RmExceptionEnum.BAD_CREDENTIALS.getCode();
        this.message = message;
    }
}
