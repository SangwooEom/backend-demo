package com.sangwoo.backenddemo.service;

import com.sangwoo.backenddemo.model.Member;

public interface UserService {
	public void createMember(Member member);
	
	public boolean validateUser(String userId, String password);
	
	public boolean existsUserId(String userId);
	
	public Member getMemberByUserId(String userId);
}
