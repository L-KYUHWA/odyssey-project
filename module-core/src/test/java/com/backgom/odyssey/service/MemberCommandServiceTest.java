package com.backgom.odyssey.service;

import com.backgom.odyssey.converter.MemberDtoConverter;
import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.entity.MemberEntity;
import com.backgom.odyssey.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberCommandServiceTest {

	private static final String TEST_MEMBER_ID = "testMember";
	private static final String TEST_MEMBER_PW = "testPassword";
	private static final String TEST_MEMBER_ROLE = "testRole";
	@InjectMocks
	private MemberCommandService sut;
	@Mock
	private MemberDtoConverter converter;
	@Mock
	private MemberRepository repository;
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Before
	public void setUp() throws Exception {
		when(repository.save(any(MemberEntity.class))).thenReturn(new MemberEntity());
		when(converter.convertToNewEntity(any(MemberDto.class))).thenReturn(new MemberEntity());
		when(converter.convertDtoFromEntity(any(MemberEntity.class))).thenReturn(new MemberDto());
	}

	@Test
	public void saveMember() {
		MemberDto result = sut.saveMember(mockDto());
		assertThat(result).isNotNull();
	}

	private MemberDto mockDto() {
		return new MemberDto()
				.setMemberId(TEST_MEMBER_ID)
				.setMemberPassword(TEST_MEMBER_PW)
				.setMemberRole(TEST_MEMBER_ROLE);
	}
}