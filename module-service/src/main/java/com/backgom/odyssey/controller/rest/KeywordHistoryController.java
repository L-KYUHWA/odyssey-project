package com.backgom.odyssey.controller.rest;

import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.dto.TopKeywordDto;
import com.backgom.odyssey.service.KeywordSearchHistoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class KeywordHistoryController {

	@Autowired
	private KeywordSearchHistoryQueryService queryService;

	@RequestMapping("rank-keyword")
	@ResponseBody
	public List<TopKeywordDto> getRankKeywords() {
		return queryService.findByTopKeywords();
	}

	@RequestMapping("my-keyword-history")
	@ResponseBody
	public List<KeywordSearchHistoryDto> getMyKeywordHistory() {
		return queryService.findByRecentlyMyKeywords();
	}
}
