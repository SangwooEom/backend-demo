package com.sangwoo.backenddemo.service;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthServiceImpl implements AuthService {	
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
	public String hashSHA512(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		digest.reset();
		digest.update(password.getBytes("UTF-8"));
		
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(digest.digest());
	}
	
	private String createToken(String id, long exp) {
		return Jwts.builder()
				.setIssuer(id)
				.setIssuedAt(new Date())
				.setExpiration(createExpDate(exp))
				.signWith(key)
				.compact();
	}
	
	private Date createExpDate(long exp) {
		return new Date(new Date().getTime() + exp);
	}
}
