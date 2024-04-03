package com.thekey.stylekeyserver.coordinatelook.service;

import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookPageResponse;
import com.thekey.stylekeyserver.coordinatelook.entity.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookDetailsResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookResponse;
import com.thekey.stylekeyserver.coordinatelook.repository.CoordinateLookRepository;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CoordinateLookService {

    private final CoordinateLookAdminService coordinateLookAdminService;
    private final LikeCoordinateLookService likeCoordinateLookService;
    private final CoordinateLookRepository coordinateLookRepository;

    public ApiCoordinateLookDetailsResponse getCoordinateLookWithLikes(Long id) {
        CoordinateLook coordinateLook = coordinateLookAdminService.findById(id);
        Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(id);
        return ApiCoordinateLookDetailsResponse.of(coordinateLook, likeCount, coordinateLook.getItems());
    }

    public ApiCoordinateLookPageResponse getAllCoordinateLooksWithLikes(Pageable pageable) {
        Slice<CoordinateLook> coordinateLooks = coordinateLookRepository.findAll(pageable);
        List<ApiCoordinateLookResponse> responses = coordinateLooks.getContent().stream()
                .map(this::buildApiCoordinateLookResponse)
                .toList();

        return ApiCoordinateLookPageResponse.fromSlice(responses, coordinateLooks.hasNext());
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
