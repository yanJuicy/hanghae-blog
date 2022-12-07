package com.hanghae.blog.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginMemberRequestDto {

    private final String username;
    private final String password;
}
