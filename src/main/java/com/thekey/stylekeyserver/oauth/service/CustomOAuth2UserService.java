package com.thekey.stylekeyserver.oauth.service;

import lombok.RequiredArgsConstructor;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekey.stylekeyserver.auth.entity.User;
import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.oauth.entity.ProviderType;
import com.thekey.stylekeyserver.oauth.entity.RoleType;
import com.thekey.stylekeyserver.oauth.entity.UserPrincipal;
import com.thekey.stylekeyserver.oauth.exception.OAuthProviderMissMatchException;
import com.thekey.stylekeyserver.oauth.info.OAuth2UserInfo;
import com.thekey.stylekeyserver.oauth.info.OAuth2UserInfoFactory;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        try {
            ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
            OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
            User savedUser = userRepository.findByUserId(userInfo.getId());

            if (savedUser != null) {
                if (providerType != savedUser.getProviderType()) {
                    throw new OAuthProviderMissMatchException(
                            "Looks like you're signed up with " + providerType +
                                    " account. Please use your " + savedUser.getProviderType() + " account to login."
                    );
                }
                updateUser(savedUser, userInfo);
            } else {
                savedUser = createUser(userInfo, providerType);
            }

            return UserPrincipal.create(savedUser, user.getAttributes());
        } catch (Exception ex) {
            log.debug("Exception during processing OAuth2 user", ex);
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }


    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(
                userInfo.getId(),
                userInfo.getName(),
                userInfo.getEmail(),
                "Y",
                userInfo.getImageUrl(),
                providerType,
                RoleType.USER,
                now,
                now
        );

        return userRepository.saveAndFlush(user);
    }

    private User updateUser(User user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getUsername().equals(userInfo.getName())) {
            user.setUsername(userInfo.getName());
        }

        if (userInfo.getImageUrl() != null && !user.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.setProfileImageUrl(userInfo.getImageUrl());
        }

        // userRepository.saveAndFlush(user);
        return user;
    }
}

