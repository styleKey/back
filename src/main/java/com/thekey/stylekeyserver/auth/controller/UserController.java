package com.thekey.stylekeyserver.auth.controller;

import com.thekey.stylekeyserver.oauth.entity.UserPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookResponse;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
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
    private final LikeCoordinateLookService likeCoordinateLookService;
    private final LikeItemService likeItemService;

    @GetMapping
    public ApiResponse getUser(@AuthenticationPrincipal UserPrincipal user) {
        User userDetails = userService.getUser(user.getUsername());
        return ApiResponse.success(userDetails);
    }

    @Operation(summary = "사용자가 좋아요한 코디룩 목록 조회")
    @GetMapping("/likes/coordinate-looks")
    public ApiResponse<List<ApiCoordinateLookResponse>> getLikeCoordinateLooks() throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ApiResponse.success(likeCoordinateLookService.getLikeCoordinateLooks(userId));
    }

    @Operation(summary = "사용자가 좋아요한 아이템 목록 조회")
    @GetMapping("/likes/items")
    public ApiResponse<List<ApiItemResponse>> getLikeItems() throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ApiResponse.success(likeItemService.getLikeItems(userId));
    }
}
