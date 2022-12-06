package com.hanghae.blog.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtService {

	private final JwtUtil jwtUtil;

	public Claims getTokenClaim(HttpServletRequest servletRequest) {
		String token = jwtUtil.resolveToken(servletRequest);
		Claims claim = jwtUtil.getUserInfoFromToken(token);
		return claim;
	}

	public void addTokenToHeader(HttpServletResponse servletResponse, String payload) {
		servletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(payload));
	}


}
