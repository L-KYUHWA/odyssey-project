package com.backgom.odyssey.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ObjectMapperHolder {

	@Getter
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	}

	public static <T> T clone(T source, TypeReference<T> typeReference) {
		String json = writeValueAsString(source);
		return readValue(json, typeReference);
	}

	public static <T> T clone(T source, Class<T> clazz) {
		String json = writeValueAsString(source);
		return readValue(json, clazz);
	}

	public static <T> T clone(String source, Class<T> clazz) {
		String json = writeValueAsString(source);
		return readValue(json, clazz);
	}

	public static <T> T readValue(String content, Class<T> valueType) {
		T result = null;

		try {
			result = objectMapper.readValue(content, valueType);
		} catch (IOException e) {
			log.error("JSON deserialize Error {} to {}", content, valueType.getName());
		}

		return result;
	}

	public static <T> T readValue(String content, TypeReference<T> typeReference) {
		T result = null;

		try {
			result = objectMapper.readValue(content, typeReference);
		} catch (IOException e) {
			log.error("JSON deserialize Error {} to {}", content, typeReference.getType().getTypeName());
		}

		return result;
	}

	public static String writeValueAsString(Object object) {
		String result = null;

		try {
			result = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("JSON serialize Error {}", object);
		}

		return result;
	}


}
