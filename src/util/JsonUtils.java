package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return (T)objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        throw new RuntimeException();
//        return null;
    }

    public static <T> T fromJson(String json, TypeReference<T> type){
        try {
            return (T)objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
