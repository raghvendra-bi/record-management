package com.bi.recordmanagement.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Encoders {

    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder(8).encode("test123"));
        System.out.println(new Encoders().userPasswordEncoder().matches("test123", "$2a$08$CvI2cbNf47lH3jxLXJaA/ugQrtCN4UYFvFAaILJPbYZ7zif0.1fM."));
    }
}