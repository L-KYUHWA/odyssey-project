package com.backgom.odyssey.converter;

import com.backgom.odyssey.auth.SecurityContext;
import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class KeywordSearchHistoryDtoConverter {

	public KeywordSearchHistoryDto convertDtoFromEntity(KeywordSearchHistoryEntity sourceEntity) {

		if (Objects.isNull(sourceEntity)) {
			return null;
		}

		return new KeywordSearchHistoryDto()
				.setKeyword(sourceEntity.getKeyword())
				.setMemberId(sourceEntity.getMemberId())
				.setCount(sourceEntity.getCount());

	}

	public KeywordSearchHistoryEntity convertToNewEntity(KeywordSearchHistoryDto sourceDto) {
		KeywordSearchHistoryEntity entity = new KeywordSearchHistoryEntity();
		BeanUtils.copyProperties(sourceDto, entity);

		entity.setCreatedBy(SecurityContext.getLoginMember());
		entity.setModifiedBy(SecurityContext.getLoginMember());
		entity.setCreateAt(new Date(System.currentTimeMillis()));
		entity.setModifiedAt(new Date(System.currentTimeMillis()));

		return entity;
	}
}
