package com.backgom.odyssey.provider;

import com.backgom.odyssey.dto.KeywordSearchCondition;
import com.backgom.odyssey.service.KeywordHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaceSearchProviderTest {

	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final String KEYWORD = "TestKeyword";
	@InjectMocks
	private PlaceSearchProvider sut;
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private KeywordHistoryService keywordHistoryService;

	@Before
	public void setUp() throws Exception {
		when(restTemplate.postForEntity(anyString(), any(), any())).thenReturn(mockResponse());
	}

	@Test
	public void findPlaces_null_condition() {
		String result = sut.findPlaces(null);
		assertThat(result).isBlank();
	}

	@Test
	public void findPlaces_blank_keyword() {
		String result = sut.findPlaces(mockCondition(StringUtils.EMPTY));
		assertThat(result).isBlank();
	}

	@Test
	public void findPlaces() {
		String result = sut.findPlaces(mockCondition(KEYWORD));
		assertThat(result).isNotBlank();
	}

	private KeywordSearchCondition mockCondition(String keyword) {
		return new KeywordSearchCondition()
				.setKeyword(keyword)
				.setPageNumber(DEFAULT_PAGE_NUMBER)
				.setPageSize(DEFAULT_PAGE_SIZE);
	}

	private ResponseEntity mockResponse() {
		return new ResponseEntity<>("{\"Test\":{\"TestKey\":\"TestValue\"}}", new HttpHeaders(), HttpStatus.OK);
	}
}