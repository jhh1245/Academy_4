package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.vo.MemberVo;
import service.MyBatisConnector;

public class MemberDao {
	SqlSessionFactory factory;
	
	// single-ton pattern : 객체 1개만 생성해서 이용하자
	static MemberDao single = null;

	public static MemberDao getInstance() {

		//없으면 생성해라
		if (single == null)
			single = new MemberDao();

		return single;
	}

	// 외부에서 객체생성하지 말아라...
	private MemberDao() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//전체조회
	public List<MemberVo> selectList() {

		List<MemberVo> list = null;

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 
		
		// 2. 작업 수행 
		list = sqlSession.selectList("member.member_list");
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기 

		return list;
	}
	
	// mem_idx 에 해당하는 정보 1건 가져옴 
	public MemberVo selectOne(int mem_idx) {

		MemberVo vo = null;

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 
		
		// 2. 작업 수행 
		vo = sqlSession.selectOne("member.member_one", mem_idx);
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기 
		
		return vo;
	}
	
	// mem_id 에 해당하는 1건의 정보 얻어온다. 
	public MemberVo selectOne(String mem_id) { // 매개변수 타입, 이름 변경 // 오버로딩!! 

		MemberVo vo = null;

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 
		
		// 2. 작업 수행 
		vo = sqlSession.selectOne("member.member_one_id", mem_id);
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기 

		
		return vo;
	}

	public int insert(MemberVo vo) {
		int res = 0;

		SqlSession sqlSession = factory.openSession(); // Connection 획득 
		
		// 2. 작업 수행 
		res = sqlSession.insert("member.member_insert", vo); // 여기서 insert는 마이바티스 객체의 API 이다. 

		if(res == 1) {
			sqlSession.commit();
		}
			
		return res;

	} // end:insert() 
	
	public int delete(int mem_idx) {
		int res = 0;
		

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(true); // Connection 획득 
				
		// 2. 작업 수행 
		res = sqlSession.delete("member.member_delete", mem_idx); // 여기서 insert는 마이바티스 객체의 API 이다. 
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기
					
				
		return res;

	}//end:delete()
	
	
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub

		int res = 0;

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(true); // Connection 획득 
		
		// 2. 작업 수행 
		res = sqlSession.update("member.member_update", vo);
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기
		
		return res;
		

	}//end:update()
}
