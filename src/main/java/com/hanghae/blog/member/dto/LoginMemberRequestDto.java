package com.hanghae.blog.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class LoginMemberRequestDto {

    @Size(min = 4, max = 10)
    private final String username;

    @Size(min = 8, max = 15)
    private final String password;
}
