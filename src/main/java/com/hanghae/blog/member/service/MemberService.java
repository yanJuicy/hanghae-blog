package com.hanghae.blog.member.service;

import static com.hanghae.blog.common.exception.ExceptionMessage.ALREADY_EXIST_MEMBER_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.NO_EXIST_MEMBER_EXCEPTION_MSG;
import static com.hanghae.blog.common.exception.ExceptionMessage.WRONG_PASSWORD_EXCEPTION_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.CREATE_MEMBER_SUCCESS_MSG;
import static com.hanghae.blog.common.response.ResponseMessage.LOGIN_MEMBER_SUCCESS_MSG;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.blog.jwt.JwtService;
import com.hanghae.blog.member.dto.JoinMemberRequestDto;
import com.hanghae.blog.member.dto.JoinMemberResponseDto;
import com.hanghae.blog.member.dto.LoginMemberRequestDto;
import com.hanghae.blog.member.dto.LoginMemberResponseDto;
import com.hanghae.blog.member.entity.Member;
import com.hanghae.blog.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Transactional
    public JoinMemberResponseDto createMember(JoinMemberRequestDto request) {
        String username = request.getUsername();

        Optional<Member> dbUser = memberRepository.findByUsername(username);
        if (dbUser.isPresent()) {
            throw new IllegalArgumentException(ALREADY_EXIST_MEMBER_EXCEPTION_MSG.getMsg());
        }

        memberRepository.save(new Member(request));

        return new JoinMemberResponseDto(CREATE_MEMBER_SUCCESS_MSG);
    }

    @Transactional(readOnly = true)
    public LoginMemberResponseDto login(LoginMemberRequestDto request, HttpServletResponse response) {
        String username = request.getUsername();
        String password = request.getPassword();

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_MEMBER_EXCEPTION_MSG.getMsg()));

        if (isNotEqualPassword(member.getPassword(), password)) {
            throw new IllegalArgumentException(WRONG_PASSWORD_EXCEPTION_MSG.getMsg());
        }

        jwtService.addTokenToHeader(response, username);
        return new LoginMemberResponseDto(LOGIN_MEMBER_SUCCESS_MSG);
    }

    private boolean isNotEqualPassword(String a, String b) {
        return !a.equals(b);
    }

    public Member findMember(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException(NO_EXIST_MEMBER_EXCEPTION_MSG.getMsg()));

        return member;
    }
}