package com.jongho.common.util.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jongho.common.exception.MyJsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseRedisTemplate {
    private final StringRedisTemplate stringRedisTemplate;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public <T> void setData(String key, T value) {
        stringRedisTemplate.opsForValue().set(key, toJson(value));
    }

    public <T> T getData(String key, Class<T> valueType) {
        String jsonValue = stringRedisTemplate.opsForValue().get(key);

        return toObject(jsonValue, valueType);
    }

    public <T> T toObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        }catch (Exception e) {
            throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
        }
    }

    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        }catch (Exception e) {
            throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
        }
    }
}