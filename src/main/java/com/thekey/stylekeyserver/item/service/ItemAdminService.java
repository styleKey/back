package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ItemAdminService {

    Item create(ItemRequest requestDto, MultipartFile imageFile);

    Item findById(Long id);

    List<Item> findAll();
    List<Item> findAllByCoordinateLookId(Long id);

    Item update(Long coordinateLookId, Long itemId, ItemRequest requestDto, MultipartFile imageFile);

    void delete(Long id);

}
