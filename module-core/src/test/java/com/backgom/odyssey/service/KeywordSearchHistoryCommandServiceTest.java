package com.backgom.odyssey.service;

import com.backgom.odyssey.converter.KeywordSearchHistoryDtoConverter;
import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import com.backgom.odyssey.repository.KeywordSearchHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeywordSearchHistoryCommandServiceTest {

	@InjectMocks
	private KeywordSearchHistoryCommandService sut;

	@Mock
	private KeywordSearchHistoryDtoConverter converter;
	@Mock
	private KeywordSearchHistoryRepository repository;

	@Before
	public void setUp() throws Exception {
		when(repository.save(any(KeywordSearchHistoryEntity.class))).thenReturn(new KeywordSearchHistoryEntity());
		when(converter.convertToNewEntity(any(KeywordSearchHistoryDto.class))).thenReturn(new KeywordSearchHistoryEntity());
		when(converter.convertDtoFromEntity(any(KeywordSearchHistoryEntity.class))).thenReturn(new KeywordSearchHistoryDto());
	}

	@Test
	public void saveHistory() {
		KeywordSearchHistoryDto result = sut.saveHistory(mockDto());
		assertThat(result).isNotNull();
	}

	private KeywordSearchHistoryDto mockDto() {
		return new KeywordSearchHistoryDto()
				.setId(1L)
				.setCount(1L)
				.setKeyword("TestKeyword")
				.setMemberId("TestMember");
	}
}