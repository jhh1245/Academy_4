/*

-- 일련번호 관리 객체 
create sequence seq_visit_idx

-- 방명록 테이블 생성 
create table visit
(
	idx		int, 						-- 일련번호
	name	varchar2(100)  not null, 	-- 작성자이름	
	content varchar2(2000) not null,	-- 내용
	pwd		varchar2(100)  not null,	-- 비밀번호		
	ip		varchar2(100)  not null,	-- 아이피
	regdate	date						-- 작성일자
);

-- 기본키 
alter table visit
	add constraint pk_visit_idx primary key(idx);
	
-- sample data
insert into visit values(seq_visit_idx.nextVal,
						'일길동', 
						'내가 1등!!',
						'1234',
						'192.168.1.1',
						sysdate);

insert into visit values(seq_visit_idx.nextVal,
						'이길동', 
						'난 2등!!',
						'4567',
						'192.168.2.2',
						sysdate);

-- JDBC용 insert문
insert into visit values(seq_visit_idx.nextVal, ?, ?, ?, ?, sysdate);
						
										
-- view 로 생성 
create or replace view visit_view 
as 																										
	select rownum as no, v.* -- 열명  
	from
	(select * from visit order by idx desc) v


select * from visit_view where idx = 1;

-- 수정 
update visit set name='원길동', content='내가 일등이다', pwd='1234', ip='192.168.0.107', regdate=sysdate where idx=1; 
 
						
*/