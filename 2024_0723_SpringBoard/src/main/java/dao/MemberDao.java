package dao;

import java.util.List;

import vo.MemberVo;

public interface MemberDao {

	// 전체조회
	public List<MemberVo> selectList();

	// mem_idx에 해당되는 1건의 정보 얻어온다
	public MemberVo selectOne(int mem_idx);

	// mem_id 에 해당되는 1건의 정보 얻어온다
	public MemberVo selectOne(String mem_id);

	public int insert(MemberVo vo);

	public int delete(int mem_idx);

	public int update(MemberVo vo);

}
