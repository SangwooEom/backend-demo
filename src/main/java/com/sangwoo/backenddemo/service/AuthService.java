package com.sangwoo.backenddemo.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface AuthService {
	public String createAccessToken(String id);
	
	public String createRefreshToken(String id);
	
	public String hashSHA512(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
