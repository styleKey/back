package com.thekey.stylekeyserver.item.controller;

import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.item.service.ItemService;
import com.thekey.stylekeyserver.item.service.RecentlyViewedItemsService;
import com.thekey.stylekeyserver.oauth.entity.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Item", description = "Item API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemApiController {

    private final ItemService itemService;
    private final RecentlyViewedItemsService viewedItemsService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One Item", description = "아이템 정보 단건 조회")
    public ApiResponse<ApiItemResponse> getItem(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal user) {
        viewedItemsService.addViewItem(id, user.getUserId());
        return ApiResponse.ok(itemService.getItemWithLikes(id));
    }

    @GetMapping
    @Operation(summary = "Read All Items", description = "아이템 정보 전체 조회")
    public ApiResponse<List<ApiItemResponse>> getItems() {
        return ApiResponse.ok(itemService.getAllItemsWithLikes());
    }

    @GetMapping("/coordinate-looks/{id}")
    @Operation(summary = "Read All Items By CoordinateLookId", description = "코디룩 ID에 해당하는 아이템 목록 전체 조회")
    public ApiResponse<List<ApiItemResponse>> getItemsByCoordinateLookId(@PathVariable Long id) {
        return ApiResponse.ok(itemService.getItemWithLikesByCoordinateLookId(id));
    }
}
