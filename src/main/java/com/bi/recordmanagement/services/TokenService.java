package com.bi.recordmanagement.services;

import java.util.Optional;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.bi.recordmanagement.models.AccessToken;


public interface TokenService {
	AccessToken save(AccessToken accessToken);

    Optional<AccessToken> findToken(String guid, Long userId);

    Boolean tokenValidation(OAuth2AccessToken accessToken);

    boolean verifyJwtToken(String jwtToken);

    Long getUserIdByToken(String jwtToken);
}
