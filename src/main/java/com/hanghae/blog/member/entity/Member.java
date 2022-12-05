package com.hanghae.blog.member.entity;

import javax.persistence.*;

import com.hanghae.blog.member.dto.CreateMemberRequestDto;
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

	public Member(CreateMemberRequestDto request) {
		this.username = request.getUsername();
		this.password = request.getPassword();
	}

}
