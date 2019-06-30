package com.backgom.odyssey.configuration;

import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.service.MemberCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InitializeDataLoader implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private MemberCommandService memberCommandService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		seedData();
	}

	private void seedData() {
		MemberDto testMember1 = new MemberDto();
		testMember1.setMemberId("windsp7");
		testMember1.setMemberPassword("1234");
		testMember1.setMemberRole("USER");
		memberCommandService.saveMember(testMember1);

		MemberDto testMember2 = new MemberDto();
		testMember2.setMemberId("kakao");
		testMember2.setMemberPassword("4321");
		testMember2.setMemberRole("USER");
		memberCommandService.saveMember(testMember2);
	}

}
