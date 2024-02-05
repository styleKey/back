package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import java.util.List;

public interface ItemAdminService {

    Item create(ItemRequest requestDto);

    Item findById(Long id);

    List<Item> findAll();
    List<Item> findAllByCoordinateLookId(Long id, CoordinateLookAdminService coordinateLookAdminService);

    Item update(Long id, ItemRequest requestDto);

    void delete(Long id);

}
