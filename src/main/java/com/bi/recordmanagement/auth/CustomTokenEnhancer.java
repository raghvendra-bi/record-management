package com.bi.recordmanagement.auth;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.bi.recordmanagement.models.AccessToken;
import com.bi.recordmanagement.models.Authority;
import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.services.MessageByLocaleService;
import com.bi.recordmanagement.services.TokenService;
import com.bi.recordmanagement.utils.GMConstant;
import com.bi.recordmanagement.vo.UserExtraInfo;

/**
 * As of now we are sending all info into token but in future
 * token would be compact and would not be used db info
 */
@Component
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Qualifier("accessTokenServiceImpl")
    @Autowired
    private TokenService tokenService;
//    @Autowired
//    private GmcService gmcService;
    @Autowired
    private MessageByLocaleService messageService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomTokenEnhancer.class);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//    	UserExtraInfo extraInfo = null;
    	final Map<String, Object> additionalInfo = new LinkedHashMap<>();
        //adding hooks if token is for client only .
        if(authentication.isClientOnly() || authentication.getPrincipal() instanceof String)
            return super.enhance(accessToken, authentication);

        User user = (User) authentication.getPrincipal();
        user.setAuthorities(authentication.getAuthorities());
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }
        Set<String> scopes = accessToken.getScope();
        user.getAuthorities().forEach(a -> LOGGER.debug("all user's authority -> " + a.getAuthority()));
        //override authorities :
        Set<? extends GrantedAuthority> authorities = createAuthoritiesBasedOnScopes(scopes, user);
        LOGGER.debug("GrantedAuthorities -> " + authorities);
        additionalInfo.put("authorities", authorities.stream().map(authority-> authority.getAuthority()).collect(Collectors.toList()));
        additionalInfo.put("roles", roles);
        additionalInfo.put("email", user.getEmail());
        String name = null;
        if(!StringUtils.isEmpty(user.getFirstName())) {
        	name = user.getFirstName();
        }
        
        if(name != null && !StringUtils.isEmpty(user.getLastName())) {
        	 name =  name +" "+user.getLastName();
        }
        additionalInfo.put("name", name);
        additionalInfo.put("clientId", user.getClientId());
        
        String clientId = authentication.getOAuth2Request().getClientId();
        
        //Going to save token info while enhancing the token .
        AccessToken token = new AccessToken();
        token.setGuid(UUID.randomUUID().toString());
        token.setUserId(user.getId());
        token.setCreatedOn(LocalDateTime.now());
        //as of now we just leave it blank if we are thinking one token would be used to multiple clients then
        //client should be put here.
        token.setClientId(null);
        token.setExpiryTime(accessToken.getExpiration().toString());
        AccessToken savedToken = tokenService.save(token);
        additionalInfo.put("auid", savedToken.getGuid());
        additionalInfo.put("user_id", savedToken.getUserId());
        // token has been saved and incorporated in access_token also .
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(additionalInfo);

        return super.enhance(customAccessToken, authentication);
    }

    //added function to filter out roles based on scopes .
    private Set<? extends GrantedAuthority> createAuthoritiesBasedOnScopes(Set<String> scopes, User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        scopes.forEach(s -> LOGGER.debug("scope -> " + s));
        for (String scop : scopes) {
            for (Role role : user.getRoles()) {
                if (scop.equals(role.getName())) {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                    role.getAuthorities().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
                } else {
                    for (Authority authority : role.getAuthorities()) {
                        if (scop.equals(authority.getName()))
                            authorities.add(new SimpleGrantedAuthority(authority.getName()));
                    }
                }
            }
        }
        authorities.forEach(a -> LOGGER.debug("authority name based on scope  ->" + a.getAuthority()));
        return authorities;
    }
}
