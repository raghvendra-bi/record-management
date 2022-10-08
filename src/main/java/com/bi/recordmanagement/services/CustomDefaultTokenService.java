package com.bi.recordmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class CustomDefaultTokenService extends DefaultTokenServices {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private TokenService tokenService;

    @Override
	public OAuth2Authentication loadAuthentication(String accessTokenValue)
			throws AuthenticationException, InvalidTokenException {
		OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(accessTokenValue);
//      boolean isNotValid = this.tokenService.tokenValidation(accessToken);
		if (accessToken == null) {
			throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
		} else if (accessToken.isExpired()) {
			this.tokenStore.removeAccessToken(accessToken);
			throw new InvalidTokenException("Access token expired: " + accessTokenValue);
		}
//        else if (isNotValid) {
//            throw new InvalidTokenException("Access token has been modified with userid and auid: " + accessTokenValue);
//           }
		else {
			OAuth2Authentication result = this.tokenStore.readAuthentication(accessToken);
			if (result == null) {
				throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
			} else {
				if (this.clientDetailsService != null) {
					String clientId = result.getOAuth2Request().getClientId();

					try {
						this.clientDetailsService.loadClientByClientId(clientId);
					} catch (ClientRegistrationException var6) {
						throw new InvalidTokenException("Client not valid: " + clientId, var6);
					}
				}
				return result;
			}
		}
	}

}
