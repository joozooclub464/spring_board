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
	function insert() {
		//댓글등록기능
		
	}
	
	return {add:insert, getList:"", remove:"", update:"", get:"", displayTime:""}; 
})();

//replyService.add() --> insert() replyService.add() 는  insert를 호출하는 것과 마찬가지이다.