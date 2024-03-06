package com.thekey.stylekeyserver.coordinatelook.controller;

import com.thekey.stylekeyserver.common.exception.ApiResponse;
import com.thekey.stylekeyserver.common.exception.ErrorCode;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.CoordinateLookDetailsResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.CoordinateLookResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.request.CoordinateLookRequest;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.item.dto.request.ItemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "CoordinateLook", description = "CoordinateLook API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/coordinate-looks")
public class CoordinateLookAdminController {

    private final CoordinateLookAdminService coordinateLookAdminService;

    @PostMapping
    @Operation(summary = "Create CoordinateLook", description = "코디룩 정보 등록")
    public ApiResponse create(@RequestPart CoordinateLookRequest requestDto,
                              @RequestPart("coordinate-looks_imageFile") MultipartFile coordinateLookImageFile,
                              @RequestPart("item_imageFile") List<MultipartFile> itemImageFiles) throws Exception {

        if (coordinateLookImageFile.isEmpty() || itemImageFiles.isEmpty()) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
        }
        coordinateLookAdminService.create(requestDto, coordinateLookImageFile, itemImageFiles);
        return ApiResponse.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read One CoordinateLook With Items", description = "코디룩 단건 조회. 해당 코디룩 안에 속한 아이템을 함께 반환한다.")
    public ApiResponse getCoordinateLook(@PathVariable Long id) {
        Optional<CoordinateLook> optional = Optional.ofNullable(coordinateLookAdminService.findById(id));

        return optional.map(coordinateLook -> {
            CoordinateLookDetailsResponse response = CoordinateLookDetailsResponse.of(coordinateLook,
                    coordinateLook.getItems());
            return ApiResponse.success(response);
        }).orElse(ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage()));
    }

    @GetMapping
    @Operation(summary = "Read All CoordinateLook", description = "코디룩 정보 전체 조회")
    public ApiResponse<List<CoordinateLookResponse>> getCoordinateLooks() {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findAll();
        List<CoordinateLookResponse> response = coordinateLooks.stream()
                .map(CoordinateLookResponse::of)
                .collect(Collectors.toList());

        return ApiResponse.success(response);
    }

    @GetMapping("/style-points/{id}")
    @Operation(summary = "Read All CoordinateLooks By StylePointId", description = "스타일포인트 ID에 해당하는 코디룩 목록 전체 조회")
    public ApiResponse<List<CoordinateLookResponse>> getCoordinateLooksByStylePointId(@PathVariable Long id) {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findByStylePointId(id);
        List<CoordinateLookResponse> response = coordinateLooks.stream()
                .map(CoordinateLookResponse::of)
                .collect(Collectors.toList());

        return ApiResponse.success(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CoordinateLook", description = "코디룩 정보 수정")
    public ApiResponse update(@PathVariable Long id, @RequestPart CoordinateLookRequest requestDto,
                              @RequestPart(value = "coordinate-looks_imageFile", required = false) MultipartFile coordinateLookImageFile)
            throws Exception {

        if (id == null) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
        }

        CoordinateLook coordinateLook = coordinateLookAdminService.update(id, requestDto, coordinateLookImageFile);
        CoordinateLookResponse response = CoordinateLookResponse.of(coordinateLook);
        return ApiResponse.success(response);
    }

    @PutMapping("/{coordinateLookId}/items/{itemId}")
    @Operation(summary = "Update Item From CoordinateLookId", description = "코디룩에 속한 아이템 수정")
    public ApiResponse updateItemFromCoordinateLook(@PathVariable Long coordinateLookId,
                                                    @PathVariable Long itemId,
                                                    @RequestPart ItemRequest requestDto,
                                                    @RequestPart(value = "item_imageFile", required = false) MultipartFile itemImageFile)
            throws IOException {
        if(coordinateLookId == null || itemId == null) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
        }

        CoordinateLook coordinateLook = coordinateLookAdminService.updateItem(coordinateLookId, itemId, requestDto, itemImageFile);
        CoordinateLookResponse response = CoordinateLookResponse.of(coordinateLook);
        return ApiResponse.success(response);
    }

    @DeleteMapping("/{coordinateLookId}/items/{itemId}")
    @Operation(summary = "Delete Item From CoordinateLookId", description = "코디룩에 속한 아이템 삭제")
    public ApiResponse<Void> deleteItemFromCoordinateLook(@PathVariable Long coordinateLookId,
                                                             @PathVariable Long itemId) {
        coordinateLookAdminService.deleteItemFromCoordinateLook(coordinateLookId, itemId);
        return ApiResponse.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CoordinateLook ", description = "코디룩 정보 삭제")
    public ApiResponse<Void> delete(@PathVariable Long id) throws MalformedURLException, UnsupportedEncodingException {
        coordinateLookAdminService.delete(id);
        return ApiResponse.success();
    }
}
