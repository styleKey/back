package com.thekey.stylekeyserver.item.controller;

import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.response.ItemResponse;
import com.thekey.stylekeyserver.item.service.ItemAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    @Operation(summary = "Read One Item", description = "아이템 정보 단건 조회")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
        Optional<Item> optional = Optional.ofNullable(itemAdminService.findById(id));

        return optional
                .map(item -> {
                    ItemResponse response = ItemResponse.of(item);
                    return ResponseEntity.ok(response);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Read All Items", description = "아이템 정보 전체 조회")
    public ResponseEntity<List<ItemResponse>> getItems() {
        List<Item> items = itemAdminService.findAll();

        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ItemResponse> responses = items.stream()
                .map(ItemResponse::of)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/coordinate-looks/{id}")
    @Operation(summary = "Read All Items By CoordinateLookId", description = "코디룩 ID에 해당하는 아이템 목록 전체 조회")
    public ResponseEntity<List<ItemResponse>> getItemsByCoordinateLookId(@PathVariable Long id) {
        List<Item> items = itemAdminService.findAllByCoordinateLookId(id, coordinateLookAdminService);

        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ItemResponse> responses = items.stream()
                .map(ItemResponse::of)
                .toList();

        return ResponseEntity.ok(responses);
    }

}
