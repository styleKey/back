package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.common.redis.RedisService;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.response.ApiItemResponse;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecentlyViewedItemsService {

    private static final String USER_VIEW_ITEMS = "user:%s:view_items";
    private static final long SECONDS_IN_A_WEEK = 7L * 24 * 60 * 60;    // 최근 7일 동안 저장
    private static final int MAX_COUNT = 30;    // 최대 30개
    private static final double MILLISECONDS_TO_SECONDS = 1000.0;

    private final ItemRepository itemRepository;
    private final RedisService redisService;
    private final LikeItemService likeItemService;

    public void addViewItem(Long itemId, String userId) {
        String userViewedKey = getUserViewedKey(userId);
        double score = getCurrentTimeInSeconds(); // 현재 시간을 score로 사용
        redisService.setViewData(userViewedKey, String.valueOf(itemId), score);
    }

    public List<ApiItemResponse> getViewItems(String userId) {
        String userViewedKey = getUserViewedKey(userId);
        double minScore = getCurrentTimeInSeconds() - SECONDS_IN_A_WEEK;
        // 가장 오래된 아이템부터 (총 개수 - 30)개 제거
        Set<Long> itemIds = redisService.getViewData(userViewedKey, minScore, MAX_COUNT);

        // 데이터베이스에서 아이템 조회
        List<Item> items = itemIds.stream()
                .map(itemId -> itemRepository.findById(itemId)
                        .orElseThrow(() -> new EntityNotFoundException("해당 아이템이 존재하지 않습니다.")))
                .toList();

        // Redis에서 가져온 순서대로 아이템 정렬
        List<Item> sortedItems = itemIds.stream()
                .map(itemId -> items.stream()
                        .filter(item -> item.getId().equals(itemId))
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();

        return sortedItems.stream()
                .map(item -> ApiItemResponse.of(item, item.getLikeCount()))
                .collect(Collectors.toList());
    }

    private static String getUserViewedKey(String userId) {
        return String.format(USER_VIEW_ITEMS, userId);
    }

    private double getCurrentTimeInSeconds() {
        return System.currentTimeMillis() / MILLISECONDS_TO_SECONDS;
    }

}
