package com.thekey.stylekeyserver.like.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.like.dto.request.LikeItemRequest;
import com.thekey.stylekeyserver.like.service.LikeItemService;
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
public class LikeItemController {

    private final LikeItemService likeItemService;

    @Operation(description = "아이템 좋아요")
    @PostMapping("/api/items/likes")
    public ApiResponse<Void> like(@RequestBody LikeItemRequest request,
                                  @AuthenticationPrincipal UserPrincipal user) throws JsonProcessingException {
        likeItemService.addLikeItem(request.getItemIds(), user.getUserId());
        return ApiResponse.ok();
    }

    @Operation(description = "아이템 좋아요 취소")
    @DeleteMapping("/api/items/likes")
    public ApiResponse<Void> unlike(@RequestBody LikeItemRequest request,
                                    @AuthenticationPrincipal UserPrincipal user) throws JsonProcessingException {
        likeItemService.deleteLikeItem(request.getItemIds(), user.getUserId());
        return ApiResponse.ok();
    }
}
