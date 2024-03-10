package com.thekey.stylekeyserver.oauth.token;

import java.security.Key;
import java.util.Date;

import com.thekey.stylekeyserver.oauth.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.thekey.stylekeyserver.oauth.exception.TokenValidFailedException;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
@AllArgsConstructor
public class AuthTokenProvider {

    private final Key key;
    private final CustomUserDetailsService customUserDetailsService;

    private static final String AUTHORITIES_KEY = "role";


    public AuthTokenProvider(String secret, CustomUserDetailsService customUserDetailsService) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.customUserDetailsService = customUserDetailsService;
    }

    public AuthToken createAuthToken(String id, Date expiry) {
        return new AuthToken(id, expiry, key);
    }

    public AuthToken createAuthToken(String id, String role, Date expiry) {
        return new AuthToken(id, role, expiry, key);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public Authentication getAuthentication(AuthToken authToken) {

        if(authToken.validate()) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authToken.getTokenClaims().getSubject());
            return new UsernamePasswordAuthenticationToken(userDetails, authToken, userDetails.getAuthorities());
        } else {
            throw new TokenValidFailedException();
        }
    }

}

