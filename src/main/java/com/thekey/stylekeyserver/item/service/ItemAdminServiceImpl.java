package com.thekey.stylekeyserver.item.service;

import com.thekey.stylekeyserver.brand.domain.Brand;
import com.thekey.stylekeyserver.brand.service.BrandAdminService;
import com.thekey.stylekeyserver.category.domain.Category;
import com.thekey.stylekeyserver.category.service.CategoryService;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.image.domain.Image;
import com.thekey.stylekeyserver.image.domain.Type;
import com.thekey.stylekeyserver.image.repository.ImageRepository;
import com.thekey.stylekeyserver.image.service.ImageService;
import com.thekey.stylekeyserver.item.ItemErrorMessage;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import com.thekey.stylekeyserver.common.s3.service.S3Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ItemAdminServiceImpl implements ItemAdminService {

    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;
    private final BrandAdminService brandAdminService;
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final S3Service s3Service;
    private CoordinateLookAdminService coordinateLookAdminService;

    @Autowired
    public void setCoordinateLookAdminService(@Lazy CoordinateLookAdminService coordinateLookAdminService) {
        this.coordinateLookAdminService = coordinateLookAdminService;
    }

    @Override
    @Transactional
    public Item create(ItemRequest requestDto, MultipartFile imageFile) throws IOException {
        Image image = s3Service.uploadFile(imageFile, Type.ITEM);
        imageRepository.save(image);
        Category category = categoryService.findById(requestDto.getCategoryId());
        Brand brand = brandAdminService.findById(requestDto.getBrandId());

        Item item = requestDto.toEntity(brand, category);
        item.setImage(image);

        return itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ItemErrorMessage.NOT_FOUND_ITEM.get() + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAllByCoordinateLookId(Long coordinateLookId) {
        CoordinateLook coordinateLook = coordinateLookAdminService.findById(coordinateLookId);
        return itemRepository.findItemByCoordinateLook(coordinateLook);
    }

    @Override
    @Transactional
    public Item update(Long coordinateLookId, Long itemId, ItemRequest requestDto, MultipartFile imageFile)
            throws IOException {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(ItemErrorMessage.NOT_FOUND_ITEM.get() + itemId));

        if (!itemRepository.existsByCoordinateLookId(coordinateLookId)) {
            throw new IllegalArgumentException(ItemErrorMessage.NOT_FOUND_ITEM.get());
        }

        Category category = categoryService.findById(requestDto.getCategoryId());
        Brand brand = brandAdminService.findById(requestDto.getBrandId());

        if (!imageFile.isEmpty()) {
            Image oldImage = item.getImage();
            if (oldImage != null) {
                oldImage.setUnused();
                imageRepository.save(oldImage);
                imageService.deleteUnusedImages();

                Image newImage = s3Service.uploadFile(imageFile, Type.ITEM);
                imageRepository.save(newImage);
                item.setImage(newImage);
                itemRepository.save(item);
            }
        }

        item.update(requestDto.getTitle(),
                requestDto.getSales_link(),
                brand,
                category);

        return item;
    }

    @Override
    @Transactional
    public void delete(Long id) throws MalformedURLException, UnsupportedEncodingException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ItemErrorMessage.NOT_FOUND_ITEM.get() + id));

        Image image = item.getImage();

        if (image != null) {
            image.setUnused();
            imageRepository.save(image);
            imageService.deleteUnusedImages();
        }
        itemRepository.deleteById(id);
    }

}
