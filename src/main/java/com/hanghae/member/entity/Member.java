package com.hanghae.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Member {

	@Id
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String pw;


}
