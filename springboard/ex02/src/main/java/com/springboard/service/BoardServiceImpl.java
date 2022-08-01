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
// @Service : 서비스 객체임을 알려줌.이게 없으면 주입받을 수 없음 , root-context에 컴포넌트 스캔 달아주기
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
}
