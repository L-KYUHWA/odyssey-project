package com.backgom.odyssey.converter;

import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.entity.MemberEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MemberDtoConverter {

	public MemberDto convertDtoFromEntity(MemberEntity sourceEntity) {

		if (Objects.isNull(sourceEntity)) {
			return null;
		}

		return new MemberDto()
				.setMemberId(sourceEntity.getMemberId())
				.setMemberPassword(sourceEntity.getMemberPassword())
				.setCreateAt(sourceEntity.getCreateAt())
				.setModifiedAt(sourceEntity.getModifiedAt())
				.setCreatedBy(sourceEntity.getCreatedBy())
				.setModifiedBy(sourceEntity.getModifiedBy());
	}

	public MemberEntity convertToNewEntity(MemberDto sourceDto) {
		MemberEntity entity = new MemberEntity();
		BeanUtils.copyProperties(sourceDto, entity);
		return entity;
	}

}
