package com.backgom.odyssey.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class MemberDto {
	private String memberId;
	private String memberPassword;
	private MemberRole memberRole;
	private Date createAt;
	private Date modifiedAt;
	private String createdBy;
	private String modifiedBy;
}
