package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.vo.DeptVo;
import service.MyBatisConnector;

public class DeptDao {

	SqlSessionFactory factory;
	
	// single-ton pattern : 객체 1개만 생성해서 이용
	static DeptDao single = null;

	static public DeptDao getInstance() {
		// 없으면 생성 
		if (single == null) {
			single = new DeptDao();
		}
		// 2번째 부터는 이미 생성된걸 반환 
		return single;
	}

	// 외부에서 객체 생성 하지 못하도록 한다. 
	private DeptDao() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	
	public List<DeptVo> selectList(){
		List<DeptVo> list = null;
		
		// 1. sqlSession 얻어오기 
		SqlSession sqlSession = factory.openSession(); // Connection 획득 

		// 2. 작업 수행 
		list = sqlSession.selectList("dept.dept_list");

		// 3. 작업세션 닫기 : conn.close() 과정 포함 
		sqlSession.close();
		
		return list;
	}
}
