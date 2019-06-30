package com.backgom.odyssey.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordSearchCondition {
	private String keyword;
	private int pageNumber;
	private int pageSize;
}
