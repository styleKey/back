package com.thekey.stylekeyserver.common.cache;

import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.repository.CoordinateLookRepository;
import com.thekey.stylekeyserver.item.entity.Item;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@DependsOn("redisTemplate") // redisTemplate 의존하도록 설정
@RequiredArgsConstructor
public class CacheInitializer {

    private final CoordinateLookRepository coordinateLookRepository;
    private final ItemRepository itemRepository;
    private final LikeItemService likeItemService;
    private final LikeCoordinateLookService likeCoordinateLookService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeCacheWithCoordinateLookLikes();
        initializeCacheWithItemLikes();
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
