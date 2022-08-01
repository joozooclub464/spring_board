package com.springboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboard.domain.Criteria;
import com.springboard.domain.PageDTO;
import com.springboard.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@GetMapping("/list")
	//스프링에서는 내부에 있는 필드를 똑같이 라라미터를 넘겨준다면 자동으로 객체화 가능
	//즉 매개변수에 DTO를 담을 수 있다.
	//매개변수에 Criteria를 담고 PageDTO에 cri 넘겨줌.
	//getList에도 cri넘겨주고 BoardService의 getList 수정해줌
	//getTotal도 cri 넘겨주기
	public void list(Criteria cri, Model model) {
		log.info("------list------");
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(cri), cri));
	}
}






