package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.service.BrandAdminService;
import com.thekey.stylekeyserver.category.domain.Category;
import com.thekey.stylekeyserver.category.service.CategoryService;
import com.thekey.stylekeyserver.item.ItemErrorMessage;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.ItemDto;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
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
    public Item create(ItemDto requestDto) {
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
    public Item update(Long id, ItemDto requestDto) {
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

    @Override
    public ItemDto convertToDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .sales_link(item.getSales_link())
                .image(item.getImage())
                .brandId(item.getBrand().getId())
                .categoryId(item.getCategory().getId())
                .build();
    }
}
