package com.thekey.stylekeyserver.common.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import java.util.Set;
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
    public void setData(String key, Set<Long> values) throws JsonProcessingException {
        valueOperations.set(key, objectMapper.writeValueAsString(values));
    }

    public Set<Long> getData(String key) throws JsonProcessingException {
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

