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

	@Async
	public void saveKeywordHistory(String keyword, String loginMemberId) {

		try {
			KeywordSearchHistoryDto keywordSearchHistoryDto = queryService.findByKeywordAndMemberId(keyword, loginMemberId);

			if (hasKeyword(keywordSearchHistoryDto)) {
				updateKeywordHistory(keywordSearchHistoryDto, loginMemberId);
			} else {
				saveNewKeywordHistory(keyword, loginMemberId);
			}
		} catch (Exception e) {
			log.error("[KeywordHistoryFacade] Failed to save keyword history.", e);
		}
	}

	private void saveNewKeywordHistory(String keyword, String loginMemberId) {
		KeywordSearchHistoryDto historyDto = new KeywordSearchHistoryDto();
		historyDto.setKeyword(keyword);
		historyDto.setCount(1L);
		historyDto.setMemberId(loginMemberId);
		historyDto.setCreatedBy(loginMemberId);
		historyDto.setModifiedBy(loginMemberId);
		historyDto.setCreateAt(new Date(System.currentTimeMillis()));
		historyDto.setModifiedAt(new Date(System.currentTimeMillis()));
		commandService.saveHistory(historyDto);
	}

	private void updateKeywordHistory(KeywordSearchHistoryDto dto, String loginMemberId) {
		dto.setCount(dto.getCount() + 1L);
		dto.setModifiedBy(loginMemberId);
		dto.setModifiedAt(new Date(System.currentTimeMillis()));
		commandService.saveHistory(dto);
	}

	private boolean hasKeyword(KeywordSearchHistoryDto dto) {
		return Objects.nonNull(dto);
	}

}
