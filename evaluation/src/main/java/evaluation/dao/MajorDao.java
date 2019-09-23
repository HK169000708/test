package evaluation.dao;

import java.util.List;


import evaluation.entity.Major;

public interface MajorDao {
	//查看所有
	List<Major> getMajor();
	//根据id删除
	int deleteMajor(int majorid);
	//新增
	int addMajor(Major major);
	//修改
	int updateMajor(Major major);
	//根据id查询
	Major getMajorById(int majorid);
	//根据id批量删除
	int delAllMajor(String[] ids);

}
