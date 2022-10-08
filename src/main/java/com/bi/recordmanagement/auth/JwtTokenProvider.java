package com.bi.recordmanagement.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;

import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.User;


/**
 * Class for creating, verifying jwt_token
 */
@Component
public class JwtTokenProvider {

    @Autowired
    private AuthorizationServerEndpointsConfiguration configuration;
    
    
    public String generateTokenWithPrinciple(User userPrincipal) {
        return generateToken(userPrincipal);
    }

    public String generateToken(User user) {
    
        Map<String, String> requestParameters = new HashMap<String, String>();
		Map<String, Serializable> extensionProperties = new HashMap<String, Serializable>();
    	List<Role> roles = user.getRoles().stream().collect(Collectors.toList());
    	List<String> scopes = roles.stream()
    		    .map(Role::getName)
    		    .collect(Collectors.toList());
    	
		boolean approved = true;
		Set<String> responseTypes = new HashSet<String>();
		responseTypes.add("code");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE" + roles));

		OAuth2Request oauth2Request = new OAuth2Request(requestParameters, user.getClientId(), authorities, approved, new HashSet<String>(scopes),
				new HashSet<String>(Arrays.asList("api")), null, responseTypes, extensionProperties);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, "N/A", authorities);
		OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);
		AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();
		OAuth2AccessToken token = tokenService.createAccessToken(auth);

		return token.getValue();
    }

}
