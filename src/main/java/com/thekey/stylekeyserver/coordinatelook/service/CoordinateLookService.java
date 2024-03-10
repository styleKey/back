package com.thekey.stylekeyserver.coordinatelook.service;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookDetailsResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookResponse;
import com.thekey.stylekeyserver.like.service.LikeCoordinateLookService;
import java.util.ArrayList;
import java.util.List;
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
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findAll();
        List<ApiCoordinateLookResponse> response = new ArrayList<>();

        for (CoordinateLook coordinateLook : coordinateLooks) {
            Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(coordinateLook.getId());
            ApiCoordinateLookResponse coordinateLookResponse = ApiCoordinateLookResponse.of(coordinateLook, likeCount);
            response.add(coordinateLookResponse);
        }

        return response;
    }

    public List<ApiCoordinateLookResponse> getCoordinateLookWithLikesByStylePointId(Long id) {
        List<CoordinateLook> coordinateLooks = coordinateLookAdminService.findByStylePointId(id);
        List<ApiCoordinateLookResponse> response = new ArrayList<>();

        for (CoordinateLook coordinateLook : coordinateLooks) {
            Integer likeCount = likeCoordinateLookService.getCoordinateLookLikeCount(id);
            ApiCoordinateLookResponse coordinateLookResponse = ApiCoordinateLookResponse.of(coordinateLook, likeCount);
            response.add(coordinateLookResponse);
        }

        return response;
    }
}
