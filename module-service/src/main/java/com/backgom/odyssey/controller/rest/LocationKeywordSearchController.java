package com.backgom.odyssey.controller.rest;

import com.backgom.odyssey.dto.KeywordSearchCondition;
import com.backgom.odyssey.provider.PlaceSearchProvider;
import com.backgom.odyssey.support.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class LocationKeywordSearchController {

	@Autowired
	private PlaceSearchProvider placeSearchProvider;

	@GetMapping("search")
	@ResponseBody
	public RequestResult search(@ModelAttribute KeywordSearchCondition keywordSearchCondition) {
		Object searchResult = placeSearchProvider.findPlaces(keywordSearchCondition);
		return new RequestResult(searchResult);
	}

}
