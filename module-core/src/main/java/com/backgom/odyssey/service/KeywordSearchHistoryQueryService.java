package com.backgom.odyssey.service;

import com.backgom.odyssey.auth.SecurityContext;
import com.backgom.odyssey.converter.KeywordSearchHistoryDtoConverter;
import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.dto.TopKeywordDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import com.backgom.odyssey.repository.KeywordSearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class KeywordSearchHistoryQueryService {

	private static final int DEFAULT_PAGE = 0;
	private static final int DEFAULT_SIZE = 10;

	@Autowired
	private KeywordSearchHistoryDtoConverter converter;
	@Autowired
	private KeywordSearchHistoryRepository repository;

	public List<TopKeywordDto> findByTopKeywords() {
		Pageable limitPageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE);
		return repository.getTopKeywords(limitPageable);
	}

	public List<KeywordSearchHistoryDto> findByRecentlyMyKeywords() {
		Sort sort = new Sort(Sort.Direction.DESC, "modifiedAt");
		Pageable limitPageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE, sort);

		String loginMember = SecurityContext.getLoginMember();
		List<KeywordSearchHistoryEntity> myRecentlyKeywordList = repository.findByMemberId(loginMember, limitPageable);
		return converter.convertFromEntities(myRecentlyKeywordList);
	}

	public KeywordSearchHistoryDto findByKeywordAndMemberId(String keyword, String memberId) {
		KeywordSearchHistoryEntity history = repository.findByKeywordAndMemberId(keyword, memberId);
		return converter.convertDtoFromEntity(history);
	}

}
