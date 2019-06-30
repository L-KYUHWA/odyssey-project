package com.backgom.odyssey.controller;

import com.backgom.odyssey.dto.KeywordHistorySearchCondition;
import com.backgom.odyssey.service.KeywordSearchHistoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api")
public class KeywordHistoryController {

	@Autowired
	private KeywordSearchHistoryQueryService queryService;

	@RequestMapping("rank-keyword")
	public void getRankKeywords(
			@ModelAttribute KeywordHistorySearchCondition searchCondition,
			@SortDefault(sort = "count", direction = Sort.Direction.DESC) Pageable pageable) {


	}

	@RequestMapping("my-keyword-history")
	public void getMyKeywordHistory(@ModelAttribute KeywordHistorySearchCondition searchCondition) {

	}


}
