package com.hanghae.blog.member.controller;

import com.hanghae.blog.member.dto.CreateMemberRequestDto;
import com.hanghae.blog.member.dto.CreateMemberResponseDto;
import com.hanghae.blog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/join")
	public CreateMemberResponseDto joinMember(@RequestBody CreateMemberRequestDto requestDto) {
		return memberService.createMember(requestDto);
	}

}

