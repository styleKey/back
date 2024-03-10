package com.thekey.stylekeyserver.like.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.like.dto.request.LikeCoordinateLookRequest;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.oauth.entity.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeCoordinateLookController {

    private final LikeCoordinateLookService likeCoordinateLookService;

    @Operation(summary = "코디룩 좋아요")
    @PostMapping("/api/coordinate-looks/likes")
    public ApiResponse<Void> like(@RequestBody LikeCoordinateLookRequest request,
                                  @AuthenticationPrincipal UserPrincipal user) throws JsonProcessingException {
        likeCoordinateLookService.addLikeCoordinateLook(request.getCoordinateLookIds(), user.getUserId());
        return ApiResponse.success();
    }

    @Operation(summary = "코디룩 좋아요 취소")
    @DeleteMapping("/api/coordinate-looks/likes")
    public ApiResponse<Void> unlike(@RequestBody LikeCoordinateLookRequest request,
                                    @AuthenticationPrincipal UserPrincipal user) throws JsonProcessingException {
        likeCoordinateLookService.deleteLikeCoordinateLook(request.getCoordinateLookIds(), user.getUserId());
        return ApiResponse.success();
    }
}
