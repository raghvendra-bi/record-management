package com.bi.recordmanagement.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bi.recordmanagement.config.Encoders;

@EnableWebSecurity
//TODO: use SecurityProperties.ACCESS_OVERRIDE_ORDER
@Order(100)
@Import(Encoders.class)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;


    @Autowired
    @Qualifier("userPasswordEncoder")
    private PasswordEncoder userPasswordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:on
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth/token/revokeById/**").permitAll()
                .antMatchers("/oauth/users").permitAll()
                .antMatchers(HttpMethod.POST,"/oauth/registerUserWithToken").permitAll()
                .antMatchers("/oauth/check_token").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/oauth/health").permitAll()
                .antMatchers("/oauth/users/add").permitAll()
                .antMatchers("/oauth/users/delete").permitAll()
                .antMatchers("/oauth/change-password").permitAll()
                .antMatchers("/oauth/reset-password").permitAll()
                .antMatchers("/oauth/logout").permitAll()
                .antMatchers("/records/**").permitAll()
                .antMatchers("/configuration/ui", "/webjars/**",
                        "/swagger-ui.html**",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and()
               // .logout().logoutSuccessUrl("/").permitAll()
               // .and()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())
                .and().csrf().disable();
        // @formatter:off

    }


}