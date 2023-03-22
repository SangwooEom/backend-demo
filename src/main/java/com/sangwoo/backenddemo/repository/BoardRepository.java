package com.sangwoo.backenddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.sangwoo.backenddemo.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
}
