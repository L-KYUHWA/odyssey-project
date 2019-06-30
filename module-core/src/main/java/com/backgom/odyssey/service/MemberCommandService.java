package com.backgom.odyssey.service;

import com.backgom.odyssey.converter.MemberDtoConverter;
import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.entity.MemberEntity;
import com.backgom.odyssey.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class MemberCommandService {

	@Autowired
	private MemberDtoConverter converter;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public MemberEntity saveMember(MemberDto memberDto) {
		MemberEntity entity = converter.convertToNewEntity(memberDto);
		entity.setMemberPassword(bCryptPasswordEncoder.encode(entity.getMemberPassword()));
		return memberRepository.save(entity);
	}

}
