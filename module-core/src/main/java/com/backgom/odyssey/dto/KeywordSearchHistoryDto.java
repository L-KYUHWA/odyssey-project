package com.backgom.odyssey.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class KeywordSearchHistoryDto {

	private String memberId;
	private String keyword;
	private Long count;

}
