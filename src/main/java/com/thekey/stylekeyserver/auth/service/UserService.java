package com.thekey.stylekeyserver.auth.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.USER_NOT_FOUND;

import com.thekey.stylekeyserver.auth.entity.User;
import com.thekey.stylekeyserver.auth.repository.UserRepository;
import com.thekey.stylekeyserver.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId)
            .orElseThrow(() -> new ApiException(USER_NOT_FOUND));
    }
}
