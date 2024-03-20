package com.thekey.stylekeyserver.item.controller;

import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.response.ItemPageResponse;
import com.thekey.stylekeyserver.item.dto.response.ItemResponse;
import com.thekey.stylekeyserver.item.service.ItemAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Item", description = "Item API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/items")
public class ItemAdminController {

    private ItemAdminService itemAdminService;

    @Autowired
    public void setItemAdminService(ItemAdminService itemAdminService) {
        this.itemAdminService = itemAdminService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read One Item", description = "아이템 정보 단건 조회")
    public ApiResponse<ItemResponse> getItem(@PathVariable Long id) {
        Optional<Item> optional = Optional.ofNullable(itemAdminService.findById(id));

        return optional.map(item -> {
            ItemResponse response = ItemResponse.of(item);
            return ApiResponse.ok(response);
        }).orElse(ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.ERROR_BAD_REQUEST.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Read All Items", description = "아이템 정보 전체 조회")
    public ApiResponse<ItemPageResponse> getItems(
            @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
        return ApiResponse.ok(itemAdminService.findAllPaging(pageable));
    }

    @GetMapping("/coordinate-looks/{id}")
    @Operation(summary = "Read All Items By CoordinateLookId", description = "코디룩 ID에 해당하는 아이템 목록 전체 조회")
    public ApiResponse<ItemPageResponse> getItemsByCoordinateLookId(@PathVariable Long id,
                                                                    @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
        return ApiResponse.ok(itemAdminService.findAllByCoordinateLookId(id, pageable));
    }
}
