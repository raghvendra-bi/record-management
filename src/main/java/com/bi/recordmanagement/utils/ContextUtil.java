package com.bi.recordmanagement.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.services.UserService;

@Component
public class ContextUtil {

    @Autowired
    UserService userService;

    public Optional<User> getUser() {
    	
        User user = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userId != null) {
            try {
                user = userService.getUser(Long.parseLong(userId));

            } catch (NumberFormatException e) {

            }
        }
        return Optional.ofNullable(user);
    }
    
    public String getClientID() {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return ((OAuth2Authentication) authentication).getOAuth2Request().getClientId();
    }

}
