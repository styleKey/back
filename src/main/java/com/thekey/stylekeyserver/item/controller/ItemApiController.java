package com.thekey.stylekeyserver.item.controller;

import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.item.service.ItemAdminService;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Item", description = "Item API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemApiController {

    private final ItemAdminService itemAdminService;
    private final CoordinateLookAdminService coordinateLookAdminService;
    private final LikeItemService likeItemService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One Item", description = "아이템 정보 단건 조회")
    public ApiResponse<ApiItemResponse> getItem(@PathVariable Long id) {
        Optional<Item> optional = Optional.ofNullable(itemAdminService.findById(id));

        return optional.map(item -> {
            Integer likeCount = likeItemService.getItemLikeCount(id);
            ApiItemResponse response = ApiItemResponse.of(item, likeCount);
            return ApiResponse.success(response);
        }).orElse(ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Read All Items", description = "아이템 정보 전체 조회")
    public ApiResponse<List<ApiItemResponse>> getItems() {
        List<Item> items = itemAdminService.findAll();
        List<ApiItemResponse> response = new ArrayList<>();

        for (Item item : items) {
            Integer likeCount = likeItemService.getItemLikeCount(item.getId());
            ApiItemResponse itemResponse = ApiItemResponse.of(item, likeCount);
            response.add(itemResponse);
        }

        return ApiResponse.success(response);
    }

    @GetMapping("/coordinate-looks/{id}")
    @Operation(summary = "Read All Items By CoordinateLookId", description = "코디룩 ID에 해당하는 아이템 목록 전체 조회")
    public ApiResponse<List<ApiItemResponse>> getItemsByCoordinateLookId(@PathVariable Long id) {
        List<Item> items = itemAdminService.findAllByCoordinateLookId(id, coordinateLookAdminService);
        List<ApiItemResponse> response = new ArrayList<>();

        for (Item item : items) {
            Integer likeCount = likeItemService.getItemLikeCount(item.getId());
            ApiItemResponse itemResponse = ApiItemResponse.of(item, likeCount);
            response.add(itemResponse);
        }

        return ApiResponse.success(response);
    }

}
