package com.backgom.odyssey.provider;

import com.sun.jndi.toolkit.url.Uri;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;

@Slf4j
@Component
public class PlaceSearchProvider {

	@Autowired
	private RestTemplate restTemplate;

	public String findPlacesByKeyword(String keyword) {

		String uri = buildUri();
		HttpEntity httpEntity = buildHttpEntity(keyword);

		ResponseEntity<String> response = restTemplate.postForEntity(uri, httpEntity, String.class);
		return response.getBody();
	}

	private String buildUri() {
		try {
			return new Uri("http://dapi.kakao.com/v2/local/search/keyword.json").toString();
		} catch (MalformedURLException e) {
			log.error("[PlaceSearchProvider] Uri build Fail", e);
		}
		return StringUtils.EMPTY;
	}

	private MultiValueMap<String, String> buildParameters(String keyword) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("x", "127.06283102249932");
		parameters.add("y", "37.514322572335935");
		parameters.add("radius", "20000");
		parameters.add("query", keyword);
		return parameters;
	}

	private HttpEntity buildHttpEntity(String keyword) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 3f75896063f89f451cfc92dcb07a9544");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(buildParameters(keyword), headers);
		return request;
	}

}
