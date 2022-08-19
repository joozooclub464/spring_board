package com.springboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springboard.domain.Criteria;
import com.springboard.domain.ReplyDTO;

public interface ReplyMapper {
	int insert(ReplyDTO reply);
	int getTotal(Long boardnum); //하나의 게시글에 담겨있는 모든 댓글의 갯수
	
	//서로 다른 타입의 여러 파라미터를 mybatis로 넘기려면 원래는 해시맵을 썼음
	//MyBatis는 두 개 이상의 데이터를 파라미터로 넘길 때 객체나 Map, List등 혹은 @Param을 이용한다.
	//정해진 파라미터는 MyBatis에서 #{param명}으로 사용 가능하다
	List<ReplyDTO> getList(@Param("cri")Criteria cri, @Param("boardnum")Long boardnum);
	int delete(Long replynum);
}
