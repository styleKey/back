package com.thekey.stylekeyserver.auth.controller;

import com.thekey.stylekeyserver.oauth.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekey.stylekeyserver.auth.entity.User;
import com.thekey.stylekeyserver.auth.service.UserService;
import com.thekey.stylekeyserver.common.exception.ApiResponse;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public ApiResponse getUser(@AuthenticationPrincipal UserPrincipal user) {
        User userDetails = userService.getUser(user.getUsername());
        return ApiResponse.success(userDetails);
    }
}
