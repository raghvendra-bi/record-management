package com.bi.recordmanagement.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RMServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final HttpStatus code;
    private final String message;

    public RMServiceException(String message) {
        super();
        this.code = HttpStatus.UNPROCESSABLE_ENTITY;
        this.message = message;
    }
    
    public RMServiceException(HttpStatus httpStatus, String message) {
        super();
        this.code = httpStatus;
        this.message = message;
    }
}
