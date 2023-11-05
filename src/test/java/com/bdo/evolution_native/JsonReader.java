package com.bdo.evolution_native;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader<T> {
    private Class<T> model;

    public JsonReader(Class<T> model) {
        this.model = model;
    }

    public T loadTestJson(String fileName) throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        return getObjectMapper().readValue(file, this.model);
    }

    public List<T> loadTestJsonArray(String fileName) throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        return getObjectMapper().readValue(file, new TypeReference<ArrayList<T>>() {
        });
    }

    private ObjectMapper getObjectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();
    }

}
