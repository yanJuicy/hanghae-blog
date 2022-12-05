package com.hanghae.blog.common.custom;

public class NotEnoughArgumentException extends RuntimeException{

	private static final String MESSAGE = "요청 인자가 충분하지 않습니다.";

	public NotEnoughArgumentException() {
		super(MESSAGE);
	}

}
