package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ItemAdminService {

    Item create(ItemRequest requestDto, MultipartFile imageFile) throws IOException;

    Item findById(Long id);

    List<Item> findAll();
    List<Item> findAllByCoordinateLookId(Long id, CoordinateLookAdminService coordinateLookAdminService);

    Item update(Long coordinateLookId, Long itemId, ItemRequest requestDto, MultipartFile imageFile) throws IOException;

    void delete(Long id) throws MalformedURLException, UnsupportedEncodingException;

}
