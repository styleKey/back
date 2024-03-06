package com.thekey.stylekeyserver.common.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> void setData(String key, T value) throws JsonProcessingException {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        String serializedValue = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, serializedValue);
    }

    public <T> T getData(String key, TypeReference<T> type) throws JsonProcessingException {
        String serializedValue = (String) redisTemplate.opsForValue().get(key);
        if (serializedValue == null) {
            return null;
        }
        T result = objectMapper.readValue(serializedValue, type);
        return objectMapper.readValue(serializedValue, type);
    }


    public <T> void deleteData(String key, TypeReference<T> type) throws JsonProcessingException {
        T data = getData(key, type);
        if(data != null) {
            redisTemplate.delete(key);
        }
    }


    public void increaseLikeCount(String id) {
        redisTemplate.opsForValue().increment("likeCount:" + id);
    }

    public void decreaseLikeCount(String id) {
        Object likeCount = redisTemplate.opsForValue().get("likeCount:" + id);
        if (likeCount != null && ((Number) likeCount).longValue() > 0) {
            redisTemplate.opsForValue().decrement("likeCount:" + id);
        }
    }

    public Object getLikeCount(String id) {
        return redisTemplate.opsForValue().get("likeCount:" + id);
    }

}

