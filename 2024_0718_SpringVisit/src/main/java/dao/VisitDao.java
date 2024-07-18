package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import vo.VisitVo;

public class VisitDao {

	@Autowired
	SqlSession sqlSession; // SqlSessionTemplate 
		
	// 목록 전체 조회 
	public List<VisitVo> selectList() { 		
		
		// sqlSession.selectList("visit.visit_list");
		// SqlSessionTemplate 내부에 재 정의된 selectList 동작한다
		// 1. openSession() -> 2. 작업수행 -> 3.close() 가 동작 
		
		/* 
		 	class SqlSessionTemplate implements SqlSession {
		 		public List selectList( String mapper_id ){ 
					SqlSession sqlSession = factory.openSession();
					list = sqlSession.selectList("visit.visit_list");
					sqlSession.close();
				}
			}
			
			아래 return 문은 이런 과정을 거쳤을 것이다. 
		 */
		
		return sqlSession.selectList("visit.visit_list");
	}

	// Map을 인자로 받는 selectList
	public List<VisitVo> selectList(Map<String, Object> map) { //select한 결과를 List로 만든다. 
		
		return sqlSession.selectList("visit.visit_list_condition",  map);
	}
	
	
	// 쓰기
	public int insert(VisitVo vo) {
				
		return sqlSession.insert("visit.visit_insert", vo);

	} // end:insert()  

	
	// 삭제
	public int delete(int idx) {
		
		return sqlSession.delete("visit.visit_delete", idx);

	} // end:delete() 

	
	// select one
	public VisitVo selectOne(int idx) {

		return sqlSession.selectOne("visit.visit_one", idx);
	}


	// 수정
	public int update(VisitVo vo) {
		
		return sqlSession.update("visit.visit_update", vo);

	} // end:update() 

	
	
	// 전체 레코드 수 구하기
	public int selectRowTotal(Map<String, Object> map) {
						
		return sqlSession.selectOne("visit.visit_row_total", map);
	}

}
