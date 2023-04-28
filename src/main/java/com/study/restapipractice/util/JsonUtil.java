package com.study.restapipractice.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

public class JsonUtil {
        //Json파일을 읽어서 자바객체로 변환
        public static <T> List<T> readJsonFromFile(String filePath, Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), //writeValue(): json파일을 읽어 자바객체로 생성
                objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }

//    public static <T> List<T> readJsonFromStream(InputStream inputStream, Class<T> valueType) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
//    }


        //JSON 데이터를 Integer 객체로 변환
        public static class IntegerDeserializer extends JsonDeserializer<Integer> {
            @Override
            public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                String value = jsonParser.getValueAsString();
                if (StringUtils.isBlank(value)) { // null or blank string
                    return 0;
                } else {
                    return Integer.valueOf(value);
                }
            }
        }

        public class PhoneNumberDeserializer extends StringDeserializer {

            private final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d{2,3}-\\d{3,4}-\\d{4}$");

            @Override
            public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                String value = super.deserialize(p, ctxt);
                if (value == null || value.isEmpty()) {
                    return "";
                } else {
                    if (PHONE_NUMBER_PATTERN.matcher(value).matches()) {
                        return value;
                    } else {
                        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                        BindingResult bindingResult = (BindingResult) request.getAttribute("org.springframework.validation.BindingResult");
                        bindingResult.addError(new FieldError("memberDto", "hp", "Invalid phone number format"));
                        return value;
                    }
                }
            }
        }


}
