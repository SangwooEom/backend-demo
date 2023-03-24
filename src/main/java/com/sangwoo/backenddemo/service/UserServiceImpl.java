package com.sangwoo.backenddemo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangwoo.backenddemo.model.Member;
import com.sangwoo.backenddemo.repository.MemberRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public void createMember(Member member) {
		member.setCreateDate(new Date());
		member.setUpdateDate(new Date());
		memberRepository.save(member);
	}
	
	@Override
	public boolean validateUser(String userId, String password) {
		return memberRepository.existsByUserIdAndPassword(userId, password);
	}
	
	@Override
	public boolean existsUserId(String UserId) {
		return memberRepository.existsById(UserId);
	}
	
	@Override
	public Member getMemberByUserId(String userId) {
		return memberRepository.findById(userId).orElse(null);
	}
}
