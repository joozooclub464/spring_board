/**
 * reply 관리할 모듈(module)
 */

//function create() {
//	return {brand:"Ferrari"}; //{} : 자바스크립트의 객체
//}
//const mycar = create();
const mycar = (function create() {
	return {brand:"Ferrari"}; 
})(); //js에서 함수를 선언함과 동시에 호출한것
//console.log(mycar.brand);

function factory(f) {
	return f(); //f()는 사실 create()와 같음
	// 위 create()의 객체를(Ferrari) 리턴받은것이고 다시 그 객체를 리턴함
}
//factory(create);

//callback함수 : 매개변수로 함수를 넘겨주고 그 함수 소괄호를 통해서 넘겨준 함수를 호출하는것
//-------------------------------------------------------------
const replyService = (function() {
	function insert(reply,callback,err) {
		//댓글등록기능
		console.log('add reply.....');
		//callback, err는 외부에서 전달받을 함수이다
		//자바스크립트는 함수의 파라미터 개수를 일치시킬 필요가 없다(매개변수를 부족하게 넘겨줘도 호출 가능)
		//사용시 callback이나 err와 같은 것은 상황에 따라 작성한다.
		$.ajax(
			{
				type:"POST",
				url:"/reply/regist",
				data:JSON.stringify(reply),//JSON.stringify(JS객체) : JS객체 -> JSON으로 변환
				contentType:"application/json; charset=utf-8",
				success:function(result,status,xhr) {
					if(callback) {
						console.log("regist result: "+result);
						callback(result);
					}
				},
				error:function(xhr,status,e) { //여기서 e는 trycatch의 e
					if(err) {
						err(e);
					}
				}
			}
		)
		
	}
	//data : {boardnum:123, pagenum:3} , callback : DOM으로 리스트를 만들어주는 함수 function(replyCnt,list)
	function selectAll(data,callback,err) {
		//여기서 err는 지금 안 넘겨주고있음
		let boardnum = data.boardnum;//123
		let pagenum = data.pagenum;//3
		//ajax로 검색을 하고 결과를 받아와서 그 결과를 뿌려줘야함
		//-> db에 있는걸 java로 검색하고 그 검색된 결과를 여기로 보내줌
		$.getJSON(
			"/reply/pages/"+boardnum+"/"+page+".json", //   /reply/pages/123/3.json 이런 형태로 보냄
			//위의 uri의 json을 정상적으로 읽어왔다면 아래에 있는 함수를 호출해줌. 그 때 매개변수 data에 읽어온 json 내용이 담기게 됨
			function(data) {
				//data : {replyCnt:댓글개수, list:[ReplyDTO를 json으로 바꾼 문자열,...]}
				if(callback) {
					//function(replyCnt,list)
					//getJSON 요청으로 받아온 데이터의 replyCnt, list를 넘겨주면서 호출한다.
					//흐름이 다시 get.jsp의 'function(replyCnt,list)' 이 부분으로 이동
					callback(data.replyCnt,data.list);
				}
			}
		).fail(
			function(xhr,status,e) {
				if(err) {
					err(e);
				}
			}
		)
		
	}
	
	return {add:insert, getList:selectAll, remove:"", update:"", get:"", displayTime:""}; 
})();

//replyService.add() --> insert() replyService.add() 는  insert를 호출하는 것과 마찬가지이다.