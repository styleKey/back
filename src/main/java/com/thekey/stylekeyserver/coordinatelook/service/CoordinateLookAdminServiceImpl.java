package com.thekey.stylekeyserver.coordinatelook.service;

import com.thekey.stylekeyserver.coordinatelook.CoordinateLookErrorMessage;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.request.CoordinateLookRequest;
import com.thekey.stylekeyserver.coordinatelook.repository.CoordinateLookRepository;
import com.thekey.stylekeyserver.image.domain.Image;
import com.thekey.stylekeyserver.image.domain.Type;
import com.thekey.stylekeyserver.image.repository.ImageRepository;
import com.thekey.stylekeyserver.image.service.ImageService;
import com.thekey.stylekeyserver.item.ItemErrorMessage;
import com.thekey.stylekeyserver.item.domain.Item;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import com.thekey.stylekeyserver.item.service.ItemAdminService;
import com.thekey.stylekeyserver.common.s3.S3ErrorMessage;
import com.thekey.stylekeyserver.common.s3.S3Service;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.service.StylePointAdminService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CoordinateLookAdminServiceImpl implements CoordinateLookAdminService {

    private final CoordinateLookRepository coordinateLookRepository;
    private final StylePointAdminService stylePointAdminService;
    private final ItemAdminService itemAdminService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final S3Service s3Service;

    @Override
    @Transactional
    public CoordinateLook create(CoordinateLookRequest requestDto,
                                 MultipartFile coordinateLookImageFile,
                                 List<MultipartFile> itemImageFiles) throws Exception {

        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());
        CoordinateLook coordinateLook = requestDto.toEntity(stylePoint);

        List<ItemRequest> itemRequests = requestDto.getItems();
        Map<Long, MultipartFile> itemImageFilesMap = new HashMap<>();

        // Map에 아이템 ID와 이미지 파일을 매핑
        for (MultipartFile file : itemImageFiles) {
            Long imageId = getItemIdFromFileName(file);
            itemImageFilesMap.put(imageId, file);
        }

        List<Item> items = new ArrayList<>();
        for (ItemRequest itemDto : itemRequests) {
            Long itemId = itemDto.getId();
            MultipartFile itemImageFile = itemImageFilesMap.get(itemId);

            if (itemImageFile == null) {
                throw new IllegalArgumentException(S3ErrorMessage.NO_FILE_ITEM_ID.getMessage() + itemId);
            }

            Item item = itemAdminService.create(itemDto, itemImageFile);
            items.add(item);
        }

        Image coordinateImage = s3Service.uploadFile(coordinateLookImageFile, Type.COORDINATE_LOOK);
        imageRepository.save(coordinateImage);
        coordinateLook.setImage(coordinateImage);

        CoordinateLook savedCoordinateLook = coordinateLookRepository.save(coordinateLook);

        for (Item item : items) {
            savedCoordinateLook.addItem(item);
        }
        return savedCoordinateLook;
    }

    @Override
    @Transactional(readOnly = true)
    public CoordinateLook findById(Long id) {
        return coordinateLookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        CoordinateLookErrorMessage.NOT_FOUND_COORDINATE_LOOK.get() + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoordinateLook> findAll() {
        return coordinateLookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoordinateLook> findByStylePointId(Long id) {
        return coordinateLookRepository.findCoordinateLookByStylePointId(id);
    }

    @Override
    @Transactional
    public CoordinateLook update(Long id, CoordinateLookRequest requestDto,
                                 MultipartFile coordinateLookImageFile) throws IOException {

        CoordinateLook coordinateLook = coordinateLookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        CoordinateLookErrorMessage.NOT_FOUND_COORDINATE_LOOK.get() + id));
        StylePoint stylePoint = stylePointAdminService.findById(requestDto.getStylePointId());

        if (!coordinateLookImageFile.isEmpty()) {
            Image coordinateLookOldImage = coordinateLook.getImage();

            if (coordinateLookOldImage != null) {
                coordinateLookOldImage.setUnused();
                imageRepository.save(coordinateLookOldImage);
                imageService.deleteUnusedImages();

                Image newImage = s3Service.uploadFile(coordinateLookImageFile, Type.COORDINATE_LOOK);
                imageRepository.save(newImage);

                coordinateLook.setImage(newImage);
                coordinateLookRepository.save(coordinateLook);
            }
        }

        coordinateLook.update(
                requestDto.getTitle(),
                stylePoint);
        return coordinateLook;
    }

    @Override
    @Transactional
    public CoordinateLook updateItem(Long coordinateLookId, Long itemId, ItemRequest requestDto,
                                     MultipartFile itemImageFile) throws IOException {

        CoordinateLook coordinateLook = coordinateLookRepository.findById(coordinateLookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CoordinateLookErrorMessage.NOT_FOUND_COORDINATE_LOOK.get() + coordinateLookId));
        itemAdminService.update(coordinateLookId, itemId, requestDto, itemImageFile);
        return coordinateLook;
    }

    @Override
    @Transactional
    public void delete(Long id) throws MalformedURLException, UnsupportedEncodingException {
        CoordinateLook coordinateLook = coordinateLookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CoordinateLookErrorMessage.NOT_FOUND_COORDINATE_LOOK.get() + id));

        List<Item> items = coordinateLook.getItems();
        for(Item item : items) {
            Image image = item.getImage();
            if(image != null) {
                image.setUnused();
                imageRepository.save(image);
            }
        }

        Image image = coordinateLook.getImage();

        if(image != null) {
            image.setUnused();
            imageRepository.save(image);
        }
        imageService.deleteUnusedImages();
        coordinateLookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteItemFromCoordinateLook(Long coordinateLookId, Long itemId) {
        CoordinateLook coordinateLook = coordinateLookRepository.findById(coordinateLookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CoordinateLookErrorMessage.NOT_FOUND_COORDINATE_LOOK.get() + coordinateLookId));

        List<Item> items = coordinateLook.getItems();

        try {
            Item itemToDelete = items.stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst()
                    .orElseThrow();

            itemAdminService.delete(itemToDelete.getId());
            items.remove(itemToDelete);

            // 코디룩에 속한 아이템이 없을 경우, 코디룩도 함께 삭제한다.
            if (coordinateLook.getItems().isEmpty()) {
                coordinateLookRepository.delete(coordinateLook);
            }
        } catch (NoSuchElementException | MalformedURLException | UnsupportedEncodingException e) {
            throw new EntityNotFoundException(ItemErrorMessage.NOT_FOUND_ITEM.get() + itemId);
        }
    }


    private Long getItemIdFromFileName(MultipartFile fileName) {
        String filenameWithoutExtension = fileName.getOriginalFilename()
                .substring(0, fileName.getOriginalFilename().lastIndexOf('.'));
        String[] parts = filenameWithoutExtension.split("_");
        return Long.valueOf(parts[parts.length - 1]);
    }
}
