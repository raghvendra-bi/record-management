package com.bi.recordmanagement.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import com.bi.recordmanagement.exception.CustomOauthException;
import com.bi.recordmanagement.exception.EntityNotFoundException;

import java.util.NoSuchElementException;

/**
 * @author narendra
 * This class would be used to handle Oauth2Exceptions
 */

@Component("customExceptionTranslator")
public class CustomExceptionTranslator extends DefaultWebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception exception) throws Exception {

        if (exception instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        } else if (exception instanceof AuthenticationException) {
            AuthenticationException authenticationException = (AuthenticationException) exception;
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOauthException(authenticationException.getMessage()));
        }else if (exception instanceof EntityNotFoundException) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CustomOauthException(exception.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .body(new CustomOauthException(exception.getMessage()));
    }

}
