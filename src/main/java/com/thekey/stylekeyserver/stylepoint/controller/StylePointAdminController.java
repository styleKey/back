package com.thekey.stylekeyserver.stylepoint.controller;

import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.dto.StylePointDto;
import com.thekey.stylekeyserver.stylepoint.service.StylePointAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "StylePoint", description = "StylePoint API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/style-points")
public class StylePointAdminController {

    private final StylePointAdminService stylePointAdminService;

    @GetMapping("/{id}")
    @Operation(summary = "Read One StylePoint", description = "스타일포인트 단건 정보 조회")
    public ResponseEntity<StylePoint> getStylePoint(@PathVariable Long id) {
        Optional<StylePoint> optional = Optional.ofNullable(stylePointAdminService.findById(id));

        return optional.map(stylePoint -> ResponseEntity.ok(stylePoint))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Read All StylePoint", description = "스타일포인트 전체 정보 조회.")
    public ResponseEntity<List<StylePoint>> getStylePoints() {
        List<StylePoint> stylePoints = stylePointAdminService.findAll();

        return Optional.of(stylePoints)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).body(stylePoints));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update StylePoint", description = "스타일포인트 정보 수정")
    public ResponseEntity<StylePoint> update(@PathVariable Long id,
                                             @RequestBody StylePointDto requestDto) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<StylePoint> optional = Optional.ofNullable(stylePointAdminService.update(id, requestDto));
        return optional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
