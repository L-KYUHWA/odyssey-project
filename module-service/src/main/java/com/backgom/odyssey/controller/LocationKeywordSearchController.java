package com.backgom.odyssey.controller;

import com.backgom.odyssey.dto.KeywordSearchCondition;
import com.backgom.odyssey.provider.PlaceSearchProvider;
import com.backgom.odyssey.support.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LocationKeywordSearchController {

	@Autowired
	private PlaceSearchProvider placeSearchProvider;

	@GetMapping("search")
	public RequestResult search(@ModelAttribute KeywordSearchCondition keywordSearchCondition) {
		String placesByKeyword = placeSearchProvider.findPlaces(keywordSearchCondition);
		return new RequestResult(placesByKeyword);
	}

}
