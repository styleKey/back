package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.item.dto.response.ApiItemPageResponse;
import com.thekey.stylekeyserver.item.entity.Item;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemAdminService itemAdminService;
    private final LikeItemService likeItemService;
    private final ItemRepository itemRepository;

    public ApiItemResponse getItemWithLikes(Long id) {
        Item item = itemAdminService.findById(id);
        return buildApiItemResponse(item);
    }

    public ApiItemPageResponse getAllItemsWithLikes(Pageable pageable) {
        Slice<Item> items = itemRepository.findAll(pageable);
        List<ApiItemResponse> responses = items.getContent().stream()
                .map(this::buildApiItemResponse)
                .toList();

        return ApiItemPageResponse.fromSlice(responses, items.hasNext());
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