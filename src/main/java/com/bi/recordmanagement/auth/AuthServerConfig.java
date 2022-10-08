package com.bi.recordmanagement.auth;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
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
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bi.recordmanagement.models.TokenString;
import com.bi.recordmanagement.services.CustomDefaultTokenService;


/**
 * @author narendra kumar
 */


@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(WebSecurityConfig.class)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServerConfig.class);

    @Value("${check.scopes}")
    private Boolean checkUserScopes;

    @Autowired
    private DataSource dataSource;

    @Qualifier("oauthClientPasswordEncoder")
    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;

    @Autowired
    @Qualifier("UserDetailsServiceByIdImpl")
    private UserDetailsService customUserDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenEnhancerChain tokenEnhancerChain;

    @Autowired
    private WebResponseExceptionTranslator customExceptionTranslator;
    

    @Bean
    public OAuth2RequestFactory requestFactory() {
        CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
        requestFactory.setCheckUserScopes(checkUserScopes);
        return requestFactory;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(oauthClientPasswordEncoder);
    }

    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager, requestFactory());
        filter.setAuthenticationEntryPoint(new AuthExceptionEntryPoint());
        return filter;
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
                .userDetailsService(customUserDetailsService)
                .tokenServices(tokenServices(tokenEnhancerChain, endpoints.getClientDetailsService()));
        //check user scope flag
        if (checkUserScopes)
            endpoints.requestFactory(requestFactory());

        endpoints.exceptionTranslator(customExceptionTranslator);
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory ksf = new KeyStoreKeyFactory(new ByteArrayResource(configService.getBlobConfiguration(BlobConfigurationKeyImpl.AUTH_KEY_STORE.getKeyName())),configService.getConfigValue(String.class, ConfigurationKeyImpl.AUTH_KEY_STORE_PASS).toCharArray());
//     	converter.setKeyPair(ksf.getKeyPair("gomedii"));
   //         converter.setSigningKey("G0Med!!2o18");

     		//		converter.setKeyPair(
//				new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices(TokenEnhancerChain tokenEnhancerChain, ClientDetailsService clientDetailsService) {
        final DefaultTokenServices defaultTokenServices = new CustomDefaultTokenService();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        addUserDetailsService(defaultTokenServices, customUserDetailsService);
        return defaultTokenServices;
    }

    private void addUserDetailsService(DefaultTokenServices tokenServices, UserDetailsService customUserDetailsService) {
        if (customUserDetailsService != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(
                    customUserDetailsService));
            tokenServices
                    .setAuthenticationManager(new ProviderManager(Arrays.<AuthenticationProvider>asList(provider)));
        }
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        return new TokenEnhancerChain();
    }


    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TokenString tokenString() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder
                        .currentRequestAttributes())
                .getRequest();
        String value = request.getHeader("Authorization").split(" ")[1];
        return new TokenString(value);
    }
    
}