package com.springboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboard.domain.ReplyDTO;
import com.springboard.service.ReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RequestMapping("/reply/*")
@Log4j
@RestController // : JSON 형태로 객체 데이터를 반환하기위한 Controller
public class ReplyController {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
	
	
	//ResponseEntity : 서버의 상태코드, 응답메시지, 데이터 등을 담을 수 있는 타입
	//consumes : 이 메소드가 호출될 때 소비할 데이터의 타입(넘겨지는 body(body태그아님)의 데이터 타입)
	//@RequestBody : 넘겨지는 body의 데이터 타입을 해석해서 해당 파라미터에 채워넣기
	@PostMapping(value = "/regist", consumes = "application/json") //consumes : 어떤 데이터를 소비할지 적어주는 것
	public ResponseEntity<String> regist(@RequestBody ReplyDTO reply) {
		//ResponseEntity<String> : 성공인지 실패인지 담아주는것
		//데이터를 json으로 바꿔서 넘겨줬으므로 매개변수가 아닌 body로 날아온다
		boolean check = service.regist(reply);
		return check ? new ResponseEntity<String>("sucess",HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
