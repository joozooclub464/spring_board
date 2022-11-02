DROP TABLE spring_board;
DROP SEQUENCE SEQ_BOARD;
DROP SEQUENCE SEQ_REPLY;
DROP TABLE SPRING_REPLY;
------------------------------------------------------
--게시판 테이블 생성
CREATE SEQUENCE SEQ_BOARD;
CREATE TABLE spring_board(
	boardnum number(10) PRIMARY KEY,
	boardtitle varchar2(1000) NOT NULL,
	boardcontents varchar2(4000) NOT NULL,
	boardwriter varchar2(300) NOT NULL, --익명게시판으로 운영할것이므로 받아줘야함
	regdate DATE DEFAULT sysdate,
	updatedate DATE DEFAULT sysdate
);
--게시판에 더미데이터 넣기
SELECT * FROM SPRING_BOARD;
INSERT INTO spring_board (boardnum,boardtitle,boardcontents,boardwriter)
values(seq_board.nextval,'테스트 제목1','테스트 내용1','apple');
INSERT INTO spring_board (boardnum,boardtitle,boardcontents,boardwriter)
values(seq_board.nextval,'테스트 제목2','테스트 내용2','banana');
INSERT INTO spring_board (boardnum,boardtitle,boardcontents,boardwriter)
values(seq_board.nextval,'테스트 제목3','테스트 내용3','cherry');
--삽입된 데이터 갯수
SELECT count(*) FROM spring_board;
--nextval로 붙여서 가져오기(호출할 때 마다 행의 갯수만큼 증가함)
SELECT seq_board.nextval,boardtitle,boardcontents,boardwriter FROM spring_board;
--nextval만 가져오기(호출할 때 마다 행의 갯수만큼 증가함)
SELECT seq_board.nextval FROM spring_board;
--테이블에 있는 행의 갯수만큼 데이터를 추가해 주는 코드
INSERT INTO spring_board (boardnum,boardtitle,boardcontents,boardwriter)
(SELECT seq_board.nextval,boardtitle,boardcontents,boardwriter FROM spring_board);
--게시글 번호에 따라 최신 게시글이 위로 오도록 정렬해 주는 코드
select boardnum,boardtitle,boardcontents,boardwriter,regdate,updatedate
from
	(select rownum rn,boardnum,boardtitle,boardcontents,boardwriter,
	regdate,updatedate from spring_board where rownum<=131 order by boardnum desc)
where rn>0;

/*+ */
-- : 힌트. 이걸 참고해서 검색해와라
select boardnum,boardtitle,boardcontents,boardwriter,regdate,updatedate from 
(SELECT /*+INDEX_DESC(spring_board boardidx) */ rownum rn,boardnum,boardtitle,boardcontents,boardwriter, regdate,updatedate 
from spring_board where ( (boardtitle like '%목2%') ) and rownum<=1*10) 
where rn>(1-1)*10;

ALTER INDEX sys_c0010549 RENAME TO boardidx;


SELECT * FROM SPRING_BOARD;
--------------------------------------------------
--댓글 테이블 
CREATE SEQUENCE SEQ_REPLY;
CREATE TABLE SPRING_REPLY(
	replynum number(10) CONSTRAINT S_REPLY_PK PRIMARY KEY,
	replywriter varchar2(300) NOT NULL,
	replycontents varchar2(2000) NOT NULL,
	regdate DATE DEFAULT sysdate,
	updatedate DATE DEFAULT sysdate,
	boardnum number(10)
);

SELECT * FROM spring_reply;
SELECT count(*) FROM spring_reply WHERE boardnum=421;

--------------------------------------------------------
--mapper만들기 실습용 쿼리






