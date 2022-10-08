package com.bi.recordmanagement.auth;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;




@Configuration
@EnableResourceServer
@Import(AuthServerConfig.class)
public class OAuth2ResourceServerConfigJwt extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;
    
    
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // TODO add http method type in ant matchers
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .authorizeRequests().antMatchers("/login").permitAll()
            		.antMatchers("/oauth/token/revokeById/**").permitAll()
            		.antMatchers("/login/facebook").permitAll()
            		.antMatchers("/tokens/**").permitAll()
            		.antMatchers(HttpMethod.GET, "/api/health").permitAll()
            		.antMatchers("/oauth/otps/**").permitAll()
            		.antMatchers("/api/forget-password").permitAll()
            		.antMatchers(HttpMethod.POST,"/oauth/users/add").permitAll()
            		.antMatchers(HttpMethod.POST,"/oauth/reset-password").permitAll()
            		.antMatchers("/oauth/users").permitAll()
            		.antMatchers(HttpMethod.POST,"/oauth/registerUserWithToken").permitAll()
            		.antMatchers("/api/reset-password").permitAll()
            		.antMatchers("/api/specialities").permitAll()
            		.antMatchers("/oauth/access/**").permitAll()
            		.antMatchers(HttpMethod.POST, "/api/users").permitAll() 
            		// TODO should not be open
            		.antMatchers("/configuration/ui","/webjars/**",
            					 "/swagger-ui.html",
            					 "/swagger-resources/**",
            					 "/configuration/security",
            					 "/v2/api-docs").permitAll()
            		.anyRequest().authenticated();
        // @formatter:on                
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.resourceId("api");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
   
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);
        
//        converter.setVerifierKey(new String(configService.getBlobConfiguration(BlobConfigurationKeyImpl.AUTH_PUBLIC_KEY.getKeyName())));
        
      //  converter.setSigningKey("G0Med!!2o18");
        return converter;
    }

}