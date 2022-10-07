package com.bi.recordmanagement.auth;
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

//import com.lfs.auth.model.BlobConfigurationKeyImpl;
//import com.lfs.config.ConfigurationService;

@Configuration
@EnableResourceServer
@Import(AuthServerConfig.class)
public class OAuth2ResourceServerConfigJwt extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;
    
//    @Autowired
//    private ConfigurationService configService;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // TODO add http method type in ant matchers
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .authorizeRequests().antMatchers("/login*").permitAll()
            		.antMatchers("/token/revokeById/**").permitAll()
            		.antMatchers("/tokens/**").permitAll()
            		.antMatchers(HttpMethod.POST,"/otps/**").permitAll()
            		.antMatchers(HttpMethod.POST,"/resetPassword").permitAll()
            		.antMatchers(HttpMethod.POST,"/users").permitAll()
            		.antMatchers(HttpMethod.POST,"/users/checkLoginName").permitAll()
            		.antMatchers(HttpMethod.PUT,"/users/checkEmail").permitAll()
//            		.antMatchers(HttpMethod.GET,"/forwardLogin&loginNa=*").permitAll()
            		.antMatchers(HttpMethod.GET,"/forwardLogin*").permitAll()
//            		.antMatchers(HttpMethod.GET,"/forwardLogin/**").permitAll()
            		.antMatchers(HttpMethod.POST,"/facebooklogin").permitAll()
            		.antMatchers(HttpMethod.POST,"/login/facebook").permitAll()
            		.antMatchers(HttpMethod.POST,"/access/**").permitAll()
            		.antMatchers("/users/registerUserConfirmations").permitAll()
            		.antMatchers("/configuration/ui","/webjars/**",
          					 "/swagger-ui/**",
          					 "/swagger-resources/**",
          					 "/v2/api-docs",
          					"/v3/api-docs").permitAll()
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
        return converter;
    }

}