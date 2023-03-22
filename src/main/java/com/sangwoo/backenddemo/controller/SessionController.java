package com.sangwoo.backenddemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sangwoo.backenddemo.dto.LoginDto;
import com.sangwoo.backenddemo.dto.TokenDto;
import com.sangwoo.backenddemo.service.SessionService;
import com.sangwoo.backenddemo.service.UserService;

@RestController
public class SessionController {
	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("session")
	public TokenDto login(@RequestBody LoginDto loginDto) {
		String id = loginDto.getId();
		String password = sessionService.hashSHA512(loginDto.getPassword());
		
		if (!userService.validateUser(id, password)) {
			logger.error("비밀번호가 틀렸습니다.");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 실패하였습니다.");
		}
		
		String accessToken = sessionService.createAccessToken(id);
		String refreshToken = sessionService.createRefreshToken(id);
		
		TokenDto tokenDto = new TokenDto();
		
		tokenDto.setAccessToken(accessToken);
		tokenDto.setRefreshToken(refreshToken);
		
		return tokenDto;
	}
	
	@PutMapping("session")
	public TokenDto reissue(@RequestHeader("Authorization") String refreshToken) {
		String id = sessionService.getIdByToken(refreshToken);
		String accessToken = sessionService.createAccessToken(id);
		
		TokenDto tokenDto = new TokenDto();
		
		tokenDto.setAccessToken(accessToken);
		tokenDto.setRefreshToken(refreshToken);
		
		return tokenDto;
	}
}
