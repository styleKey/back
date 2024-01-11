package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.ItemDto;
import java.util.List;

public interface ItemAdminService {

    Item create(ItemDto requestDto);

    Item findById(Long id);

    List<Item> findAll();
    List<Item> findAllByCoordinateLookId(Long id, CoordinateLookAdminService coordinateLookAdminService);

    Item update(Long id, ItemDto requestDto);

    void delete(Long id);

    ItemDto convertToDto(Item item);
}
