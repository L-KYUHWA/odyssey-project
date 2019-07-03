package com.backgom.odyssey.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class KeywordSearchCondition {
	private String keyword;
	private int pageNumber;
	private int pageSize;
}
