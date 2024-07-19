package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.vo.VisitVo;
import service.MyBatisConnector;

public class VisitDao {
	//Mybatis객체 
	SqlSessionFactory factory;
	
	// single-ton pattern : 객체 1개만 생성해서 이용
	static VisitDao single = null;

	static public VisitDao getInstance() {
		// 없으면 생성 
		if (single == null) {
			single = new VisitDao();
		}
		// 2번째 부터는 이미 생성된걸 반환 
		return single;
	}

	// 외부에서 객체 생성 하지 못하도록 한다. 
	private VisitDao() { // 처음에 만들어질 때 가져온다!!! 
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();

	}
	

	
	// 목록 전체 조회 
	public List<VisitVo> selectList() { 
		List<VisitVo> list = null;

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 
		
		// 2. 작업 수행 
		list = sqlSession.selectList("visit.visit_list");
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기 
		
		
		
		return list;
	}

	// Map을 인자로 받는 selectList
	public List<VisitVo> selectList(Map<String, Object> map) { //select한 결과를 List로 만든다. 
		List<VisitVo> list = null;

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 
		
		// 2. 작업 수행 
		list = sqlSession.selectList("visit.visit_list_condition",  map);
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기 
		
		return list;
	}
	
	
	// 쓰기
	public int insert(VisitVo vo) {
		int res = 0;
																//   1  2  3  4 <- pstmt index
		// String sql = "insert into visit values(seq_visit_idx.nextVal, ?, ?, ?, ?, sysdate)"; // ; 세미콜론은 없어야됨  

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 
				
		// 2. 작업 수행 
		res = sqlSession.insert("visit.visit_insert", vo); // 여기서 insert는 마이바티스 객체의 API 이다. 

		// 여기까지는 Transaction Log에 저장되었고 close 단계에서 rollback이 된다. 그래서 DB에 insert가 안되었다. 
		// 여기서 commit을 하면 DB에 반영된다. 
		if(res == 1) {
			sqlSession.commit();
		}
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기
		
		return res;

	} // end:insert()  

	
	// 삭제
	public int delete(int idx) {
		int res = 0;
		
		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(true); // Connection 획득 
				
		// 2. 작업 수행 
		res = sqlSession.delete("visit.visit_delete", idx); // 여기서 insert는 마이바티스 객체의 API 이다. 
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기
				
		return res;

	} // end:delete() 

	public VisitVo selectOne(int idx) {

		VisitVo vo = null;

		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 
		
		// 2. 작업 수행 
		vo = sqlSession.selectOne("visit.visit_one", idx);
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기 
		

		return vo;
	}


	// 수정
	public int update(VisitVo vo) {
		int res = 0;
		
		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(true); // Connection 획득 
		
		// 2. 작업 수행 
		res = sqlSession.update("visit.visit_update", vo);
		
		// 3. 닫기 : conn.close() 과정 포함 
		sqlSession.close(); // 작업세션 닫기 
		  
		
		return res;

	} // end:update() 

	
	
	// 전체 레코드 수 구하기
	public int selectRowTotal(Map<String, Object> map) {
		int total = 0;
		
		// 1. SqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession();
		
		//2. 작업수행 
		total = sqlSession.selectOne("visit.visit_row_total", map);
		
		//3. 닫기 
		sqlSession.close();
				
		return total;
	}

	
	}
