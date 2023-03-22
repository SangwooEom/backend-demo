package com.sangwoo.backenddemo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.sangwoo.backenddemo.model.Board;
import com.sangwoo.backenddemo.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public Page<Board> getBoardList(int page) {
		PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("seq").descending());
		return boardRepository.findAll(pageRequest);
	}

	@Override
	public Optional<Board> getBoard(int seq) {
		return boardRepository.findById(seq)
	}

	@Override
	public void createBoard(Board board) {
		board.setCreateDate(new Date());
		board.setUpdateDate(new Date());
		boardRepository.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		board.setUpdateDate(new Date());
		boardRepository.save(board);
	}

	@Override
	public void deleteBoard(int seq) {
		// TODO Auto-generated method stub
		
	}

}
