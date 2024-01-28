package com.jongho.common.util.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jongho.common.exception.MyJsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if(jsonValue == null){
            return null;
        }
        return toObject(jsonValue, valueType);
    }

    public <T> List<T> getAllListData(String key, Class<T> valueType) {
        List<String> jsonValue = stringRedisTemplate.opsForList().range(key, 0, -1);
        if(jsonValue == null){
            return null;
        }
        return mappingToElement(jsonValue, valueType);
    }

    public <T> List<T> getReverseRangeAllListData(String key, Class<T> valueType) {
        List<String> jsonValue = stringRedisTemplate.opsForList().range(key, 0, -1);
        if(jsonValue == null){
            return null;
        }
        List<T> result = mappingToElement(jsonValue, valueType);
        Collections.reverse(result);

        return result;
    }

    public <T> void setAllListData(String key, List<T> value) {
        stringRedisTemplate.opsForList().rightPushAll(key, value.stream().map(this::toJson).collect(Collectors.toList()));
    }

    public <T> void setListData(String key, T value) {
        stringRedisTemplate.opsForList().rightPush(key, toJson(value));
    }

    public <T> List<T> getListData(String key, Class<T> valueType, int offset, int limit) {
        List<String> jsonValue = stringRedisTemplate.opsForList().range(key, offset, limit);
        if(jsonValue == null){
            return null;
        }
        return mappingToElement(jsonValue, valueType);
    }

    public void setHashData(String key, Map<String, String> value) {
        stringRedisTemplate.opsForHash().putAll(key, value);
    }

    public <T> T getHashData(String key, Class<T> valueType) {
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
        if(map.size() == 0){
            return null;
        }
        return mapToObject(map, valueType);
    }

    public <T> T toObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        }catch (Exception e) {
            throw new MyJsonProcessingException(e.getMessage()!=null? e.getMessage():"json processing error");
        }
    }

    public <T> T mapToObject(Map<Object, Object> map, Class<T> valueType) {
        try {
            return objectMapper.convertValue(map, valueType);
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

    private <T> List<T> mappingToElement(Collection<String> jsonList, Class<T> valueType){
        return jsonList.stream().map(json -> toObject(json, valueType)).collect(Collectors.toList());
    }
}