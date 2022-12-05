package com.hanghae.blog.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hanghae.blog.member.dto.JoinMemberRequestDto;
import lombok.Getter;

@Getter
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	protected Member() {}

	public Member(JoinMemberRequestDto request) {
		this.username = request.getUsername();
		this.password = request.getPassword();
	}

}
