package com.backgom.odyssey.service;

import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
public class KeywordHistoryFacade {

	@Autowired
	private KeywordSearchHistoryQueryService queryService;
	@Autowired
	private KeywordSearchHistoryCommandService commandService;

	private static final long ADD_COUNT = 1L;

	@Async
	public KeywordSearchHistoryDto saveKeywordHistory(String keyword, String loginMemberId) {

		try {
			KeywordSearchHistoryDto keywordSearchHistoryDto = queryService.findByKeywordAndMemberId(keyword, loginMemberId);

			if (hasKeyword(keywordSearchHistoryDto)) {
				return updateKeywordHistory(keywordSearchHistoryDto, loginMemberId);
			} else {
				return saveNewKeywordHistory(keyword, loginMemberId);
			}
		} catch (Exception e) {
			log.error("[KeywordHistoryFacade] Failed to save keyword history.", e);
		}

		return new KeywordSearchHistoryDto();
	}

	private KeywordSearchHistoryDto saveNewKeywordHistory(String keyword, String loginMemberId) {
		KeywordSearchHistoryDto historyDto = new KeywordSearchHistoryDto();
		historyDto.setKeyword(keyword);
		historyDto.setCount(ADD_COUNT);
		historyDto.setMemberId(loginMemberId);
		historyDto.setCreatedBy(loginMemberId);
		historyDto.setModifiedBy(loginMemberId);
		historyDto.setCreateAt(new Date(System.currentTimeMillis()));
		historyDto.setModifiedAt(new Date(System.currentTimeMillis()));
		return commandService.saveHistory(historyDto);
	}

	private KeywordSearchHistoryDto updateKeywordHistory(KeywordSearchHistoryDto dto, String loginMemberId) {
		dto.setCount(dto.getCount() + ADD_COUNT);
		dto.setModifiedBy(loginMemberId);
		dto.setModifiedAt(new Date(System.currentTimeMillis()));
		return commandService.saveHistory(dto);
	}

	private boolean hasKeyword(KeywordSearchHistoryDto dto) {
		return Objects.nonNull(dto);
	}

}
