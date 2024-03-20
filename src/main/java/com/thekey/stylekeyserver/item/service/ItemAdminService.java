package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import com.thekey.stylekeyserver.item.dto.response.ItemPageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ItemAdminService {

    Item create(ItemRequest requestDto, MultipartFile imageFile);

    Item findById(Long id);

    List<Item> findAll();

    ItemPageResponse findAllPaging(Pageable pageable);

    List<Item> findAllByCoordinateLookId(Long id);
    ItemPageResponse findAllByCoordinateLookId(Long coordinateLookId, Pageable pageable);

    Item update(Long coordinateLookId, Long itemId, ItemRequest requestDto, MultipartFile imageFile);

    void delete(Long id);

}
