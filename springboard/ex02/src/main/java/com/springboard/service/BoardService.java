package com.springboard.service;

import java.util.List;

import com.springboard.domain.BoardDTO;
import com.springboard.domain.Criteria;

public interface BoardService {
	public List<BoardDTO> getList(Criteria cri);

	public int getTotal(Criteria cri);
	
	public void regist(BoardDTO board);
	
	public BoardDTO get(Long boardnum);

	public boolean modify(BoardDTO board);

	public boolean remove(Long boardnum);

}
