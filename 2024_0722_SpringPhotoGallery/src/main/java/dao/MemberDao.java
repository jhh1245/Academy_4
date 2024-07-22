package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVo;

public class MemberDao {

	SqlSession sqlSession; //SqlSessionTemplate의 interface

	//Setter Injection
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// mem_id 에 해당되는 1건의 정보 얻어온다
	public MemberVo selectOne(String mem_id) {

		return sqlSession.selectOne("member.member_one_id", mem_id);
	}
	
	
	//전체조회
	public List<MemberVo> selectList() {

		return sqlSession.selectList("member.member_list");
	}
	
	// mem_idx 에 해당하는 정보 1건 가져옴 
	public MemberVo selectOne(int mem_idx) {
		
		return sqlSession.selectOne("member.member_one", mem_idx);
	}
	

	public int insert(MemberVo vo) {
			
		return sqlSession.insert("member.member_insert", vo); // 여기서 insert는 마이바티스 객체의 API 이다. 


	} // end:insert() 
	
	public int delete(int mem_idx) {
					
				
		return sqlSession.delete("member.member_delete", mem_idx); // 여기서 insert는 마이바티스 객체의 API 이다. 
		

	}//end:delete()
	
	
	public int update(MemberVo vo) {
		
		return sqlSession.update("member.member_update", vo);
		

	}//end:update()
	
}
