package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.service.BrandAdminService;
import com.thekey.stylekeyserver.category.domain.Category;
import com.thekey.stylekeyserver.category.service.CategoryService;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.item.ItemErrorMessage;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemAdminServiceImpl implements ItemAdminService {

    private final ItemRepository itemRepository;
    private final BrandAdminService brandAdminService;
    private final CategoryService categoryService;

    @Override
    public Item create(ItemRequest requestDto) {
        Category category = categoryService.findById(requestDto.getCategoryId());
        Brand brand = brandAdminService.findById(requestDto.getBrandId());
        return itemRepository.save(requestDto.toEntity(brand, category));
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ItemErrorMessage.NOT_FOUND_ITEM.get() + id));
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAllByCoordinateLookId(Long id, CoordinateLookAdminService coordinateLookAdminService) {
        CoordinateLook coordinateLook = coordinateLookAdminService.findById(id);
        return itemRepository.findItemByCoordinateLook(coordinateLook);
    }

    @Override
    public Item update(Long id, ItemRequest requestDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ItemErrorMessage.NOT_FOUND_ITEM.get() + id));

        Category category = categoryService.findById(requestDto.getCategoryId());
        Brand brand = brandAdminService.findById(requestDto.getBrandId());

        item.update(requestDto.getTitle(),
                requestDto.getSales_link(),
                requestDto.getImage(),
                requestDto.toEntity(brand, category).getBrand(),
                requestDto.toEntity(brand, category).getCategory());

        return item;
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

}
