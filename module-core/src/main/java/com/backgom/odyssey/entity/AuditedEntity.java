package com.backgom.odyssey.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditedEntity implements Serializable {

	//MySql 기준 - BaseEntity 로 상속 받아 Entity 생성

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
	private String createdBy = "System";

	@CreatedDate
	@Column(length = 50)
	private String modifiedBy = "System";

}
