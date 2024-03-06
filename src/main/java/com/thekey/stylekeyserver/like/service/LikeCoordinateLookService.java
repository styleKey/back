package com.thekey.stylekeyserver.like.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.common.redis.RedisService;
import com.thekey.stylekeyserver.coordinatelook.CoordinateLookErrorMessage;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.repository.CoordinateLookRepository;
import com.thekey.stylekeyserver.like.dto.response.LikeCoordinateLookResponse;
import jakarta.persistence.EntityNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeCoordinateLookService {

    private final CoordinateLookRepository coordinateLookRepository;
    private final RedisService redisService;


    @Transactional
    public void addLikeCoordinateLook(List<Long> coordinateLookIds, String userId)
            throws JsonProcessingException {

        List<LikeCoordinateLookResponse> likeCoordinateLookResponses = new ArrayList<>();
        for (Long coordinateLookId : coordinateLookIds) {
            CoordinateLook coordinateLook = coordinateLookRepository.findById(coordinateLookId)
                    .orElseThrow(() -> new EntityNotFoundException(
                            CoordinateLookErrorMessage.NOT_FOUND_COORDINATE_LOOK.get()
                    ));

            LikeCoordinateLookResponse likeCoordinateLookResponse = LikeCoordinateLookResponse.of(coordinateLook);
            likeCoordinateLookResponses.add(likeCoordinateLookResponse);

            redisService.increaseLikeCount(String.valueOf(coordinateLookId));
            redisService.setData("like:" + userId, likeCoordinateLookResponses);
        }
    }

    @Transactional(readOnly = true)
    public List<LikeCoordinateLookResponse> getLikeCoordinateLooks(String userId) throws JsonProcessingException {
        List<LikeCoordinateLookResponse> likeCoordinateLookResponses = redisService.getData("like:" + userId,
                new TypeReference<List<LikeCoordinateLookResponse>>() {
                });

        if (likeCoordinateLookResponses != null) {
            return likeCoordinateLookResponses;
        } else {
            return new ArrayList<>();
        }
    }

    public Integer getLikeCount(Long coordinateLookId) {
        return (Integer) redisService.getLikeCount(String.valueOf(coordinateLookId));
    }

    public void deleteLikeCoordinateLook(List<Long> coordinateLookIds, String userId) throws JsonProcessingException {

        for (Long coordinateLookId : coordinateLookIds) {
            redisService.decreaseLikeCount(String.valueOf(coordinateLookId));
        }

        redisService.deleteData("like:" + userId, new TypeReference<List<LikeCoordinateLookResponse>>() {
        });
    }

}
