package com.bi.recordmanagement.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.bi.recordmanagement.auth.CustomOauthExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    public CustomOauthException(String msg) {
        super(msg);
    }
}
