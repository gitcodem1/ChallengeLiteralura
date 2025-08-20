package com.njaimed.literalura.challenge.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvertData implements IConvertData {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T retrieveData(String json, Class<T> clase) {
        try {
            // Deserializa el JSON en un objeto
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            // Error de procesamiento de JSON
            throw new RuntimeException("Error al deserializar el JSON", e);
        }
    }
}