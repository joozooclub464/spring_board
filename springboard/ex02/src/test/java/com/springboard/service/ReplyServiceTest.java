package com.springboard.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboard.domain.Criteria;
import com.springboard.domain.ReplyDTO;

//import com.springboard.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTest {
	
	
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
	//service test
//	@Test
//	public void serviceTest() {
//		assertNotNull(service);
//		log.info(service);
//	}
	//servicetest //com.springboard.service.ReplyServiceImpl@73ad4ecc serviceImpl객체가 잘 들어온것 확인 可
	
	
//	@Test
//	public void registTest() {
//		ReplyDTO reply = new ReplyDTO();
//		reply.setBoardnum(10L);
//		reply.setReplywriter("apple");
//		reply.setReplycontents("댓글댓글대앳글");
//		
//		service.regist(reply); //객체넘김
//		log.info(reply.getReplynum());
//	}
	
	@Test
	public void getListTest() {
		Criteria cri = new Criteria();
		cri.setPagenum(1);
		cri.setAmount(10);
		log.info(service.getList(cri, 10L));
	}

}
