package com.thekey.stylekeyserver.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thekey.stylekeyserver.auth.dto.request.AuthRequestDto;
import com.thekey.stylekeyserver.auth.service.AuthService;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

   @PostMapping("/sign-up")
    public ResponseEntity <ApiResponseDto> signUp (@RequestBody @Valid AuthRequestDto authRequestDto){
        return authService.signUp(authRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity <ApiResponseDto> login (@RequestBody @Valid AuthRequestDto authRequestDto,
                                                     HttpServletResponse response){
        return authService.login(authRequestDto,response);
    }

   
}
