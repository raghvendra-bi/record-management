package com.bi.recordmanagement.auth;

import java.util.Map;

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

//import com.lfs.auth.services.MessageByLocaleService;
//import com.lfs.auth.services.UserService;
import com.bi.recordmanagement.util.RMConstant;
import com.bi.recordmangement.services.MessageByLocaleService;
import com.bi.recordmangement.services.UserService;

@Component
public class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {

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
    	if (StringUtils.isEmpty(requestParameters.get(RMConstant.GRANT_TYPE)))
    		throw new InvalidGrantException(messageByLocaleService.getMessage("err.user.grant.type.empty"));
    	//Customized - check scopes if its blank then throw the exception
    	if (requestParameters.get(RMConstant.GRANT_TYPE).equals(RMConstant.PASSWORD)) {
    		scopeBuilder = userService.addScope(requestParameters.get(RMConstant.USER_NAME), authenticatedClient.getScope());
    	}
    	if(StringUtils.isEmpty(scopeBuilder) || scopeBuilder.length() == 0) {
    		throw new InvalidScopeException(messageByLocaleService.getMessage("err.user.scope.empty"));
    	} else {
    		requestParameters.put(RMConstant.SCOPE,scopeBuilder.toString() );
    	}
    	if (requestParameters.get(RMConstant.GRANT_TYPE).equals(RMConstant.REFRESH_TOKEN)) {
    		OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(
    				tokenStore.readRefreshToken(requestParameters.get(RMConstant.REFRESH_TOKEN)));

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
