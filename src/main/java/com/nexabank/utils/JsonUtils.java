package com.nexabank.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static JsonNode readJson(String filePath) {
        try {
            return objectMapper.readTree(new File(filePath));
        } catch (IOException exception) {
            throw new RuntimeException("Unable to read JSON file: " + filePath, exception);
        }
    }
}
