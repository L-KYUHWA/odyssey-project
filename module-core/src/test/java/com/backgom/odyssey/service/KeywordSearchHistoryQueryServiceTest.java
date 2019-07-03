package com.backgom.odyssey.service;

import com.backgom.odyssey.converter.KeywordSearchHistoryDtoConverter;
import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.dto.TopKeywordDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import com.backgom.odyssey.repository.KeywordSearchHistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeywordSearchHistoryQueryServiceTest {

	private static final String TEST_MEMBER = "testMember";
	private static final String TEST_KEYWORD = "testKeyword";
	@InjectMocks
	private KeywordSearchHistoryQueryService sut;
	@Mock
	private KeywordSearchHistoryDtoConverter converter;
	@Mock
	private KeywordSearchHistoryRepository repository;

	@Test
	public void findByTopKeywords() {
		when(repository.getTopKeywords(any(Pageable.class))).thenReturn(mockTopKeywords());
		List<TopKeywordDto> result = sut.findByTopKeywords();
		assertThat(result).isNotNull();
	}

	@Test
	public void findByRecentlyMyKeywords() {
		when(repository.findByMemberId(anyString(), any(Pageable.class))).thenReturn(mockKeywordHistoryEntity());
		when(converter.convertFromEntities(anyList())).thenReturn(mockKeywordHistoryDto());
		List<KeywordSearchHistoryDto> result = sut.findByRecentlyMyKeywords();
		assertThat(result).isNotNull();
	}

	@Test
	public void findByKeywordAndMemberId() {
		when(repository.findByKeywordAndMemberId(TEST_KEYWORD, TEST_MEMBER)).thenReturn(new KeywordSearchHistoryEntity());
		when(converter.convertDtoFromEntity(any(KeywordSearchHistoryEntity.class))).thenReturn(new KeywordSearchHistoryDto());
		KeywordSearchHistoryDto result = sut.findByKeywordAndMemberId(TEST_KEYWORD, TEST_MEMBER);
		assertThat(result).isNotNull();
	}

	private List<TopKeywordDto> mockTopKeywords() {
		List<TopKeywordDto> mockList = new ArrayList<>();
		return mockList;
	}

	private List<KeywordSearchHistoryEntity> mockKeywordHistoryEntity() {
		List<KeywordSearchHistoryEntity> mockList = new ArrayList<>();
		return mockList;
	}

	private List<KeywordSearchHistoryDto> mockKeywordHistoryDto() {
		List<KeywordSearchHistoryDto> mockList = new ArrayList<>();
		return mockList;
	}


}