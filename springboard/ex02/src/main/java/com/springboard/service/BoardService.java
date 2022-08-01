package com.springboard.service;

import java.util.List;

import com.springboard.domain.BoardDTO;
import com.springboard.domain.Criteria;

public interface BoardService {
	//list를 검색해서 가져오는기능
	public List<BoardDTO> getList(Criteria cri);
		//BoardController에서 넘어와서 Criteria타입의 cri로 매개변수 넣어주기
		//아래 getTotal도 Criteria타입으로 매개변수 넣기

	public int getTotal(Criteria cri);
}
