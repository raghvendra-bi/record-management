package com.bi.recordmanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;

import com.bi.recordmanagement.models.AccessToken;
import com.bi.recordmanagement.repository.AccessTokenRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class AccessTokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenServiceImpl.class);
    @Autowired
    private AccessTokenRepository tokenRepository;
    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    //save the AccessToken object
    public AccessToken save(AccessToken accessToken) {
        return this.tokenRepository.save(accessToken);
    }

    @Override
    public Optional<AccessToken> findToken(String guid, Long userId) {
        return this.tokenRepository.findToken(guid, userId);
    }

    @Override
    public Boolean tokenValidation(OAuth2AccessToken accessToken) {
        String guid = (String) accessToken.getAdditionalInformation().getOrDefault("auid", null);
        Long userid = Long.valueOf((Integer) accessToken.getAdditionalInformation().getOrDefault("user_id", 0));
        if (guid == null || userid == null)
            return true;

        Optional<AccessToken> token = this.tokenRepository.findToken(guid, userid);
        return token.map(tokenL -> {LOGGER.debug("OAuth2AccessToken tokenValidation {}",tokenL.toString()); return true;}).isPresent();
    }

    /*@Override
    public AccessToken getToken(String userId, String clientId) {
        AccessToken token = new AccessToken();
        token.setGuid(UUID.randomUUID().toString());
        token.setUserId(new Long(userId));
        token.setCreatedOn(LocalDateTime.now());
        token.setClientId(clientId);
        token.setExpiryTime(LocalDateTime.now().plusSeconds(100).toString());
        return tokenRepository.save(token);
    }*/


    @Override
    public boolean verifyJwtToken(String jwtToken) {
        OAuth2AccessToken token = resourceServerTokenServices.readAccessToken(jwtToken);
        if (token == null) {
            LOGGER.debug("Token was not recognised");
            throw new InvalidTokenException("Token was not recognised");
        } else if (token.isExpired()) {
            LOGGER.debug("Token has expired");
            throw new InvalidTokenException("Token has expired");
        } else {
            //as of now we just check token of expiration .In future we will check auid with DB
            //DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(token);
            //Map<String,Object> map = customAccessToken.getAdditionalInformation();
            /*if(null!=map.get("user_id"))
                userId = (Integer) map.get("user_id");
            if (null!=map.get("auid"))
                tokenId = (String)map.get("auid");*/
            return true;
        }
    }

    @Override
    public Long getUserIdByToken(String jwtToken) {
        OAuth2AccessToken token = resourceServerTokenServices.readAccessToken(jwtToken);
        Integer userId = 0;
        if (token == null) {
            LOGGER.debug("Token was not recognised");
            throw new InvalidTokenException("Token was not recognised");
        } else if (token.isExpired()) {
            LOGGER.debug("Token has expired");
            throw new InvalidTokenException("Token has expired");
        } else {
            //as of now we just check token of expiration .In future we will check auid with DB
            DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(token);
            Map<String,Object> map = customAccessToken.getAdditionalInformation();
            if(null!=map.get("user_id"))
                userId = (Integer) map.get("user_id");

            return Long.valueOf(userId);
        }
    }


}

