package com.hanghae.blog.common.exception.custom;

public class IllegalJwtException extends RuntimeException{

	public IllegalJwtException(String msg) {
		super(msg);
	}

}
