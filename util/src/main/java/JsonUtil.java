import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by 089245 on 2014/10/27.
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
    }

    public static <T> T readData(String json, Class<T> classType) {
        try {
            return mapper.readValue(json, classType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeValueAsString(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode readTree(String content) {
        try {
            return mapper.readTree(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T readData(JsonNode jsonNode, Class<? extends T> clazz) {
        try {
            return mapper.readValue(jsonNode.traverse(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
