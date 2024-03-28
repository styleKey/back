package com.thekey.stylekeyserver.common.security;

import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.oauth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thekey.stylekeyserver.oauth.token.AuthTokenProvider;
import org.springframework.context.annotation.Primary;


@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Bean("jwtCustomUserDetailsService")
    @Primary
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public AuthTokenProvider jwtProvider(@Qualifier("jwtCustomUserDetailsService") CustomUserDetailsService customUserDetailsService) {
        return new AuthTokenProvider(secret, customUserDetailsService);
    }

}
