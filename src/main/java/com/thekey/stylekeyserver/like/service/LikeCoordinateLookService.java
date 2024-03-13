package com.thekey.stylekeyserver.like.service;

import static com.thekey.stylekeyserver.common.exception.ErrorCode.COORDINATE_LOOK_NOT_FOUND;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thekey.stylekeyserver.common.redis.RedisService;
import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.ApiCoordinateLookResponse;
import com.thekey.stylekeyserver.coordinatelook.repository.CoordinateLookRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeCoordinateLookService {

    private static final String USER_LIKES_KEY = "user:%s:coordinateLook_likes";
    private static final String COORDINATE_LOOK_LIKES_KEY = "coordinateLook:%s:likes";

    private final CoordinateLookRepository coordinateLookRepository;
    private final RedisService redisService;

    public void addLikeCoordinateLook(List<Long> coordinateLookIds, String userId)
            throws JsonProcessingException {

        String userLikesKey = getUserLikesKey(userId);
        Set<Long> userLikes = redisService.getLikeData(userLikesKey);
        if (userLikes == null) {
            userLikes = new HashSet<>();
        }

        for (Long coordinateLookId : coordinateLookIds) {

            // 사용자가 이미 좋아요를 눌렀는지 확인
            if (userLikes.contains(coordinateLookId)) {
                continue;
            }

            String coordinateLookLikesKey = getCoordinateLookLikesKey(coordinateLookId);
            redisService.increaseLikeCount(coordinateLookLikesKey);
            userLikes.add(coordinateLookId);

            // 좋아요 개수 증가 DB에 업데이트
            CoordinateLook coordinateLook = coordinateLookRepository.findById(coordinateLookId)
                    .orElseThrow(() -> new EntityNotFoundException(
                            COORDINATE_LOOK_NOT_FOUND.getMessage()
                    ));
            coordinateLook.setLikeCount(coordinateLook.getLikeCount() + 1);
            coordinateLookRepository.save(coordinateLook);
        }

        redisService.setLikeData(userLikesKey, userLikes);
    }

    public List<ApiCoordinateLookResponse> getLikeCoordinateLooks(String userId) throws JsonProcessingException {
        String userLikesKey = getUserLikesKey(userId);
        Set<Long> userLikes = redisService.getLikeData(userLikesKey);
        if (userLikes == null) {
            return Collections.emptyList();
        }

        // 코디룩 정보는 DB에서 조회
        List<CoordinateLook> coordinateLooks = coordinateLookRepository.findAllById(userLikes);

        // ApiCoordinateLookResponse 형태로 반환
        return coordinateLooks.stream()
                .map(coordinateLook -> {
                    String coordinateLookLikesKey = getCoordinateLookLikesKey(coordinateLook.getId());
                    // 좋아요 개수는 캐시에서 조회
                    Integer likeCount = redisService.getLikeCount(coordinateLookLikesKey);
                    return ApiCoordinateLookResponse.of(coordinateLook, likeCount);
                }).collect(Collectors.toList());
    }

    public Integer getCoordinateLookLikeCount(Long coordinateLookId) {
        return redisService.getLikeCount(getCoordinateLookLikesKey(coordinateLookId));
    }

    public void deleteLikeCoordinateLook(List<Long> coordinateLookIds, String userId) throws JsonProcessingException {
        String userLikesKey = getUserLikesKey(userId);
        Set<Long> userLikes = redisService.getLikeData(userLikesKey);
        if (userLikes != null) {
            for (Long coordinateLookId : coordinateLookIds) {
                String coordinateLookLikesKey = getCoordinateLookLikesKey(coordinateLookId);
                redisService.decreaseLikeCount(coordinateLookLikesKey);
                userLikes.remove(coordinateLookId);

                // 좋아요 개수 감소 DB에 업데이트
                CoordinateLook coordinateLook = coordinateLookRepository.findById(coordinateLookId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                COORDINATE_LOOK_NOT_FOUND.getMessage()
                        ));

                coordinateLook.setLikeCount(coordinateLook.getLikeCount() - 1);
                coordinateLookRepository.save(coordinateLook);
            }
        }
        redisService.setLikeData(userLikesKey, userLikes);
    }

    private static String getUserLikesKey(String userId) {
        return String.format(USER_LIKES_KEY, userId);
    }

    private static String getCoordinateLookLikesKey(Long coordinateLookId) {
        return String.format(COORDINATE_LOOK_LIKES_KEY, coordinateLookId);
    }
}
