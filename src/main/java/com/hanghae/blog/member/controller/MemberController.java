package com.hanghae.blog.member.controller;

import com.hanghae.blog.member.dto.JoinMemberRequestDto;
import com.hanghae.blog.member.dto.JoinMemberResponseDto;
import com.hanghae.blog.member.dto.LoginMemberRequestDto;
import com.hanghae.blog.member.dto.LoginMemberResponseDto;
import com.hanghae.blog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/join")
	public JoinMemberResponseDto joinMember(@Valid @RequestBody JoinMemberRequestDto requestDto) {
		return memberService.createMember(requestDto);
	}

	@PostMapping("/login")
	public LoginMemberResponseDto loginMember(@Valid @RequestBody LoginMemberRequestDto requestDto, HttpServletResponse response) {
		return memberService.loginMember(requestDto, response);
	}

}

