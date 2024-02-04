package com.thekey.stylekeyserver.coordinatelook.controller;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.CoordinateLookDetailsResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.CoordinateLookResponse;
import com.thekey.stylekeyserver.coordinatelook.service.CoordinateLookAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CoordinateLook", description = "CoordinateLook API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coordinate-looks")
public class CoordinateLookApiController {

    private final CoordinateLookAdminService coordinateLookAdminService;


    @GetMapping("/{id}")
    @Operation(summary = "Read One CoordinateLook With Items", description = "코디룩 단건 조회. 해당 코디룩 안에 속한 아이템을 함께 반환한다.")
    public ResponseEntity<CoordinateLookDetailsResponse> getCoordinateLook(@PathVariable Long id) {
        Optional<CoordinateLook> optional = Optional.ofNullable(coordinateLookAdminService.findById(id));

        return optional.map(coordinateLook -> {
            CoordinateLookDetailsResponse response = CoordinateLookDetailsResponse.of(coordinateLook,
                    coordinateLook.getItems());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    @Operation(summary = "Read All CoordinateLook", description = "코디룩 정보 전체 조회")
    public ResponseEntity<List<CoordinateLookResponse>> getCoordinateLooks() {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findAll();

        if (coordinateLooks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CoordinateLookResponse> coordinateLookResponses = coordinateLooks.stream()
                .map(CoordinateLookResponse::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(coordinateLookResponses);
    }

    @GetMapping("/style-points/{id}")
    @Operation(summary = "Read All CoordinateLooks By StylePointId", description = "스타일포인트 ID에 해당하는 코디룩 목록 전체 조회")
    public ResponseEntity<List<CoordinateLookResponse>> getCoordinateLooksByStylePointId(@PathVariable Long id) {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findByStylePointId(id);
        List<CoordinateLookResponse> coordinateLookResponses = coordinateLooks.stream()
                .map(CoordinateLookResponse::of)
                .collect(Collectors.toList());

        return Optional.of(coordinateLookResponses)
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).body(coordinateLookResponses));
    }
}
