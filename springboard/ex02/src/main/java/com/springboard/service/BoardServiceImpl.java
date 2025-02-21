package com.springboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboard.domain.BoardDTO;
import com.springboard.domain.Criteria;
import com.springboard.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		log.info("BoardService - getList");
		List<BoardDTO> list = mapper.getList(cri);
		log.info(list);
		return list;
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}
	
	@Override
	public void regist(BoardDTO board) {
		mapper.insert_with_bno(board);
	}
	
	@Override
	public BoardDTO get(Long boardnum) {
		return mapper.read(boardnum);
	}
	
	@Override
	public boolean modify(BoardDTO board) {
		return 1 == mapper.update(board);
		//성공인지 실패인지를 넘겨주기 위해 boolean으로 리턴
	}
	
	@Override
	public boolean remove(Long boardnum) {
		return 1 == mapper.delete(boardnum);
	}
	
	
}
