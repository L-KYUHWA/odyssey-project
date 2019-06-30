package com.backgom.odyssey.repository;

import com.backgom.odyssey.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {
	MemberEntity findByMemberIdAndMemberPassword(String memberId, String memberPassword);
}
