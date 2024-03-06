package com.thekey.stylekeyserver.like.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.like.dto.request.LikeCoordinateLookRequest;
import com.thekey.stylekeyserver.like.dto.response.LikeCoordinateLookResponse;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeCoordinateLookController {

    private final LikeCoordinateLookService likeCoordinateLookService;

    @Operation(summary = "코디룩 좋아요")
    @PostMapping("/api/coordinate-looks/likes")
    public ApiResponse<Void> like(@RequestBody LikeCoordinateLookRequest request)
            throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("유저 정보: " + userId);
        likeCoordinateLookService.addLikeCoordinateLook(request.getCoordinateLookIds(), userId);
        return ApiResponse.success();
    }

    @Operation(summary = "사용자가 좋아요한 코디룩 목록 조회")
    @GetMapping("/api/users/likes/coordinate-looks")
    public ApiResponse<List<LikeCoordinateLookResponse>> getLikeCoordinateLooks() throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ApiResponse.success(likeCoordinateLookService.getLikeCoordinateLooks(userId));
    }

    @Operation(summary = "코디룩 별 좋아요 개수 조회")
    @GetMapping("/api/coordinate-looks/{id}/likes/count")
    public ApiResponse getLikeCount(@PathVariable Long id) {
        return ApiResponse.success(likeCoordinateLookService.getLikeCount(id));
    }

    @Operation(summary = "코디룩 좋아요 취소")
    @DeleteMapping("/api/coordinate-looks/likes")
    public ApiResponse<Void> unlike(@RequestBody LikeCoordinateLookRequest request) throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        likeCoordinateLookService.deleteLikeCoordinateLook(request.getCoordinateLookIds(), userId);
        return ApiResponse.success();
    }
}
