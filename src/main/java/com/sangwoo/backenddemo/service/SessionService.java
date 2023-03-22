package com.sangwoo.backenddemo.service;

public interface SessionService {
	public String createAccessToken(String id);
	
	public String createRefreshToken(String id);
	
	public String hashSHA512(String password);
	
	public String getIdByToken(String token);
}
