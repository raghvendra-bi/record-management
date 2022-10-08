package com.bi.recordmanagement.auth;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.repository.UserRepository;
import com.bi.recordmanagement.services.LoginSuccessAndFailService;
import com.bi.recordmanagement.services.MessageByLocaleService;

public class CustomAuthenticationFilter extends TokenEndpointAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LoginSuccessAndFailService authSuccessAndFailService;

    private Authentication authentication;
    
    
    @Autowired
    private MessageByLocaleService messageByLocaleService;
    
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {
        super(authenticationManager, oAuth2RequestFactory);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        authentication = super.extractCredentials(request);
        super.doFilter(req,res,chain);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        LOGGER.info("============User Oauth2 login success===============");
       
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
       
    	LOGGER.info("============User Oauth2 login failed===============");
       
    }
    
	
	
}
