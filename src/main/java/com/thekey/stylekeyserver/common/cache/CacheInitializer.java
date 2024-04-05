package com.thekey.stylekeyserver.common.cache;

import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.repository.CoordinateLookRepository;
import com.thekey.stylekeyserver.item.entity.Item;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheInitializer implements SmartLifecycle {

    private final CoordinateLookRepository coordinateLookRepository;
    private final ItemRepository itemRepository;
    private final LikeItemService likeItemService;
    private final LikeCoordinateLookService likeCoordinateLookService;

    private boolean isRunning = false;

    // CacheInitializer의 초기화가 필요한 의존성이 모두 준비된 후에 실행
    @Override
    public void start() {
        initializeCacheWithCoordinateLookLikes();
        initializeCacheWithItemLikes();
        this.isRunning = true;
    }

    @Override
    public void stop() {
        this.isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    // 컴포넌트 시작 순서를 결정
    // Redis와 같은 의존성이 있는 컴포넌트가 먼저 시작되도록 낮은 값을 설정
    @Override
    public int getPhase() {
        return Integer.MIN_VALUE + 1;
    }

    private void initializeCacheWithCoordinateLookLikes() {
        List<CoordinateLook> coordinateLooks = coordinateLookRepository.findAll();

        coordinateLooks.forEach(coordinateLook -> {
            Long coordinateLookId = coordinateLook.getId();
            int likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(coordinateLookId);

            coordinateLook.setLikeCount(likeCount);
            coordinateLookRepository.save(coordinateLook);
        });
    }

    private void initializeCacheWithItemLikes() {
        List<Item> items = itemRepository.findAll();

        items.forEach(item -> {
            Long itemId = item.getId();
            int likeCount = likeItemService.getItemLikeCount(itemId);

            item.setLikeCount(likeCount);
            itemRepository.save(item);
        });
    }
}
