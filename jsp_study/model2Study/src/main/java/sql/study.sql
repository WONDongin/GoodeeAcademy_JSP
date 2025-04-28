-- /src/sql/study.sql ---
create table board (
   num int  auto_increment primary key, -- 게시글 번호. 기본키
   writer varchar(30), -- 작성자
   pass varchar(20),   -- 비밀번호
   title varchar(100), -- 게시글 제목
   content varchar(2000),  -- 게시글 내용
   file1 varchar(200),  -- 첨부파일명
   boardid varchar(2),  -- 게시판 종류 : 1:공지사항, 2: 자유게시판 
   regdate datetime,    -- 게시글 등록일시
   readcnt int(10),     -- 조회수. 상세보기시 1씩 증가
   grp int,             -- 답글 작성시 원글의 게시글 번호
   grplevel int(3),     -- 답글의 레벨 0:원글, 1:원글의 답글, 2:답글의 답글 ... 
   grpstep int(5)       -- 그룹의 출력 순서 
);
select * from board
-- 댓글 table 
create table comment (
   num int  references board (num),
   seq int,
   writer varchar(30),
   content varchar(2000),
   regdate datetime,
   primary key (num,seq)
)
select * from comment
