package com.backgom.odyssey.repository;

import com.backgom.odyssey.entity.MemberEntity;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository repository;

	@Test
	public void testCustomerRepository() {
		MemberEntity member = new MemberEntity()
				.setId(1L)
				.setMemberId("Test")
				.setMemberPassword("Test");
		repository.save(member);

		List<MemberEntity> result = (List<MemberEntity>) repository.findAll();

		MemberEntity memberResultEntity = result.get(0);
		assertThat(memberResultEntity.getId()).isEqualTo(1L);
	}

	@After
	public void deleteAll() {
		repository.deleteAll();
	}

}
