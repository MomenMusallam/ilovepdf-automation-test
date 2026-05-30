package com.ilovepdf.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilovepdf.constants.FrameworkConstants;
import com.ilovepdf.exceptions.FrameworkException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() {}

    public static JsonNode readJson() {
        try {
            return MAPPER.readTree(new File(FrameworkConstants.getJsonPath()));
        } catch (IOException e) {
            throw new FrameworkException("Failed to read JSON file", e);
        }
    }

    public static <T> T readJsonAs(Class<T> clazz) {
        try {
            return MAPPER.readValue(new File(FrameworkConstants.getJsonPath()), clazz);
        } catch (IOException e) {
            throw new FrameworkException("Failed to map JSON to class", e);
        }
    }

    public static List<Map<String, Object>> readJsonAsList() {
        try {
            return MAPPER.readValue(new File(FrameworkConstants.getJsonPath()),
                    new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            throw new FrameworkException("Failed to read JSON as list", e);
        }
    }

    public static String getValue(String key) {
        return readJson().get(key).asText();
    }
}
