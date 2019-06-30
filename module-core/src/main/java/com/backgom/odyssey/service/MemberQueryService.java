package com.backgom.odyssey.service;

import com.backgom.odyssey.converter.MemberDtoConverter;
import com.backgom.odyssey.dto.MemberDto;
import com.backgom.odyssey.entity.MemberEntity;
import com.backgom.odyssey.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberQueryService {

	@Autowired
	private MemberDtoConverter converter;
	@Autowired
	private MemberRepository memberRepository;

	public MemberDto findMember(MemberDto memberDto) {
		MemberEntity entity = memberRepository.findByMemberIdAndMemberPassword(memberDto.getMemberId(), memberDto.getMemberPassword());
		return converter.convertDtoFromEntity(entity);
	}

	public Iterable<MemberEntity> findAllMembers() {
		Iterable<MemberEntity> all = memberRepository.findAll();
		all.forEach(aa -> log.warn(aa.toString()));
		return all;
	}
}
