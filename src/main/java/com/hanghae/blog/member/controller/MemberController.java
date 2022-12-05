package com.hanghae.blog.member.controller;

import java.util.List;

import com.hanghae.blog.member.dto.MemberResponseDto;
import com.hanghae.blog.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	/**
	 * 한 회원의 userId가 주었을때 회원 정보를 조회하는 API
	 * @param id
	 */
	@GetMapping("/member/{id}")
	public MemberResponseDto getMemberInfo(@PathVariable Long id) {
		return memberService.findMember(id);
	}

	/**
	 * 회원의 전체 목록을 조회하는 API
	 */
	@GetMapping("/member")
	public List<MemberResponseDto> getMemberList() {
		return memberService.findAllMember();
	}

}
