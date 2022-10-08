package com.bi.recordmanagement.auth;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bi.recordmanagement.services.MessageByLocaleService;
import com.bi.recordmanagement.services.UserService;
import com.bi.recordmanagement.utils.GMConstant;


@Component
public class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomOauth2RequestFactory.class);
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("userDetailsServiceByIdImpl")
    private UserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private MessageByLocaleService messageByLocaleService;

    public CustomOauth2RequestFactory(ClientDetailsService clientDetailsService) {
        super(clientDetailsService);
    }

    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters,
    		ClientDetails authenticatedClient) {
    	StringBuilder scopeBuilder = new StringBuilder();

    	//Customized - check grant_type if its blank then throw the exception
    	if (StringUtils.isEmpty(requestParameters.get(GMConstant.GRANT_TYPE)))
    		throw new InvalidGrantException(messageByLocaleService.getMessage("err.user.grant.type.empty"));
    	//Customized - check scopes if its blank then throw the exception
    	if (requestParameters.get(GMConstant.GRANT_TYPE).equals(GMConstant.PASSWORD)) {
    		scopeBuilder = userService.addScope(requestParameters.get(GMConstant.USER_NAME), authenticatedClient.getScope());
    	}
    	if(StringUtils.isEmpty(scopeBuilder)) {
    		throw new InvalidScopeException(messageByLocaleService.getMessage("err.user.scope.empty"));
    	} else {
    		requestParameters.put(GMConstant.SCOPE,scopeBuilder.toString() );
    	}
    	if (requestParameters.get(GMConstant.GRANT_TYPE).equals(GMConstant.REFRESH_TOKEN)) {
    		OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(
    				tokenStore.readRefreshToken(requestParameters.get(GMConstant.REFRESH_TOKEN)));

    		SecurityContextHolder.getContext()
    		.setAuthentication(new UsernamePasswordAuthenticationToken(authentication.getName(), null,
    				customUserDetailsService.loadUserByUsername(authentication.getName()).getAuthorities()));
    	}

    	/*if (requestParameters.get("grant_type").equals("client_credentials")) {
            OAuth2Authentication authentication = tokenStore.readAuthentication(
                    tokenStore.readAccessToken(requestParameters.get("access_token")));
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(authentication.getName(), null,
                            clientDetailsService.loadClientByClientId(authentication.getName()).getAuthorities()));

        }*/

    	return super.createTokenRequest(requestParameters, authenticatedClient);
    }
}
