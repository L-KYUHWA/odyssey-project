package com.backgom.odyssey.configuration;

import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.service.MemberQueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthProviderTest {

	@InjectMocks
	private AuthProvider sut;
	@Mock
	private MemberQueryService memberQueryService;
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Mock
	private Authentication authentication;

	@Before
	public void setUp() throws Exception {
		when(authentication.getName()).thenReturn("TestId");
		when(authentication.getCredentials()).thenReturn("TestPw");
	}

	@Test
	public void authenticate_not_Member() {
		Authentication result = sut.authenticate(authentication);
		assertThat(result).isNull();
	}

	@Test
	public void authenticate_wrong_Password() {
		when(memberQueryService.findByMember(any())).thenReturn(mockMemberDto());
		Authentication result = sut.authenticate(authentication);
		assertThat(result).isNull();
	}

	@Test
	public void authenticate_login_success() {
		when(memberQueryService.findByMember(any())).thenReturn(mockMemberDto());
		when(bCryptPasswordEncoder.matches(anyString(), anyString())).thenReturn(Boolean.TRUE);

		Authentication result = sut.authenticate(authentication);
		assertThat(result).isNotNull();
		assertThat(result.getPrincipal().toString()).isEqualTo(authentication.getName());
	}

	private MemberDto mockMemberDto() {
		return new MemberDto()
				.setMemberId("TestId")
				.setMemberPassword("TestPw");
	}

}