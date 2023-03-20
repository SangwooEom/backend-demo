package com.sangwoo.backenddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sangwoo.backenddemo.dto.LoginDto;
import com.sangwoo.backenddemo.dto.TokenDto;
import com.sangwoo.backenddemo.service.AuthService;

@RestController
public class AuthController {
	@Autowired
	private AuthService authService;
	
	public TokenDto login(LoginDto loginDto) {
		String id = loginDto.getId();
	}
}
