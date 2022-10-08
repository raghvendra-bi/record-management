package com.bi.recordmanagement.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bi.recordmanagement.models.User;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        User u = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userId != null) {
            try {
                long userIdL = Long.parseLong(userId);
                u = new User();
                u.setId(userIdL);
            } catch (NumberFormatException e) {

            }
        }
        return Optional.ofNullable(u);
    }
}