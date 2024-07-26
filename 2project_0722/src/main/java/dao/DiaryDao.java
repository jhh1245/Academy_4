package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vo.WeightVo;

public interface DiaryDao {

	List<WeightVo> selectList_weight();

	int diary_insert_weight(WeightVo vo);

	// 특정 날짜의 체중 데이터를 조회하는 메서드
    List<WeightVo> diary_select_weight_date(String date);

	WeightVo selectOne_weight(int w_idx);

	int update_weight(WeightVo vo);
	
}
