package com.thekey.stylekeyserver.like.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.like.dto.request.LikeItemRequest;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeItemController {

    private final LikeItemService likeItemService;

    @Operation(description = "아이템 좋아요")
    @PostMapping("/api/items/likes")
    public ApiResponse<Void> like(@RequestBody LikeItemRequest request) throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        likeItemService.addLikeItem(request.getItemIds(), userId);
        return ApiResponse.success();
    }

    @Operation(description = "사용자가 좋아요한 아이템 목록 조회")
    @GetMapping("/api/items/likes")
    public ApiResponse<List<ApiItemResponse>> getLikeItems() throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ApiResponse.success(likeItemService.getLikeItems(userId));
    }

    @Operation(description = "아이템 좋아요 취소")
    @DeleteMapping("/api/items/likes")
    public ApiResponse<Void> unlike(@RequestBody LikeItemRequest request) throws JsonProcessingException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        likeItemService.deleteLikeItem(request.getItemIds(), userId);
        return ApiResponse.success();
    }
}
