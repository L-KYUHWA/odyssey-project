package com.backgom.odyssey.configuration;

import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.service.MemberQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component()
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	private MemberQueryService memberQueryService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String userPw = authentication.getCredentials().toString();
		return authenticate(userId, userPw);
	}

	private Authentication authenticate(String id, String pw) throws AuthenticationException {
		MemberDto memberDto = new MemberDto()
				.setMemberId(id)
				.setMemberPassword(bCryptPasswordEncoder.encode(pw));

		MemberDto foundMember = memberQueryService.findByMember(memberDto);

		if (Objects.isNull(foundMember)) {
			log.info("[AuthProvider] ID:{} is not exist", id);
			return null;
		}

		if (!bCryptPasswordEncoder.matches(pw, foundMember.getMemberPassword())) {
			log.info("[AuthProvider] ID:{} is password is not equals", id);
			return null;
		}

		//임시 role 부여
		List<GrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new OdysseyAuthentication(id, pw, authList, memberDto);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
