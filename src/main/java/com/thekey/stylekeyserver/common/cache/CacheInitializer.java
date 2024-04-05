package com.thekey.stylekeyserver.common.cache;

import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.repository.CoordinateLookRepository;
import com.thekey.stylekeyserver.item.entity.Item;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import com.thekey.stylekeyserver.like.service.LikeItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheInitializer {

    private final CoordinateLookRepository coordinateLookRepository;
    private final ItemRepository itemRepository;
    private final LikeItemService likeItemService;
    private final LikeCoordinateLookService likeCoordinateLookService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeCacheWithCoordinateLookLikes(ApplicationReadyEvent event) {
        List<CoordinateLook> coordinateLooks = coordinateLookRepository.findAll();

        coordinateLooks.forEach(coordinateLook -> {
            Long coordinateLookId = coordinateLook.getId();
            int likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(coordinateLookId);

            coordinateLook.setLikeCount(likeCount);
            coordinateLookRepository.save(coordinateLook);
        });
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeCacheWithItemLikes(ApplicationReadyEvent event) {
        List<Item> items = itemRepository.findAll();

        items.forEach(item -> {
            Long itemId = item.getId();
            int likeCount = likeItemService.getItemLikeCount(itemId);

            item.setLikeCount(likeCount);
            itemRepository.save(item);
        });
    }

}
