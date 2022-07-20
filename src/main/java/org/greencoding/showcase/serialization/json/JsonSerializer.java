package org.greencoding.showcase.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JsonSerializer {

    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public void serializeToFile(Object dummy, File file) {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, dummy);
    }

    @SneakyThrows
    public String serialize(Object dummy) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dummy);
    }

    @SneakyThrows
    public Object deserializeFromFile(Class<?> destinationClass, File file) {
        return objectMapper.readValue(file, destinationClass);
    }

    @SneakyThrows
    public Object deserialize(Class<?> destinationClass, String json) {
        return objectMapper.readValue(json, destinationClass);
    }

}
