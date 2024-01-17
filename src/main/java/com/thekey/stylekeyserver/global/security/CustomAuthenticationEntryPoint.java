package com.thekey.stylekeyserver.global.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;
import com.thekey.stylekeyserver.global.response.ErrorType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorType exception = (ErrorType) request.getAttribute("exception");

        if(exception.equals(ErrorType.NOT_VALID_REQUEST)){
            exceptionHandler(response, ErrorType.NOT_VALID_REQUEST);
            return;
        }

        if (exception.equals(ErrorType.TOKEN_NOT_FOUND)) {
            exceptionHandler(response, ErrorType.TOKEN_NOT_FOUND);
            return;
        }

        if (exception.equals(ErrorType.NOT_VALID_TOKEN)) {
            exceptionHandler(response, ErrorType.NOT_VALID_TOKEN);
            return;
        }

        if (exception.equals(ErrorType.USER_NOT_FOUND)) {
            exceptionHandler(response, ErrorType.USER_NOT_FOUND);
        }
    }

    public void exceptionHandler(HttpServletResponse response, ErrorType error) {
        response.setStatus(error.getStatusCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ApiResponseDto.of(error));
            response.getWriter().write(json);
            log.error(error.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

