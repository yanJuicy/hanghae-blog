package com.hanghae.blog.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class JoinMemberRequestDto {
    private static final String USERNAME_PATTERN = "^[a-z0-9]+[a-z]+[0-9]+";
    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-_+<>@\\#$%&\\\\\\=\\(\\'\\\"]+";

    @Size(min = 4, max = 10)
    @Pattern(regexp = USERNAME_PATTERN)
    private final String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = PASSWORD_PATTERN)
    private final String password;

}
