package com.thekey.stylekeyserver.like.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.ITEM_NOT_FOUND;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.common.redis.RedisService;
import com.thekey.stylekeyserver.item.entity.Item;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeItemService {

    private static final String USER_LIKES_KEY = "user:%s:item_likes";
    private static final String ITEM_LIKES_KEY = "item:%s:likes";

    private final ItemRepository itemRepository;
    private final RedisService redisService;

    public void addLikeItem(List<Long> itemIds, String userId) throws JsonProcessingException {
        String userLikesKey = getUserLikesKey(userId);
        Set<Long> userLikes = redisService.getLikeData(userLikesKey);
        if (userLikes.isEmpty()) {
            userLikes = new HashSet<>();
        }

        for (Long itemId : itemIds) {
            if (userLikes.contains(itemId)) {
                continue;
            }

            String itemLikesKey = getItemLikesKey(itemId);
            redisService.increaseLikeCount(itemLikesKey);
            userLikes.add(itemId);

            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND.getMessage()));
            item.setLikeCount(item.getLikeCount() + 1);
            itemRepository.save(item);
        }

        redisService.setLikeData(userLikesKey, userLikes);
    }

    public List<ApiItemResponse> getLikeItems(String userId) throws JsonProcessingException {
        String userLikesKey = getUserLikesKey(userId);
        Set<Long> userLikes = redisService.getLikeData(userLikesKey);

        if (userLikes.isEmpty()) {
            return Collections.emptyList();
        }

        List<Item> items = itemRepository.findAllById(userLikes);

        return items.stream()
                .map(item -> {
                    String itemLikesKey = getItemLikesKey(item.getId());
                    Integer likeCount = redisService.getLikeCount(itemLikesKey);
                    return ApiItemResponse.of(item, likeCount);
                }).collect(Collectors.toList());
    }


    public Integer getItemLikeCount(Long itemId) {
        return redisService.getLikeCount(getItemLikesKey(itemId));
    }

    public void deleteLikeItem(List<Long> itemIds, String userId) throws JsonProcessingException {
        String userLikesKey = getUserLikesKey(userId);
        Set<Long> userLikes = redisService.getLikeData(userLikesKey);

        if (!userLikes.isEmpty()) {
            for (Long itemId : itemIds) {
                String itemLikesKey = getItemLikesKey(itemId);
                redisService.decreaseLikeCount(itemLikesKey);
                userLikes.remove(itemId);

                Item item = itemRepository.findById(itemId)
                        .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND.getMessage()));
                item.setLikeCount(item.getLikeCount() - 1);
                itemRepository.save(item);
            }
        }

        redisService.setLikeData(userLikesKey, userLikes);
    }

    private static String getUserLikesKey(String userId) {
        return String.format(USER_LIKES_KEY, userId);
    }

    private static String getItemLikesKey(Long item) {
        return String.format(ITEM_LIKES_KEY, item);
    }
}
