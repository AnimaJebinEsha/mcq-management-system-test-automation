package tech.innospace.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JSONUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode readJsonFile(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }
}

