package com.springboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.domain.BoardDTO;
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
	public void list(Criteria cri, Model model) {
		log.info("------list------");
		model.addAttribute("list",service.getList(cri));
		model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
	}
	
	@GetMapping("/regist") 
	public void regist(@ModelAttribute("cri")Criteria cri, Model model) {
		//링크 하나가 파라미터로 날아옴 -> Criteria 하나를 받아서 -> Model에 추가해서 돌려줘야함
//		model.addAttribute("cri",cri); //Criteria는 내가 날아온 파라미터로 만든 cri야.
		//뷰단으로 이동(void)라서 자동으로 이동됨
	}
	
	@PostMapping("/regist")
	public String regist(BoardDTO board, RedirectAttributes ra) {
		//작성한 글을 BoardDTO로 받아오고
		//service에는 등록하는객체 만들고
		//Mapper에서 그걸 받아오고..이런 형식
		service.regist(board);
		//새롭게 등록한 게시글의 번호를 redirect로 같이 보내주려면
		//Model 대신에 RedirectAttributes를 사용
		ra.addFlashAttribute("wn",board.getBoardnum());
		
		//redirect: 접두어를 사용하게 되면 스프링 MVC가 자동으로 redirect로 처리해준다
		return "redirect:/board/list";
	}
	
	//아래의 메소드는 /get이나 /modify인 경우에 호출되는데
	//그 때 호출하는 uri대로 view를 찾을 것이다.
	//즉 /get으로 요청해서 호출됐다면 get.jsp를, /modify로 요청해서 호출됐다면 modify.jsp를 찾게된다.
	@GetMapping({"/get","/modify"})
	public void get(Long boardnum, @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("board",service.get(boardnum));
	}
	
//	@GetMapping("/modify")
//	public void modify(Long boardnum, @ModelAttribute("cri")Criteria cri, Model model) {
//		//수정하러 페이지 이동함.
//		//Long타입의 boardnum하나 넘어오고, ModelAttribute로 그대로 날아온 cri를 다시 추가해줌,
//		//수정할 게시글의 내용이 미리 써져있어야 하기 때문에 그걸 담아서 들고갈 Model도 필요함.
//		model.addAttribute("board",service.get(boardnum));
//		//수정하기 위해서는 수정 전 정보를 검색해서 들구가야 함.
//	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO board, Criteria cri, RedirectAttributes ra) {
		//db수정
		if(service.modify(board)) {
			ra.addFlashAttribute("mn",board.getBoardnum());
		}
		return "redirect:/board/list"+cri.getListLink(); //수정을 실제로 했음. 시스템의 변화 有 -> redirect
	}

	@PostMapping("/remove")
	public String remove(Long boardnum, Criteria cri, RedirectAttributes ra) {
		if(service.remove(boardnum)) {
			ra.addFlashAttribute("rn",boardnum);
		}
		return  "redirect:/board/list"+cri.getListLink();
	}
	
}






