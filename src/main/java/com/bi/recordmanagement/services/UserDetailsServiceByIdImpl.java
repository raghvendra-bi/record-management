package com.bi.recordmanagement.services;

import com.bi.recordmanagement.models.Role;
import com.bi.recordmanagement.models.User;
import com.bi.recordmanagement.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@Api(value = "CRUD Operation for UserService")
@Qualifier("UserDetailsServiceByIdImpl")
public class UserDetailsServiceByIdImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(username)).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        user.setAuthorities(getGrantedAuthorities(user.getRoles()));
        return user;

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