package com.bi.recordmanagement.auth;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

//import com.lfs.auth.enums.ConfigurationKeyImpl;
//import com.lfs.auth.model.BlobConfigurationKeyImpl;
//import com.lfs.config.ConfigurationService;

/**
 * @author himanshu
 */


@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(WebSecurityConfig.class)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Qualifier("oauthClientPasswordEncoder")
    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;

    @Autowired
    @Qualifier("UserDetailsServiceByIdImpl")
    private UserDetailsService customUserDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenEnhancerChain tokenEnhancerChain;

    @Autowired
    private WebResponseExceptionTranslator customExceptionTranslator;
    
//    @Autowired
//    private ConfigurationService configService;
    
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    
    @Bean
    public OAuth2RequestFactory requestFactory() {
    	CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
        return requestFactory;
    }
    
    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager, requestFactory());
        filter.setAuthenticationEntryPoint(new AuthExceptionEntryPoint());
        return filter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(oauthClientPasswordEncoder);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()")
                .passwordEncoder(oauthClientPasswordEncoder).allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
    	endpoints.tokenStore(tokenStore())
    	.tokenEnhancer(tokenEnhancerChain)
    	.authenticationManager(authenticationManager)
    	.userDetailsService(customUserDetailsService);
    	endpoints.requestFactory(requestFactory());
    	endpoints.exceptionTranslator(customExceptionTranslator);
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory ksf = new KeyStoreKeyFactory(new ByteArrayResource(configService.getBlobConfiguration(BlobConfigurationKeyImpl.AUTH_KEY_STORE.getKeyName())),configService.getConfigValue(String.class, ConfigurationKeyImpl.AUTH_KEY_STORE_PASS).toCharArray());
//		converter.setKeyPair(ksf.getKeyPair("mytest"));
        return converter;
    }

	@Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        return new TokenEnhancerChain();
    }
}