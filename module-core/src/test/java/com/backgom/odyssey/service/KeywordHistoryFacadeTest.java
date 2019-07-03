package com.backgom.odyssey.service;

import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeywordHistoryFacadeTest {

	private static final String TEST_MEMBER = "testMember";
	private static final String TEST_KEYWORD = "testKeyword";
	private static final long TEST_COUNT = 1L;
	@InjectMocks
	private KeywordHistoryFacade sut;
	@Mock
	private KeywordSearchHistoryQueryService queryService;
	@Mock
	private KeywordSearchHistoryCommandService commandService;

	@Test
	public void saveKeywordHistory_new() {
		when(queryService.findByKeywordAndMemberId(TEST_KEYWORD, TEST_MEMBER)).thenReturn(null);
		when(commandService.saveHistory(any())).thenReturn(new KeywordSearchHistoryDto());

		KeywordSearchHistoryDto result = sut.saveKeywordHistory(TEST_KEYWORD, TEST_MEMBER);
		assertThat(result).isNotNull();
	}

	@Test
	public void saveKeywordHistory_update() {
		when(queryService.findByKeywordAndMemberId(TEST_KEYWORD, TEST_MEMBER)).thenReturn(mockDto());
		when(commandService.saveHistory(any())).thenReturn(new KeywordSearchHistoryDto());

		KeywordSearchHistoryDto result = sut.saveKeywordHistory(TEST_KEYWORD, TEST_MEMBER);
		assertThat(result).isNotNull();
	}

	private KeywordSearchHistoryDto mockDto() {
		return new KeywordSearchHistoryDto()
				.setCount(TEST_COUNT);
	}
}