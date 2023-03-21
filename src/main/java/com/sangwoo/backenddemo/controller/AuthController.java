package com.sangwoo.backenddemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sangwoo.backenddemo.dto.LoginDto;
import com.sangwoo.backenddemo.dto.TokenDto;
import com.sangwoo.backenddemo.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private AuthService authService;
	
	@PostMapping("login")
	public TokenDto login(@RequestBody LoginDto loginDto) {
		String id = loginDto.getId();
		String password = authService.hashSHA512(loginDto.getPassword());
		
		if (!authService.checkLogin(id, password)) {
			logger.error("비밀번호가 틀렸습니다.");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 실패하였습니다.");
		}
		
		String accessToken = authService.createAccessToken(id);
		String refreshToken = authService.createRefreshToken(id);
		
		TokenDto tokenDto = new TokenDto();
		
		tokenDto.setAccessToken(accessToken);
		tokenDto.setRefreshToken(refreshToken);
		
		return tokenDto;
	}
	
	@PostMapping("reissue")
	public TokenDto reissue(@RequestHeader("Authorization") String refreshToken) {
		String id = authService.getIdByToken(refreshToken);
		String accessToken = authService.createAccessToken(id);
		
		TokenDto tokenDto = new TokenDto();
		
		tokenDto.setAccessToken(accessToken);
		tokenDto.setRefreshToken(refreshToken);
		
		return tokenDto;
	}
	
	@PostMapping("join")
	public 
}
