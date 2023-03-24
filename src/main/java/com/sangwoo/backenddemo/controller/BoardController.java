package com.sangwoo.backenddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sangwoo.backenddemo.dto.BoardAddDto;
import com.sangwoo.backenddemo.model.Board;
import com.sangwoo.backenddemo.model.Member;
import com.sangwoo.backenddemo.service.BoardService;
import com.sangwoo.backenddemo.service.UserService;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("board")
	public Page<Board> getBoardList(@RequestParam int page) {
		return boardService.getBoardList(page);
	}
	
	@GetMapping("board/{seq}")
	public Board getBoard(@PathVariable("seq") int seq) {
		return boardService.getBoard(seq);
	}
	
	@PostMapping("board")
	public String createBoard(@RequestBody BoardAddDto boardAddDto) {
		Board board = new Board();
		
		String title = boardAddDto.getTitle();
		board.setTitle(title);
		
		String content = boardAddDto.getContent();
		board.setContent(content);
		
		String userId = boardAddDto.getUserId();
		Member member = userService.getMemberByUserId(userId);
		
		if (member == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 값을 받았습니다.");
		}
		
		board.setMember(member);
		
		boardService.createBoard(board);
		
		return "OK";
	}
}
