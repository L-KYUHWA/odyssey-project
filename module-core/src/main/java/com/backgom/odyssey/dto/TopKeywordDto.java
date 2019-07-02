package com.backgom.odyssey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopKeywordDto {
	private String keyword;
	private long count;
}
