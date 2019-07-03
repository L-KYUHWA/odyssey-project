package com.backgom.odyssey.provider;

import com.backgom.odyssey.auth.SecurityContext;
import com.backgom.odyssey.dto.KeywordSearchCondition;
import com.backgom.odyssey.helper.ObjectMapperHolder;
import com.backgom.odyssey.service.KeywordHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Slf4j
@Component
public class PlaceSearchProvider {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private KeywordHistoryService keywordHistoryService;

	@Value("${kakao.rest_api.key}")
	private String apiKey;

	public Object findPlaces(KeywordSearchCondition keywordSearchCondition) {

		if (Objects.isNull(keywordSearchCondition.getKeyword())) {
			return StringUtils.EMPTY;
		}

		String uri = buildUri();
		MultiValueMap<String, String> parameterMap = buildParameters(keywordSearchCondition);
		HttpEntity httpEntity = buildHttpEntity(parameterMap);

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(uri, httpEntity, String.class);
			keywordHistoryService.saveKeywordHistory(keywordSearchCondition.getKeyword(), SecurityContext.getLoginMember());
			return ObjectMapperHolder.readValue(response.getBody(), Object.class);
		} catch (Exception e) {
			log.warn("[PlaceSearchProvider] Unable to fetch API", e);
		}

		return StringUtils.EMPTY;
	}

	private String buildUri() {
		try {
			return new URI("http://dapi.kakao.com/v2/local/search/keyword.json").toString();
		} catch (URISyntaxException e) {
			log.error("[PlaceSearchProvider] Uri build Fail", e);
		}
		return StringUtils.EMPTY;
	}

	private MultiValueMap<String, String> buildParameters(KeywordSearchCondition keywordSearchCondition) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("radius", "20000");
		parameters.add("query", keywordSearchCondition.getKeyword());
		parameters.add("page", String.valueOf(keywordSearchCondition.getPageNumber()));
		parameters.add("size", String.valueOf(keywordSearchCondition.getPageSize()));
		return parameters;
	}

	private HttpEntity buildHttpEntity(MultiValueMap<String, String> parameterMap) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", apiKey);
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		return new HttpEntity<>(parameterMap, headers);
	}

}
