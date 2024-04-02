package com.thekey.stylekeyserver.item.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.ITEM_NOT_FOUND;

import com.thekey.stylekeyserver.brand.entity.Brand;
import com.thekey.stylekeyserver.brand.service.BrandAdminService;
import com.thekey.stylekeyserver.category.entity.Category;
import com.thekey.stylekeyserver.category.service.CategoryService;
import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.image.entity.Image;
import com.thekey.stylekeyserver.image.entity.Type;
import com.thekey.stylekeyserver.image.repository.ImageRepository;
import com.thekey.stylekeyserver.image.service.ImageService;
import com.thekey.stylekeyserver.item.entity.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import com.thekey.stylekeyserver.item.dto.response.ItemPageResponse;
import com.thekey.stylekeyserver.item.dto.response.ItemResponse;
import com.thekey.stylekeyserver.item.repository.ItemRepository;
import com.thekey.stylekeyserver.common.s3.service.S3Service;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Item create(ItemRequest requestDto, MultipartFile itemImageFile) {
        Image image = s3Service.uploadFile(itemImageFile, Type.ITEM);
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
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND.getMessage() + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ItemPageResponse findAllPaging(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        return createItemPageResponse(itemPage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAllByCoordinateLookId(Long coordinateLookId) {
        CoordinateLook coordinateLook = coordinateLookAdminService.findById(coordinateLookId);
        return itemRepository.findItemByCoordinateLook(coordinateLook);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemPageResponse findAllByCoordinateLookId(Long coordinateLookId, Pageable pageable) {
        CoordinateLook coordinateLook = coordinateLookAdminService.findById(coordinateLookId);
        Page<Item> itemPage = itemRepository.findItemByCoordinateLook(coordinateLook, pageable);

        return createItemPageResponse(itemPage);
    }

    @Override
    @Transactional
    public Item update(Long coordinateLookId, Long itemId, ItemRequest requestDto, MultipartFile itemImageFile) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND.getMessage() + itemId));

        if (!itemRepository.existsByCoordinateLookId(coordinateLookId)) {
            throw new IllegalArgumentException(ITEM_NOT_FOUND.getMessage());
        }

        Category category = categoryService.findById(requestDto.getCategoryId());
        Brand brand = brandAdminService.findById(requestDto.getBrandId());

        // 수정 할 이미지가 요청값에 포함 되어있을 때만 기존 이미지에서 수정할 이미지로 변경
        if (itemImageFile != null && !itemImageFile.isEmpty()) {
            Image oldImage = item.getImage();
            if (oldImage != null) {
                oldImage.setUnused();
                imageRepository.save(oldImage);
                imageService.deleteUnusedImages();

                Image newImage = s3Service.uploadFile(itemImageFile, Type.ITEM);
                imageRepository.save(newImage);
                item.setImage(newImage);
                itemRepository.save(item);
            }
        }

        // 수정 할 이미지가 없다면 기본 정보만 변경
        item.update(requestDto.getTitle(),
                requestDto.getSales_link(),
                brand,
                category);

        return item;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND.getMessage() + id));

        Image image = item.getImage();

        if (image != null) {
            image.setUnused();
            imageRepository.save(image);
            imageService.deleteUnusedImages();
        }
        itemRepository.deleteById(id);
    }

    private ItemPageResponse createItemPageResponse(Page<Item> itemPage) {
        List<ItemResponse> itemResponses = itemPage.getContent().stream()
                .map(ItemResponse::of)
                .toList();
        return ItemPageResponse.of(itemResponses, itemPage);
    }

}
