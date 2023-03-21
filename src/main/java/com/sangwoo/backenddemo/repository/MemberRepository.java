package com.sangwoo.backenddemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.sangwoo.backenddemo.model.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
}
