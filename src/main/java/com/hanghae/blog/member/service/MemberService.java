package com.hanghae.blog.member.service;

import java.util.List;
import java.util.stream.Collectors;

import com.hanghae.blog.member.dto.MemberResponseDto;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberResponseDto findMember(Long id) {
		Member foundMember = memberRepository.findById(id)
			.orElseThrow(() -> new NullPointerException("회원 상세 조회 실패"));

		return new MemberResponseDto(foundMember);
	}

	public List<MemberResponseDto> findAllMember() {
		List<Member> memberList = memberRepository.findAll();

		List<MemberResponseDto> responseList = memberList.stream()
			.map(m -> new MemberResponseDto(m))
			.collect(Collectors.toList());

		return responseList;
	}

}
