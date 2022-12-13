package com.hanghae.blog.member.controller;

import com.hanghae.blog.common.response.ResponseDto;
import com.hanghae.blog.member.dto.LoginMemberRequestDto;
import com.hanghae.blog.member.dto.SignupMemberRequestDto;
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
	public ResponseDto signupMember(@Valid @RequestBody SignupMemberRequestDto requestDto) {
		return memberService.createMember(requestDto);
	}

	@PostMapping("/login")
	public ResponseDto loginMember(@Valid @RequestBody LoginMemberRequestDto requestDto,
								   HttpServletResponse response) {
		return memberService.login(requestDto, response);
	}

}

