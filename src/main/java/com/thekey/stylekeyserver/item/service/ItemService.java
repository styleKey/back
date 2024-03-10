package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemAdminService itemAdminService;
    private final LikeItemService likeItemService;

    public ApiItemResponse getItemWithLikes(Long id) {
        Item item = itemAdminService.findById(id);
        Integer likeCount = likeItemService.getItemLikeCount(id);
        return ApiItemResponse.of(item, likeCount);
    }

    public List<ApiItemResponse> getAllItemsWithLikes() {
        List<Item> items = itemAdminService.findAll();
        List<ApiItemResponse> response = new ArrayList<>();

        for (Item item : items) {
            Integer likeCount = likeItemService.getItemLikeCount(item.getId());
            ApiItemResponse itemResponse = ApiItemResponse.of(item, likeCount);
            response.add(itemResponse);
        }

        return response;
    }

    public List<ApiItemResponse> getItemWithLikesByCoordinateLookId(Long id) {
        List<Item> items = itemAdminService.findAllByCoordinateLookId(id);
        List<ApiItemResponse> response = new ArrayList<>();

        for (Item item : items) {
            Integer likeCount = likeItemService.getItemLikeCount(item.getId());
            ApiItemResponse itemResponse = ApiItemResponse.of(item, likeCount);
            response.add(itemResponse);
        }

        return response;
    }

}
