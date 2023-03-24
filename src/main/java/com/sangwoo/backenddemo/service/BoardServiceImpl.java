package com.sangwoo.backenddemo.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.sangwoo.backenddemo.model.Board;
import com.sangwoo.backenddemo.repository.BoardRepository;
import com.sangwoo.backenddemo.type.YNType;

public class BoardServiceImpl implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public Page<Board> getBoardList(int page) {
		PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("seq").descending());
		return boardRepository.findAll(pageRequest);
	}

	@Override
	public Board getBoard(int seq) {
		return boardRepository.findById(seq).orElse(null);
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
		Board board = boardRepository.findById(seq).orElse(null);
		
		if (board == null) {
			logger.error("입혁한 게시글 번호 {}는 존재하지 않습니다.");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 에러가 발생하였습니다.");
		}
		
		board.setDeleteYn(YNType.Y);
		boardRepository.save(board);
	}
}
