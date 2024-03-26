package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemAdminService itemAdminService;
    private final LikeItemService likeItemService;

    public ApiItemResponse getItemWithLikes(Long id) {
        Item item = itemAdminService.findById(id);
        return buildApiItemResponse(item);
    }

    public List<ApiItemResponse> getAllItemsWithLikes() {
        return itemAdminService.findAll().stream()
                .map(this::buildApiItemResponse)
                .collect(Collectors.toList());
    }

    public List<ApiItemResponse> getItemWithLikesByCoordinateLookId(Long id) {
        return itemAdminService.findAllByCoordinateLookId(id).stream()
                .map(this::buildApiItemResponse)
                .collect(Collectors.toList());
    }

    private ApiItemResponse buildApiItemResponse(Item item) {
        Integer likeCount = likeItemService.getItemLikeCount(item.getId());
        return ApiItemResponse.of(item, likeCount);
    }
}
