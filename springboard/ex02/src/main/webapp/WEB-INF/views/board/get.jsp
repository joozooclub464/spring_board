<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
<link rel="stylesheet" href="/resources/assets/css/main.css">
</head>
<body class="is-preload">
	<div id="main">
		<div class="wrapper">
			<div class="inner">
				<header class="major">
					<h1 class="home">Board</h1>
					<p>게시글 상세보기</p>
				</header>
				<a href="/board/list${cri.listLink}" class="button primary small">목록 보기</a>
				<br>
				<br>
				<div class="col-12">
					<form method="post" action="/board/remove">
						<input type="hidden" value="${cri.pagenum}" name="pagenum">
						<input type="hidden" value="${cri.amount}" name="amount">
						<input type="hidden" value="${cri.keyword}" name="keyword">
						<input type="hidden" value="${cri.type}" name="type">
						<div class="col-12">
							<h4>번호</h4>
							<input name="boardnum" type="text" value="${board.boardnum}" readonly>
						</div>
						<hr>
						<div class="col-12">
							<h4>제목</h4>
							<input name="boardtitle" type="text" value="${board.boardtitle }" readonly>
						</div>
						<hr>
						<div class="col-12">
							<h4>내용</h4>
							<textarea name="boardcontents" rows="10" style="resize:none;" readonly>${board.boardcontents }</textarea>
						</div>
						<hr>
						<div class="col-12">
							<h4>작성자</h4>
							<input name="boardwriter" type="text" value="${board.boardwriter }" readonly>
						</div>
						<hr>
						<div class="col-12">
							<input type="button" value="수정" class="primary" onclick="location.href='/board/modify${cri.listLink}&boardnum=${board.boardnum}'">
							<input type="submit" value="삭제" class="primary">
						</div>
					</form>
					<hr>
					<h3 style="text-align:center;">댓 글</h3>
					<a href="#" class="button primary regist">댓글 등록</a>
					<br>
					<br>
					<!-- style="display:none" -->
					<div class="replyForm row" style="display:none; justify-content: center; ">
						<div style="width:15%;">
							<h4>작성자</h4>
							<input name="replywriter" placeholder="Writer" type="text">						
						</div>
						<div style="width:65%; ">
							<h4>내용</h4>
							<textarea name="replycontents" rows="2" style="resize:none;" placeholder="Contents"></textarea>
						</div>
						<div style="width:10%; margin-left:1%" class="row">
							<h4 style="margin-bottom:1.3rem !important;">&nbsp;</h4>
							<a href="#" style="margin-bottom:0.7rem;" class="button primary small finish">등록</a>
							<a href="#" class="button primary small cancel">취소</a>
						</div>
					</div>
					<!-- 댓글 띄우는 ul -->
					<ul class="alt replies"></ul>
					<!-- 댓글 페이징 처리할 div -->
					<div class="page" style="text-align:center">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="/resources/assets/js/jquery.min.js"></script>
<script src="/resources/assets/js/jquery.dropotron.min.js"></script>
<script src="/resources/assets/js/browser.min.js"></script>
<script src="/resources/assets/js/breakpoints.min.js"></script>
<script src="/resources/assets/js/util.js"></script>
<script src="/resources/assets/js/main.js"></script>
<script src="/resources/assets/js/reply.js"></script>
<script>
	$(".regist").on("click",function(e) {
		e.preventDefault();
		$(".replyForm").show();
		$(this).hide(); //댓글등록폼이 보여지면 댓글등록 버튼은 숨겨져야 하기 때문에 this.hide를 사용함
	})
	$(".canceel").on("click",function(e) {
		e.preventDefault();
		$("[name='replywriter']").val(""); //제이쿼리는 css선택자를 사용할 수 있음
		$("[name='replycontents']").val(""); //댓글 작성자와 댓글내용을 텍스트박스에서 비워주는 작업
		$(".replyForm").hide();
		$(".regist").show(); //댓글등록버튼을 보여줌
	})
	$(".finish").on("click",function(e){
		let boardnum = "${board.boardnum}";
		let replywriter = $("input[name='replywriter']").val();
		let replycontents = $("textarea[name='replycontents']").val();
		
		replyService.add(
			{boardnum:boardnum,replywriter:replywriter,replycontents:replycontents},
			//프로퍼티(객체)로 add해줌 앞의 boardnum은 키값이고,뒤의 boardnum은 finish버튼눌렀을때의 let boardnum과 같음
			function(result) {
				alert("댓글 등록 완료!");
			},
			function(result) {
				alert("댓글 등록 실패!");
			}
			//위의 함수들은 add쪽에서 받아서 콜백함수로 사용.
			
		)
			//DOM 써서 내용 바꾸기
			location.reload();
	})
	
	$(document).ready(function() {
		//댓글도 페이징 처리 할 것.
		let boardnum = "${board.boardnum}"; 
		let replies = $(".replies");
		let pagenum = 1;
		let page = $(".page"); //댓글 페이지
		
		showList(1);
		
		function showList(pagenum) {
			//매개변수 page : 현재 띄워주어야 할 댓글의 페이지 번호
			replyService.getList(
				{boardnum : boardnum, pagenum : pagenum || 1},
				//첫페이지일때는 pagenum이 넘어오지 않을 수 있음(게시글에 처음 넘어왔을때)
				// => || 1 이라는 조건을 추가해준 것(페이지값이 넘어오지 않으면 1이 페이지값으로 넘어감)
				function(replyCnt,list) {
					if(list == null || list.length == 0) {
						replies.html("<li>댓글이 없습니다.</li>");
						return 
					}
					//검색해온 list 안의 내용들로 DOM 이용해서 화면에 뿌리기
					/*
						<li style="clear:both">
							<div style="display:inline;float:left;">
								<strong>apple</strong>
									<p>댓글테스트</p>
							</div>
							<div style="text-align:right">
								<strong>2022-08-15 19:55:55</strong><br>
								<a href="#">수정</a>&nbsp;&nbsp;
								<a href="#">삭제</a>
							</div>
						</li>
					*/
					let str = "";
					for(let i=0, len=list.length; i<len; i++) {
						str += '<li style="clear:both;">';
						str += '<div style="display:incline;float:left;">';
						//str += '<strong>'+list[i].replywriter+'</strong>';
						str += `<strong>apple</strong>`;
						//str += '<p class="'+list[i].replynum+'">' + list[i].replycontents+'</p></div>';
						str += `<p class="${list[i].replynum}">${list[i].replycontents}</p></div>`;
						str += '<div style="text-align:right">';
						str += '<strong>'+replyService.displayTime(list[i])+'</strong><br>';
						str += '<a href="#">수정</a>&nbsp;&nbsp;';
						str += '<a href="#">삭제</a></div></li>';
					}
					replies.html(str);
				}//여기까지가 하나의 함수
			)
		}
		
	})


</script>
</html>