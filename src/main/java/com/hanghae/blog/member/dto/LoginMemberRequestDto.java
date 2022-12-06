package com.hanghae.blog.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class LoginMemberRequestDto {

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+")
    private final String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[A-Za-z0-9]+")
    private final String password;
}
