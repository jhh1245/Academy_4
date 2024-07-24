package dao;

import java.util.List;

import vo.CommentVo;

public interface CommentDao {
	List<CommentVo> selectList(int b_idx);
	
	int insert(CommentVo vo);
	
	int delete(int cmt_idx);
}
