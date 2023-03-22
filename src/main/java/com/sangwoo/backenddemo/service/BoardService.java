package com.sangwoo.backenddemo.service;

import org.springframework.data.domain.Page;

import com.sangwoo.backenddemo.model.Board;

public interface BoardService {
	public Page<Board> getBoardList(int size);
	
	public Board getBoard(int seq);
	
	public void createBoard(Board board);
	
	public void updateBoard(Board board);
	
	public void deleteBoard(int seq);
}
