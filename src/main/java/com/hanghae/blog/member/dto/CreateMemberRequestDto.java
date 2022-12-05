package com.hanghae.blog.member.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class CreateMemberRequestDto {

    @Size(min = 4, max = 10)
    private final String username;

    @Size(min = 8, max = 15)
    private final String password;

    public CreateMemberRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
