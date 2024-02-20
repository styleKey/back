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

import com.thekey.stylekeyserver.auth.dto.request.AuthReissueRequestDto;
import com.thekey.stylekeyserver.auth.dto.request.AuthRequestDto;
import com.thekey.stylekeyserver.auth.service.AuthService;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth", description = "Auth API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

   @PostMapping("/sign-up")
   @Operation(summary = "sign-up", description = "회원 가입 API입니다.")
    public ResponseEntity <ApiResponseDto> signUp (@RequestBody @Valid AuthRequestDto authRequestDto){
        return authService.signUp(authRequestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "login", description = "로그인 API로 access_token과 refresh_token이 발급됩니다.")
    public ResponseEntity <ApiResponseDto> login (@RequestBody @Valid AuthRequestDto authRequestDto,
                                                     HttpServletResponse response){
        return authService.login(authRequestDto,response);
    }

    @PostMapping("/re-issue")
    @Operation(summary = "re-issue", description = "토큰 재발급 시 access_token, refresh_token에서 Bearer와 공백은 제거하고 요청해주세요.")
    public ResponseEntity <ApiResponseDto> reissueToken(@RequestBody @Valid AuthReissueRequestDto authReissueRequestDto, HttpServletResponse response) {
        return authService.reissueToken(authReissueRequestDto, response);
    }
   
}