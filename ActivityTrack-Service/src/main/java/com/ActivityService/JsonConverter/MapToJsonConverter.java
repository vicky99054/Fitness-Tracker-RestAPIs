package com.ActivityService.JsonConverter;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// This class is use to Convert map to Json 

@Converter
public class MapToJsonConverter implements AttributeConverter<Map<String , Object>, String> {
	
	private final ObjectMapper objectMapper= new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Map<String, Object> attribute) {
		
		try {
			
			return objectMapper.writeValueAsString(attribute);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new IllegalIdentifierException("Error convert map to Json");
		}
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String dbData) {
		
		try {
			
			return objectMapper.readValue(dbData, new TypeReference<>() {
			});
			
		} catch (Exception e) {
			return new HashMap<>();
		}
	}

}
