package com.frog.common.redis.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author lh
 */
public class JacksonRedisSerializer<T> implements RedisSerializer<T> {

    private final Type type;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JacksonRedisSerializer(Type type) {
        this.type = type;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            throw new SerializationException("serialize fail", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        JavaType javaType = objectMapper.constructType(type);
        try {
            return objectMapper.readValue(bytes, javaType);
        } catch (IOException e) {
            throw new SerializationException("deserialize by type fail", e);
        }
    }
}
