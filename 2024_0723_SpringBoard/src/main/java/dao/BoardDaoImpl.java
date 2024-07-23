package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.BoardVo;


@Repository //component이구나 알고 자동 생성한다. 
public class BoardDaoImpl implements BoardDao {

	public BoardDaoImpl() {
		System.out.println("--BoardDaoImpl()--");
		// 자동 생성하는지 확인 위해서
	}
	
	@Autowired
	SqlSession sqlSession;
	
	
	@Override
	public List<BoardVo> selectList() {

		return sqlSession.selectList("board.board_list");
		// mapper board 안에 있는 결과를 Vo로 포장하여, select List로 넘겨준다. 
	}


	@Override
	public int insert(BoardVo vo) {
		
		return sqlSession.insert("board.board_insert", vo);
	}


	@Override
	public BoardVo selectOne(int b_idx) {
		
		return sqlSession.selectOne("board.board_one", b_idx);
	}


	@Override
	public int update_readhit(int b_idx) {
		
		return sqlSession.update("board.board_update_readhit", b_idx);
	}

}
