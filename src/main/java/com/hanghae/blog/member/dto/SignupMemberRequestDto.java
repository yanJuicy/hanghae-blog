package com.hanghae.blog.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupMemberRequestDto {
    private static final String USERNAME_PATTERN = "^[a-z0-9]+";
    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-_+<>@\\#$%&\\\\\\=\\(\\'\\\"]+";

    @Size(min = 4, max = 10)
    @Pattern(regexp = USERNAME_PATTERN)
    private String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    private boolean admin;

    private String adminToken;
}
