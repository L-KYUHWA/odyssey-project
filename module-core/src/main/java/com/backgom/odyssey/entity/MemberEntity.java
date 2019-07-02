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
@Table(name = "members")
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false, unique = true)
	private String memberId;

	@Column(length = 200, nullable = false)
	private String memberPassword;

	@Column(length = 100, nullable = false)
	private String memberRole;

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
