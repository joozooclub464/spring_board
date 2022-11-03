package com.springboard.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//검색의 기준, 화면을 구성할 기준, pageMaker에게 게시글 목록을 띄우게 시킬 때 건네주는 정보들
@Getter
@Setter
@ToString
//@Data로 붙여주지 않은 이유는 생성자를 직접 생성할 것이기 때문임.
public class Criteria {
	//pagemaker에게 넘겨주는 정보들(기준)
	//pagemaker는 페이지 처리하는 구문을 갖고 있음.그러나 위에 어떤 게시글이 띄워져야 하는지는 알 수 없음
	//따라서 pagemeker에게 그 기준을 알려줌으로써 너 이런거 만들어야해 를 알려주는 클래스가 Criteria임.
	
	private int pagenum;
	private int amount;
	private String type; //뭐로 검색했는지
	private String keyword;
	
	public Criteria() {
		//아무것도 없는 기본 설정으로 페이지를 호출하라
		//this() : 이 클래스의 생성자
		this(1,10);
	}
	
	public Criteria(int pagenum, int amount) {
		this.pagenum = pagenum;
		this.amount = amount;
	}
	//현재 객체가 가지고 있는 pagenum, amount로 쿼리스트링을 만들어서 리턴하는 메소드 
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pagenum", this.pagenum) //파라미터 추가
				.queryParam("amount", this.amount)
				.queryParam("keyword", this.keyword)
				.queryParam("type", this.type);
		//?pagenum=3&amount=10
		//위의 문자열 만드는 코드
		//fromPath : ? 앞에 붙는 uri. (우리는 ? 뒤의 문자열만 만들 것이기 때문에 fromPath를 빈문자열로 준것)
		return builder.toUriString(); //빌더가 가지고 있는 설정대로 문자열 만들기
	}
	
	public String[] getTypeArr() {
		//type 이 비어있다면 (null이라면) return {};
		//type에 "TC"가 있다면 return {"T","C"};  //TC는 스트링 {"T","C"}이 컬렉션
		//3칸짜리 배열을 만들어서 type으로 리턴해줌
		return type == null ? new String[] {} : type.split("");
		
	}
		
}
