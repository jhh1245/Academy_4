package dao;

import java.util.List;

import vo.WeightVo;

public interface DiaryDao {

	List<WeightVo> selectList_weight();

	int diary_insert_weight(WeightVo vo);

	
}
