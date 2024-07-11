package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVo;

// single-ton pattern : 객체 1개만 생성해서 이용
public class SawonDao{
	SqlSessionFactory factory;
	
	static SawonDao single = null;
	
	static public SawonDao getInstance() {
		// 없으면 생성 
		if (single == null) {
			single = new SawonDao();
		}
		// 2번째 부터는 이미 생성된걸 반환 
		return single;
	}
	
	// 외부에서 객체 생성 하지 못하도록 한다. 
	private SawonDao() {
		// Mybatis 객체 정보를 얻어온다. 
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	
	
	public List<SawonVo> selectList() { //select한 결과를 List로 만든다. 
		List<SawonVo> list = null;

		// 1. SqlSession 얻어오기 (MyBatis 수행객체)
		SqlSession sqlSession = factory.openSession(); // 세션 열어줘 
		
		// 2. 작업수행                   namespace.mapper-id
		list = sqlSession.selectList("sawon.sawon_list");
		
		// 3. 닫기 
		sqlSession.close(); // 꼭 닫아줘야 한다. 
		
		return list;
	}

	// 부서별 조회 
	public List<SawonVo> selectListFromDeptno(int deptno) {
		List<SawonVo> list = null;

		// 1. SqlSession 얻어오기 (MBatis 수행객체)
		SqlSession sqlSession = factory.openSession(); // 세션 열어줘 
		
		// 2. 작업수행                   namespace.mapper-id       parameter
		list = sqlSession.selectList("sawon.sawon_list_deptno", deptno);
		
		// 3. 닫기 
		sqlSession.close(); // 꼭 닫아줘야 한다. 
		
		return list;
	}

	// 직급별 조회
	public List<SawonVo> selectListFromSajob(String sajob) {
		
		List<SawonVo> list = null;

		// 1. SqlSession 얻어오기 (MyBatis 수행객체)
		SqlSession sqlSession = factory.openSession(); // 세션 열어줘 
		
		// 2. 작업수행                   namespace.mapper-id       parameter
		list = sqlSession.selectList("sawon.sawon_list_sajob", sajob);
		
		// 3. 닫기 
		sqlSession.close(); // 꼭 닫아줘야 한다. 
		
		return list;
		
	}

	
	// 부서, 직급 조건 조회 
	public List<SawonVo> selectList(Map<String, Object> map) {
		List<SawonVo> list = null;

		// 1. SqlSession 얻어오기 (MyBatis 수행객체)
		SqlSession sqlSession = factory.openSession(); // 세션 열어줘 
		
		// 2. 작업수행                   namespace.mapper-id       parameter
		list = sqlSession.selectList("sawon.sawon_list_condition", map);
		
		// 3. 닫기 
		sqlSession.close(); // 꼭 닫아줘야 한다. 
		
		return list;
		
	}

	public List<SawonVo> selectListFromSasex(String sasex) {
		List<SawonVo> list = null;

		// 1. SqlSession 얻어오기 (MyBatis 수행객체)
		SqlSession sqlSession = factory.openSession(); // 세션 열어줘 
		
		// 2. 작업수행                   namespace.mapper-id       parameter
		list = sqlSession.selectList("sawon.sawon_list_sasex", sasex);
		
		// 3. 닫기 
		sqlSession.close(); // 꼭 닫아줘야 한다. 
		
		return list;
	}
}