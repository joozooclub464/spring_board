package com.springboard.service;

import com.springboard.domain.Criteria;
import com.springboard.domain.ReplyDTO;
import com.springboard.domain.ReplyPageDTO;

public interface ReplyService {
	boolean regist(ReplyDTO reply);
	ReplyPageDTO getList(Criteria cri, Long boardnum);
	
	
}

