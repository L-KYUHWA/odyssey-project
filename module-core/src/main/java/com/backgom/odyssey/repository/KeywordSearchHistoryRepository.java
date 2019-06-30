package com.backgom.odyssey.repository;

import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordSearchHistoryRepository extends CrudRepository<KeywordSearchHistoryEntity, Long> {
	KeywordSearchHistoryEntity findByKeywordAndMemberId(String keyword, String memberId);
//	List<KeywordSearchHistoryEntity> findAllByOrOrderByCount();
}
