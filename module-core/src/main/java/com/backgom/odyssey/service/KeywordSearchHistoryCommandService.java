package com.backgom.odyssey.service;

import com.backgom.odyssey.converter.KeywordSearchHistoryDtoConverter;
import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import com.backgom.odyssey.repository.KeywordSearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class KeywordSearchHistoryCommandService {

	@Autowired
	private KeywordSearchHistoryDtoConverter converter;
	@Autowired
	private KeywordSearchHistoryRepository repository;

	public KeywordSearchHistoryDto saveHistory(KeywordSearchHistoryDto historyDto) {
		KeywordSearchHistoryEntity entity = converter.convertToNewEntity(historyDto);
		return converter.convertDtoFromEntity(repository.save(entity));
	}

}
