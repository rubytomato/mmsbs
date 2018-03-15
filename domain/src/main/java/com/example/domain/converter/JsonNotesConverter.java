package com.example.domain.converter;

import com.example.domain.entity.Stock;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Slf4j
public class JsonNotesConverter implements AttributeConverter<Stock.Notes, String> {

    private final ObjectMapper objectMapper =
            new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public String convertToDatabaseColumn(Stock.Notes notes) {
        try {
            return objectMapper.writeValueAsString(notes);
        } catch (JsonProcessingException e) {
            log.warn("can't convert to json.", e);
        }
        return null;
    }

    @Override
    public Stock.Notes convertToEntityAttribute(String str) {
        try {
            return objectMapper.readValue(str, Stock.Notes.class);
        } catch (IOException e) {
            log.warn("can't convert to entity.", e);
        }
        return null;
    }
}
