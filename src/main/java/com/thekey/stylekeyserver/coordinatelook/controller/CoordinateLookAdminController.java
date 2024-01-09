package com.thekey.stylekeyserver.coordinatelook.controller;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.CoordinateLookDto;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CoordinateLook", description = "CoordinateLook API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/coordinate-looks")
public class CoordinateLookAdminController {

    private final CoordinateLookAdminService coordinateLookAdminService;

    @PostMapping
    @Operation(summary = "Create CoordinateLook", description = "코디룩 정보 등록")
    public ResponseEntity<Map<String, Object>> create(@RequestBody CoordinateLookDto requestDto) {
        Optional<CoordinateLook> optional = Optional.ofNullable(coordinateLookAdminService.create(requestDto));

        return optional.map(createdCoordinateLook -> {
            Map<String, Object> response = Map.of(
                    "coordinateLook", createdCoordinateLook,
                    "stylePointId", getStylePointId(createdCoordinateLook)
            );
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/{id}")
    @Operation(summary = "Read One CoordinateLook", description = "코디룩 정보 단건 조회")
    public ResponseEntity<CoordinateLookDto> getCoordinateLook(@PathVariable Long id) {
        Optional<CoordinateLook> optional = Optional.ofNullable(coordinateLookAdminService.findById(id));

        return optional.map(coordinateLook -> {
            CoordinateLookDto coordinateLookResponseDto = coordinateLookAdminService.convertToResponseDto(
                    coordinateLook);
            return ResponseEntity.ok(coordinateLookResponseDto);
        }).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    @Operation(summary = "Read All CoordinateLook", description = "코디룩 정보 전체 조회")
    public ResponseEntity<List<CoordinateLookDto>> getCoordinateLooks() {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findAll();

        if (coordinateLooks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CoordinateLookDto> coordinateLookDtos = coordinateLooks.stream()
                .map(coordinateLookAdminService::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(coordinateLookDtos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CoordinateLook", description = "코디룩 정보 수정")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @RequestBody CoordinateLookDto requestDto) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<CoordinateLook> optional = Optional.ofNullable(coordinateLookAdminService.update(id, requestDto));
        return optional.map(coordinateLook -> ResponseEntity.ok(Map.of(
                "coordinateLook", coordinateLook,
                "stylePointId", getStylePointId(coordinateLook)
        ))).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{coordinateLookId}/items/{itemId}")
    @Operation(summary = "Delete Item From CoordinateLookId", description = "코디룩에 속한 아이템 삭제")
    public ResponseEntity<Void> deleteItemFromCoordinateLook(@PathVariable Long coordinateLookId,
                                       @PathVariable Long itemId) {
        coordinateLookAdminService.deleteItemFromCoordinateLook(coordinateLookId, itemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CoordinateLook ", description = "코디룩 정보 삭제")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coordinateLookAdminService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private Long getStylePointId(CoordinateLook coordinateLook) {
        return Optional.ofNullable(coordinateLook.getStylePoint())
                .map(StylePoint::getId)
                .orElse(null);
    }
}
