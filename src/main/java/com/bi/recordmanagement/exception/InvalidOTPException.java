package com.bi.recordmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidOTPException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidOTPException() {
        super();
    }

    public InvalidOTPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidOTPException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOTPException(String message) {
        super(message);
    }

    public InvalidOTPException(Throwable cause) {
        super(cause);
    }


}
