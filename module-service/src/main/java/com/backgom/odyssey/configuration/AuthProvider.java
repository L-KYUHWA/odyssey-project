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

//		MemberDto findedMember = memberQueryService.findMember(memberDto);
//		if (Objects.isNull(findedMember)) {
//			log.error("{} is not exist or password is not equals", id);
//			return null;
//		}
//
//		if (findedMember.getMemberPassword().equals(pw)) {
//			log.error("{} is not exist or password is not equals", id);
//			return null;
//		}

		List<GrantedAuthority> authList = new ArrayList<>();
		/**
		 * Role 처리 필요, 일단 임의로 USER Role을 부여한다.
		 **/
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new MyAuthentication(id, pw, authList, memberDto);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
