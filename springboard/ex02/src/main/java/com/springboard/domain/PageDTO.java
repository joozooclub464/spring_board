package com.springboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private int realEnd;
	private boolean prev,next;
	private int total;
	private Criteria cri;
//	private int pagenum; 
	//페이지 정보만 가지고 있는 것이 아니라 내부의 정보를 모두 가지고 있어야 하기 때문에 Criteria를 받아오고
	//pagenum은 Criteria에 있는 pagenum을 호출하여 사용한다.

	
	public PageDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int)Math.ceil(cri.getPagenum()/10.0)*10;	
		this.startPage = endPage-9;
		
		this.realEnd = (int)Math.ceil((total*1.0)/10);
		
		if(endPage>realEnd) {
			endPage = realEnd;
		}
		this.prev = this.startPage > 1; 
		this.next = this.endPage < this.realEnd;
		
		
	}
}
