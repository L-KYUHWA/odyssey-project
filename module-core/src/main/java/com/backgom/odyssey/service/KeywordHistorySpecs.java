package com.backgom.odyssey.service;

import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class KeywordHistorySpecs {

	public static Specification<KeywordSearchHistoryEntity> equalsMemberId(String memberId) {
		return (root, query, cb) -> {
			if (StringUtils.isBlank(memberId)) {
				return cb.and();
			}

			return cb.equal(root.get("memberId"), memberId);
		};
	}
}
