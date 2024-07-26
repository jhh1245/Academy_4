package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import vo.CommentVo;

public class CommentDaoImpl implements CommentDao {

	@Autowired
	SqlSession sqlSession;
	
	
	@Override
	public List<CommentVo> selectList(int b_idx) {
		
		return sqlSession.selectList("comment.comment_list", b_idx);
	}

	@Override
	public List<CommentVo> selectList(Map<String, Object> map) {
		
		return sqlSession.selectList("comment.comment_page_list", map);
	}
	
	@Override
	public int insert(CommentVo vo) {
		
		return sqlSession.insert("comment.comment_insert", vo);
	}

	@Override
	public int delete(int cmt_idx) {
		
		return sqlSession.delete("comment.comment_delete", cmt_idx);
		
	}

	@Override
	public int selectRowTotal(int b_idx) {
		return sqlSession.selectOne("comment.comment_row_total", b_idx);
	}



}
