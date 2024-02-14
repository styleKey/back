package com.thekey.stylekeyserver.auth.service;

import org.springframework.stereotype.Service;

import com.thekey.stylekeyserver.auth.domain.OAuthTokenGenerator;
import com.thekey.stylekeyserver.auth.domain.Users;
import com.thekey.stylekeyserver.auth.domain.oauth.OAuthInfoResponse;
import com.thekey.stylekeyserver.auth.domain.oauth.OAuthLoginParams;
import com.thekey.stylekeyserver.auth.domain.oauth.RequestOAuthInfoService;
import com.thekey.stylekeyserver.auth.dto.response.OAuthTokens;
import com.thekey.stylekeyserver.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final OAuthTokenGenerator oAuthTokenGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;
    
    public OAuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return oAuthTokenGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Users::getId)
                .orElseGet(() -> newUsers(oAuthInfoResponse));
    }

    private Long newUsers(OAuthInfoResponse oAuthInfoResponse) {
        Users users = Users.builder()
                    .email(oAuthInfoResponse.getEmail())
                    .name(oAuthInfoResponse.getNickname())
                    .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                    .build();
        
        return userRepository.save(users).getId();
    }
}
