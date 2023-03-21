package com.sangwoo.backenddemo.controller;

import org.springframework.web.bind.annotation.PostMapping;

import com.sangwoo.backenddemo.dto.JoinDto;

public class UsersController {
	@PostMapping("users")
	public String join(JoinDto joinDto) {
		
	}
}
