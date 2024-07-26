package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.WeightVo;


@Repository //component이구나 알고 자동 생성한다. 
public class DiaryDaoImpl implements DiaryDao {

	public DiaryDaoImpl() {
		System.out.println("--DiaryDaoImpl()--");
		// 자동 생성하는지 확인 위해서
	}
	
	@Autowired
	SqlSession sqlSession;
	
	String now_date = "2024-07-25";
	
	@Override
	public List<WeightVo> selectList_weight() {
		
		return sqlSession.selectList("diary.diary_list_weight", now_date);
	}

	@Override
	public int diary_insert_weight(WeightVo vo) {
		return sqlSession.insert("diary.diary_insert_weight", vo);
	}
	

}
