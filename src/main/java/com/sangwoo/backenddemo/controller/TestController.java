package com.sangwoo.backenddemo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangwoo.backenddemo.model.Member;
import com.sangwoo.backenddemo.repository.MemberRepository;

@RestController
public class TestController {
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("test")
	public Iterable<Member> test() {
		memberRepository.deleteAll();
		
		Member member1 = new Member();
		member1.setUserId("hong");
		member1.setPassword("1234");
		member1.setName("홍길동");
		member1.setCreateDate(new Date());
		member1.setUpdateDate(new Date());
		
		memberRepository.save(member1);
		
		Member member2 = new Member();
		member2.setUserId("kim");
		member2.setPassword("12345");
		member2.setName("김유신");
		member2.setCreateDate(new Date());
		member2.setUpdateDate(new Date());
		
		memberRepository.save(member2);
		
		member2.setPassword("1111");
		
		Iterable<Member> memberList = memberRepository.findAll();
		
		return memberList;
	}
}
