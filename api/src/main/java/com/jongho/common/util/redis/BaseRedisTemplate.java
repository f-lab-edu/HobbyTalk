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
        String jsonValue;
        try {
            jsonValue = objectMapper.writeValueAsString(value);
        }catch (Exception e) {
            throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
        }

        stringRedisTemplate.opsForValue().set(key, jsonValue);
    }

    public <T> T getData(String key, Class<T> valueType) {
        String jsonValue = stringRedisTemplate.opsForValue().get(key);

        if (jsonValue != null) {
            try {
                return objectMapper.readValue(jsonValue, valueType);
            }catch (Exception e) {
                throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
            }
        } else {
            return null;
        }
    }
}