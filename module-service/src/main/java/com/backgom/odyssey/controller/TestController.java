package com.backgom.odyssey.controller;

import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.service.MemberCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private MemberCommandService commandService;

	@RequestMapping("api/createUser")
	public void createUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();

		MemberDto dto = new MemberDto();
		dto.setMemberId("windsp7");
		dto.setMemberPassword("1234");
		dto.setMemberRole("USER");
//		dto.setCreatedBy(principal.toString());
//		dto.setModifiedBy(principal.toString());
//		dto.setCreateAt(new Date(System.currentTimeMillis()));
//		dto.setModifiedAt(new Date(System.currentTimeMillis()));

		MemberDto dto1 = new MemberDto();
		dto1.setMemberId("kakao");
		dto1.setMemberPassword("4321");
		dto1.setMemberRole("USER");
//		dto1.setCreatedBy(principal.toString());
//		dto1.setModifiedBy(principal.toString());
//		dto1.setCreateAt(new Date(System.currentTimeMillis()));
//		dto1.setModifiedAt(new Date(System.currentTimeMillis()));

		commandService.saveMember(dto);
		commandService.saveMember(dto1);
	}

}
