package com.sangwoo.backenddemo.service;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class SessionServiceImpl implements SessionService {
	private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);
	
	private static final long ACCESS_TOKEN_EXP = 30L * 60L * 1000L;
	
	private static final long REFRESH_TOKEN_EXP = 24L * 60L * 60L * 1000L;
	
	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	@Override
	public String createAccessToken(String id) {
		return createToken(id, ACCESS_TOKEN_EXP);
	}

	@Override
	public String createRefreshToken(String id) {
		return createToken(id, REFRESH_TOKEN_EXP);
	}
	
	@Override
	public String hashSHA512(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(password.getBytes("UTF-8"));
			
			Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(digest.digest());
		} catch (Exception e) {
			logger.error("사용자가 입력한 비밀번호를 SHA512 알고리즘을 이용하여 해시값으로 변경하는 도중에 오류가 발생하였습니다.");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "처리 도중에 에러가 발생하였습니다.");
		}
	}
	
	@Override
	public String getIdByToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}
	
	private String createToken(String id, long exp) {
		return Jwts.builder()
				.setSubject(id)
				.setIssuedAt(new Date())
				.setExpiration(createExpDate(exp))
				.signWith(key)
				.compact();
	}
	
	private Date createExpDate(long exp) {
		return new Date(new Date().getTime() + exp);
	}
}
