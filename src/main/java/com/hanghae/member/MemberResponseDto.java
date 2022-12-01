package com.hanghae.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResponseDto {
	private final Long id;
	private final String name;
	private final String email;
	private final String pw;

	public MemberResponseDto(final Member member) {
		this.id = member.getId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.pw = member.getPw();
	}
}
