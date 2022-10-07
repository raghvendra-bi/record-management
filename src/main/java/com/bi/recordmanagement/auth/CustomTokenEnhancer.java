package com.bi.recordmanagement.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import com.bi.recordmangement.models.Role;
import com.bi.recordmangement.models.User;

/**
 * As of now we are sending all info into token but in future
 * token would be compact and would not be used db info
 */
@Component
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        User user = (User) authentication.getPrincipal();
        List<String> roles = new ArrayList<>();
//        for (Role role : user.getRoles()) {
//			roles.add(role.getName());
//		}
        additionalInfo.put("roles", roles);
//        additionalInfo.put("loginName", user.getLoginName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
