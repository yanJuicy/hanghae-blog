package com.hanghae.blog.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class JoinMemberRequestDto {

    private final String username;
    private final String password;

}
