package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVo;

public class DeptDao {
	
	
	SqlSession sqlSession;

	// Constructor Injection
	// SqlSessionTemplate 인터페이스를 생성자 인젝션으로 연결 
	public DeptDao(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	
	public List<DeptVo> selectList(){
		
		// 1. SqlSession sqlSession = factory.openSession();
		// 2. list = sqlSession.selectList("dept.dept_list");
		// 3. sqlSession.Close(); 
		
		return sqlSession.selectList("dept.dept_list"); // 이 코드는 위의 코드가 수행된 것 
		// 스프링에서 sqlSession 인터페이스를 
		// Dao에서 sqls
	}
	
	
}
