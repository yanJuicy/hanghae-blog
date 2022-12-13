package com.hanghae.blog.common.response;

import lombok.Getter;

@Getter
public class DataResponseDto<T> extends ResponseDto {
	private T data;

    public DataResponseDto(ResponseMessage responseMessage, T data) {
        super(responseMessage);
		this.data = data;
    }

}
