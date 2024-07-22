package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.PhotoVo;

public class PhotoDao {
	SqlSession sqlSession;

	// photoDao가 만들어질 때 setter Injection 
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//전체목록
	public List<PhotoVo> selectList() {
				
		return sqlSession.selectList("photo.photo_list");
	}

	
	
	// Map을 파라미터로 받는 
	public List<PhotoVo> selectList(Map<String, Object> map) {
		
		return sqlSession.selectList("photo.photo_list_page", map);
	}
	
	// 전체 레코드 수 구하기
	public int selectRowTotal() {
						
		return sqlSession.selectOne("photo.photo_row_total");
	}
	
	
	// p_idx에 대한 1건의 정보
	public PhotoVo selectOne(int p_idx) {
		
		return sqlSession.selectOne("photo.photo_one", p_idx);
	}
	
	
	public int insert(PhotoVo vo) {
	
		return sqlSession.insert("photo.photo_insert", vo);

	}//end:insert()

	
	public int delete(int p_idx) {

		return sqlSession.delete("photo.photo_delete", p_idx);

	}//end:delete()

	
	
	public int update_filename(PhotoVo vo) {			  
				
		return sqlSession.update("photo.photo_update_filename", vo);

	} // end:update_filename() 
	
	
	public int update(PhotoVo vo) {
					

		return sqlSession.update("photo.photo_update", vo);

	}//end:update()
}
