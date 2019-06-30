package com.backgom.odyssey.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "keyword_search_history")
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class KeywordSearchHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false)
	private String memberId;

	@Column
	private String keyword;

	@Column
	private Long count;

	@CreatedDate
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@LastModifiedDate
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;

	@CreatedBy
	@Column(length = 50, updatable = false)
	private String createdBy;

	@CreatedDate
	@Column(length = 50)
	private String modifiedBy;

}
