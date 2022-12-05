package com.hanghae.blog.member.service;

import com.hanghae.blog.member.dto.JoinMemberRequestDto;
import com.hanghae.blog.member.dto.JoinMemberResponseDto;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.hanghae.blog.common.exception.ExceptionMessage.ALREADY_EXIST_MEMBER_EXCEPTION_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.CREATE_MEMBER;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public JoinMemberResponseDto createMember(JoinMemberRequestDto request) {
        String username = request.getUsername();

        Optional<Member> dbUser = memberRepository.findByUsername(username);
        if (dbUser.isPresent()) {
            throw new IllegalArgumentException(ALREADY_EXIST_MEMBER_EXCEPTION_MSG.getMsg());
        }

        memberRepository.save(new Member(request));

        return new JoinMemberResponseDto(CREATE_MEMBER);
    }

}