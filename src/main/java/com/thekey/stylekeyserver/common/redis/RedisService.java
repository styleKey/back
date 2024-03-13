package com.thekey.stylekeyserver.common.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RedisService {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void setLikeData(String key, Set<Long> values) throws JsonProcessingException {
        valueOperations.set(key, objectMapper.writeValueAsString(values));
    }

    @Transactional
    public void setViewData(String key, String itemId, Double score) {
        redisTemplate.opsForZSet().add(key, itemId, score);
    }

    public Set<Long> getViewData(String key, double minScore, int count) {
        Set<Object> itemIds = redisTemplate.opsForZSet().reverseRangeByScore(key, minScore, Double.MAX_VALUE, 0, count);
        return itemIds.stream().map(itemId -> Long.valueOf((String) itemId)).collect(Collectors.toSet());
    }

    public Set<Long> getLikeData(String key) throws JsonProcessingException {
        String values = valueOperations.get(key);
        return values == null ? null : objectMapper.readValue(values, new TypeReference<Set<Long>>() {
        });
    }


    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    public Integer increaseLikeCount(String key) {
        Long result = redisTemplate.opsForValue().increment(key, 1);
        return result != null ? result.intValue() : null;
    }

    public Integer decreaseLikeCount(String key) {
        Integer currentLikeCount = getLikeCount(key);
        if (currentLikeCount == null || currentLikeCount <= 0) {
            return 0;
        }

        Long result = redisTemplate.opsForValue().increment(key, -1);
        return result != null ? result.intValue() : null;
    }

    public Integer getLikeCount(String key) {
        Object result = redisTemplate.opsForValue().get(key);
        return result != null ? (Integer) result : 0;
    }

}

