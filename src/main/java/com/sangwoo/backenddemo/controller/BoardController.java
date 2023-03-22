package com.sangwoo.backenddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangwoo.backenddemo.model.Board;
import com.sangwoo.backenddemo.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("board")
	public Page<Board> getBoardList() {
		
	}
}
