package com.hanghae.blog.common.response;

import lombok.Getter;

@Getter
public class GenericResponseDto<T> extends ResponseDto {
	private T data;

    public GenericResponseDto(ResponseMessage responseMessage, T data) {
        super(responseMessage);
		this.data = data;
    }

}
