package com.backgom.odyssey.service;

import com.backgom.odyssey.auth.SecurityContext;
import com.backgom.odyssey.converter.KeywordSearchHistoryDtoConverter;
import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import com.backgom.odyssey.repository.KeywordSearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
@Transactional(readOnly = false)
public class KeywordSearchHistoryCommandService {

	@Autowired
	private KeywordSearchHistoryDtoConverter converter;
	@Autowired
	private KeywordSearchHistoryRepository keywordSearchHistoryRepository;

	public KeywordSearchHistoryEntity saveHistory(String keyword) {
		String loginMemberId = SecurityContext.getLoginMember();
		KeywordSearchHistoryEntity historyEntity = keywordSearchHistoryRepository.findByKeywordAndMemberId(keyword, loginMemberId);

		if (Objects.isNull(historyEntity)) {
			KeywordSearchHistoryDto historyDto = new KeywordSearchHistoryDto();
			historyDto.setKeyword(keyword);
			historyDto.setCount(1L);
			historyDto.setMemberId(loginMemberId);
			KeywordSearchHistoryEntity entity = converter.convertToNewEntity(historyDto);
			return keywordSearchHistoryRepository.save(entity);
		}

		historyEntity.setCount(historyEntity.getCount() + 1L);
		historyEntity.setModifiedAt(new Date(System.currentTimeMillis()));
		historyEntity.setModifiedBy(loginMemberId);
		return keywordSearchHistoryRepository.save(historyEntity);
	}


}
