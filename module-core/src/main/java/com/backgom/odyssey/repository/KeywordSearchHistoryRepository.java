package com.backgom.odyssey.repository;

import com.backgom.odyssey.dto.TopKeywordDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface KeywordSearchHistoryRepository extends JpaRepository<KeywordSearchHistoryEntity, Long> {

	@Query("SELECT new com.backgom.odyssey.dto.TopKeywordDto(h.keyword, SUM(h.count) as c) FROM KeywordSearchHistoryEntity as h GROUP BY h.keyword ORder By c DESC")
	List<TopKeywordDto> getTopKeywords(Pageable pageable);

	List<KeywordSearchHistoryEntity> findByMemberId(String memberId, Pageable pageable);

	KeywordSearchHistoryEntity findByKeywordAndMemberId(String keyword, String memberId);
}
