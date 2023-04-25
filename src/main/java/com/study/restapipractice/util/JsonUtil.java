package com.study.restapipractice.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonUtil {
        public static <T> List<T> readJsonFromFile(String filePath, Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }

//    public static <T> List<T> readJsonFromStream(InputStream inputStream, Class<T> valueType) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
//    }


}
