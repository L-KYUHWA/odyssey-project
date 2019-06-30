package com.backgom.odyssey.dto;

import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import com.backgom.odyssey.service.KeywordHistorySpecs;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;


@Getter
@Setter
public class KeywordHistorySearchCondition {

	private boolean isRankKeyword;
	private boolean isMyKeywordHistory;

	public Specification<KeywordSearchHistoryEntity> getSpecification() {
		Specification<KeywordSearchHistoryEntity> specification = Specification.where((root, query, cb) -> cb.and());

		if (isMyKeywordHistory) {

			specification = KeywordHistorySpecs.equalsMemberId("");
		}

		if (isRankKeyword) {
		}

		return specification;
	}


}
