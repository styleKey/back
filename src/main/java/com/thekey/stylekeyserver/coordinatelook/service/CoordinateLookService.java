package com.thekey.stylekeyserver.coordinatelook.service;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookDetailsResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookResponse;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinateLookService {

    private final CoordinateLookAdminService coordinateLookAdminService;
    private final LikeCoordinateLookService likeCoordinateLookService;

    public ApiCoordinateLookDetailsResponse getCoordinateLookWithLikes(Long id) {
        CoordinateLook coordinateLook = coordinateLookAdminService.findById(id);
        Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(id);
        return ApiCoordinateLookDetailsResponse.of(coordinateLook, likeCount, coordinateLook.getItems());
    }

    public List<ApiCoordinateLookResponse> getAllCoordinateLooksWithLikes() {
        return coordinateLookAdminService.findAll().stream()
                .map(this::buildApiCoordinateLookResponse)
                .collect(Collectors.toList());
    }

    public List<ApiCoordinateLookResponse> getCoordinateLookWithLikesByStylePointId(Long stylePointId) {
        return coordinateLookAdminService.findByStylePointId(stylePointId).stream()
                .map(this::buildApiCoordinateLookResponse)
                .collect(Collectors.toList());
    }

    private ApiCoordinateLookResponse buildApiCoordinateLookResponse(CoordinateLook coordinateLook) {
        Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(coordinateLook.getId());
        return ApiCoordinateLookResponse.of(coordinateLook, likeCount);
    }
}
