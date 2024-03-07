package com.thekey.stylekeyserver.like.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thekey.stylekeyserver.like.dto.response.LikeCoordinateLookResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("LikeCoordinateLook")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeCoordinateLook {

    private String userId;
    private LikeCoordinateLookResponse coordinateLookResponses;
    @JsonCreator
    public LikeCoordinateLook(@JsonProperty("userId") String userId,
                              @JsonProperty("coordinateLookResponse") LikeCoordinateLookResponse coordinateLookResponse) {
        this.userId = userId;
        this.coordinateLookResponses = coordinateLookResponse;
    }

    @Builder
    public static LikeCoordinateLook of(String userId, LikeCoordinateLookResponse coordinateLookResponse) {
        return new LikeCoordinateLook(userId, coordinateLookResponse);
    }
}
