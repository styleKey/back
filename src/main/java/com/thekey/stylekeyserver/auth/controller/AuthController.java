package com.thekey.stylekeyserver.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thekey.stylekeyserver.auth.service.AuthService;
import com.thekey.stylekeyserver.auth.service.dto.LoginByPasswordRequest;
import com.thekey.stylekeyserver.auth.service.dto.RegisterPasswordAuthRequest;
import com.thekey.stylekeyserver.common.dto.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/password/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> registerPasswordAuth(
            @RequestBody RegisterPasswordAuthRequest request
    ) {
        authService.registerPasswordAuth(request);
        return ApiResponse.ofSuccess();
    }

    @PostMapping("/password/login")
    public ApiResponse<Void> loginByPassword(
            @RequestBody LoginByPasswordRequest request,
            HttpServletRequest httpServletRequest
    ) {
        try {
            httpServletRequest.login(request.getId(), request.getPassword());
            return ApiResponse.ofSuccess();
        } catch (ServletException e) {
            return ApiResponse.ofFailure("Login failed");
        }
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletRequest httpServletRequest) throws ServletException {
        httpServletRequest.logout();
        return ApiResponse.ofSuccess();
    }
}
