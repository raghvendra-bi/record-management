package com.bi.recordmanagement.services;

//import com.gm.auth2.enums.ConfigurationKeyImpl;
import com.bi.recordmanagement.models.QUser;
import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.repository.UserRepository;
import com.bi.recordmanagement.services.MessageByLocaleService;
import com.bi.recordmanagement.utils.ContextUtil;
//import com.bi.recordmanagement.ConfigurationService;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;


/*
 *
 * @author narendra
 */


@Service(value = "UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
   
//    @Autowired
//    private ConfigurationService configService;
    
    @Autowired
    private MessageByLocaleService messageByLocaleService;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
    	
    	String clientId = getClientId();
    	BooleanExpression expression = QUser.user.clientId.eq(getClientId());
    	BooleanExpression loginExpression = QUser.user.loginName.eq(username);
    	if(username.contains("@")) {
    		BooleanExpression emailExpression =QUser.user.email.eq(username);
    		expression = expression.and(loginExpression.or(emailExpression));

    	}else {
    		String strArray[] = username.split("-");
    		if(strArray == null || strArray.length != 2) {
    			throw new BadCredentialsException(messageByLocaleService.getMessage("user.not.found"));
    		}
    		
    	}

    	User user   = userRepository.findOne(expression).orElseThrow(()->  new BadCredentialsException(messageByLocaleService.getMessage("user.not.found")));

    	new AccountStatusUserDetailsChecker().check(user);
    	user.setAuthorities(getGrantedAuthorities(user.getRoles()));
    	LOGGER.info("user's authorities -> " + user.getAuthorities());
    	return user;
    }

    private  String getClientId(){
    	final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    	return request.getParameter("client_id");

    }
    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {

        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            // if you want permission here then uncommented below code so
            //that you can pass permission as a scope from client also
            role.getAuthorities().forEach(p -> {
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            });
        });

        return authorities;
    }

}

