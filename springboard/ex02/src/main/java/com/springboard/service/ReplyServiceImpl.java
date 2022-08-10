package com.springboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboard.domain.ReplyDTO;
import com.springboard.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {
	

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	
	@Override
	public boolean regist(ReplyDTO reply) {
		log.info("------regist------");
		
		return 1 == mapper.insert(reply);
	}
}