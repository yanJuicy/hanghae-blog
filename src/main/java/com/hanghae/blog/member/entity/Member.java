package com.hanghae.blog.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hanghae.blog.member.dto.SignupMemberRequestDto;
import lombok.Getter;

@Getter
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberRole role;

	protected Member() {}

	public Member(SignupMemberRequestDto request, MemberRole role) {
		this.username = request.getUsername();
		this.password = request.getPassword();
		this.role = role;
	}

}
