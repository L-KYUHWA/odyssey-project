package com.backgom.odyssey.converter;

import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.entity.MemberEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MemberDtoConverterTest {

	@InjectMocks
	private MemberDtoConverter sut;

	@Test
	public void convertDtoFromEntity() {
		MemberEntity mock = mockEntity();
		MemberDto result = sut.convertDtoFromEntity(mock);

		assertThat(result.getMemberId()).isEqualTo(mock.getMemberId());
		assertThat(result.getMemberPassword()).isEqualTo(mock.getMemberPassword());
	}

	@Test
	public void convertToNewEntity() {
		MemberDto mock = mockDto();
		MemberEntity result = sut.convertToNewEntity(mock);

		assertThat(result.getMemberId()).isEqualTo(mock.getMemberId());
		assertThat(result.getMemberPassword()).isEqualTo(mock.getMemberPassword());
	}

	private MemberEntity mockEntity() {
		return new MemberEntity()
				.setId(1L)
				.setMemberId("testMemberId")
				.setMemberPassword("testMemberPw");
	}

	private MemberDto mockDto() {
		return new MemberDto()
				.setMemberId("testMemberId")
				.setMemberPassword("testMemberPw");
	}

}