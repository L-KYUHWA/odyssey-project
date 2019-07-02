package com.backgom.odyssey.converter;

import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class KeywordSearchHistoryDtoConverter {

	public KeywordSearchHistoryDto convertDtoFromEntity(KeywordSearchHistoryEntity sourceEntity) {

		if (Objects.isNull(sourceEntity)) {
			return null;
		}

		return new KeywordSearchHistoryDto()
				.setId(sourceEntity.getId())
				.setKeyword(sourceEntity.getKeyword())
				.setMemberId(sourceEntity.getMemberId())
				.setCount(sourceEntity.getCount())
				.setCreateAt(sourceEntity.getCreateAt())
				.setModifiedAt(sourceEntity.getModifiedAt())
				.setCreatedBy(sourceEntity.getCreatedBy())
				.setModifiedBy(sourceEntity.getModifiedBy());
	}

	public KeywordSearchHistoryEntity convertToNewEntity(KeywordSearchHistoryDto sourceDto) {
		KeywordSearchHistoryEntity entity = new KeywordSearchHistoryEntity();
		BeanUtils.copyProperties(sourceDto, entity);
		return entity;
	}

	public List<KeywordSearchHistoryDto> convertFromEntities(List<KeywordSearchHistoryEntity> sourceEntities) {
		if (Objects.isNull(sourceEntities)) {
			return null;
		}

		return sourceEntities.stream()
				.map(this::convertDtoFromEntity)
				.collect(Collectors.toList());
	}
}
