package com.backgom.odyssey.configuration;

import com.backgom.odyssey.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
public class OdysseyAuthentication extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private MemberDto member;

	public OdysseyAuthentication(String id, String pw, List<GrantedAuthority> authList, MemberDto member) {
		super(id, pw, authList);
		this.member = member;
	}

}
