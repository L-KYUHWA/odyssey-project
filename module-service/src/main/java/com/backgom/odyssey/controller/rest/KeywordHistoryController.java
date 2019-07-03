package com.backgom.odyssey.controller.rest;

import com.backgom.odyssey.service.KeywordSearchHistoryQueryService;
import com.backgom.odyssey.support.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class KeywordHistoryController {

	@Autowired
	private KeywordSearchHistoryQueryService queryService;

	@GetMapping("keywords/rank")
	@ResponseBody
	public ResponseEntity getRankKeywords() {
		return new ResponseEntity(queryService.findByTopKeywords());
	}

	@GetMapping("keywords/me")
	@ResponseBody
	public ResponseEntity getMyKeywordHistory() {
		return new ResponseEntity(queryService.findByRecentlyMyKeywords());
	}
}
