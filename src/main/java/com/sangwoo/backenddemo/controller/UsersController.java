package com.sangwoo.backenddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangwoo.backenddemo.dto.JoinDto;
import com.sangwoo.backenddemo.model.Member;
import com.sangwoo.backenddemo.service.SessionService;
import com.sangwoo.backenddemo.service.UserService;

@RestController
public class UsersController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping("users")
	public String join(JoinDto joinDto) {
		Member member = new Member();
		
		String userId = joinDto.getId();
		member.setUserId(userId);
		
		String password = sessionService.hashSHA512(joinDto.getPassword());
		member.setPassword(password);
		
		String name = joinDto.getName();
		member.setName(name);
		
		userService.createMember(member);
		
		return "OK";
	}
	
	@GetMapping("users/duplicate/{userId}")
	public boolean duplicateUserId(@PathVariable("userId") String userId) {
		return userService.existsUserId(userId);
	}
}
