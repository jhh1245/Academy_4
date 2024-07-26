/*
-- 게시판 일련번호 관리객체
create sequence seq_board_idx


-- 게시판 DB

create table board
(
	b_idx		int,					-- 일련번호
	b_subject   varchar2(200) not null,	-- 제목
	b_content   clob not null,			-- 내용
	b_ip		varchar2(100) not null,	-- 아이피
	b_regdate	date,					-- 작성일자
	b_readhit   int      default 0,		-- 조회수
	b_use		char(1)  default 'y',	-- 사용유무
	mem_idx		int,					-- 작성자회원번호
	mem_name	varchar2(100),			-- 작성자명
	b_ref		int,					-- 참조글번호
	b_step		int,					-- 글순서
	b_depth		int						-- 글깊이
)



-- 기본키 지정
alter table board
   add constraint  pk_board_idx  primary key(b_idx);
   
-- 외래키 지정
alter table board
   add constraint  fk_board_mem_test_idx foreign key(mem_idx)  
                                    references member_test(mem_idx) ;
        

select * from member_test
                                                                        
-- sample data

-- 새글쓰기
insert into board values(seq_board_idx.nextVal,
                         '내가 1등...',
                         '내가 첫번째로 등록했네...',
                         '192.168.10.123',
                         sysdate,
                         0,
                         'y',
                         21,
                         '일길동',
                         seq_board_idx.currVal,
                         0,
                         0
                         ) 

-- 답글쓰기
insert into board values(seq_board_idx.nextVal,
                         '아쉽네 내가1등할수 있었는데',
                         '다음에는 내가 1등해야지',
                         '192.168.10.123',
                         sysdate,
                         0,
                         'y',
                         19,
                         '홍길동',
                         1,
                         1,
                         1
                         ) 

insert into board values(seq_board_idx.nextVal,
                         '그래 다음에는 니가 1등해',
                         '아닐껄.. 다으멩도 내가 1등할껄',
                         '192.168.10.123',
                         sysdate,
                         0,
                         'y',
                         21,
                         '일길동',
                         1,
                         2,
                         2
                         ) 

select * from board



-- paging menu

select * from  
( 
	select 
		rank() over(order by b_ref desc, b_step asc)as no, 
		b.*,
		(select nvl(count(*), 0) from comment_tb where b_idx = b.b_idx) as cmt_count
	from 
		(select * from board) b  
)
where no between 1 and 5 

-- 해당 게시글에 연결된 댓글 갯수 
select nvl(count(*), 0) from comment_tb where b_idx = 30 


*/
