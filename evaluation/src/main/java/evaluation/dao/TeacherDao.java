package evaluation.dao;

import java.util.List;

import evaluation.entity.Score_V;
import evaluation.entity.Teacher;
import evaluation.entity.Teacher_V;

import org.apache.ibatis.annotations.Param;

public interface TeacherDao {

	List<Teacher> getTeachersmajor();
	
	int resetpwd(int teacherid);

	List<Teacher> getTeachers();
	//根据id查询
	Teacher getTeacherByid(int teacherid);
	//查询一组
	int getCount();
	//根据账号查询
	int getTeacherByNum(String teachernumber);
	//新增
	int addTeacher(Teacher teacher);
	//删除
	int delTeacher(String teachernumber);
	//修改
	int updateTeacher(Teacher teacher);

    List<Teacher> getteacherpower();
    
	int updatepower(Teacher teacher);
	//查询所有
	Teacher getTeacher(Teacher teacher);

	//批量删除
	int delAllTeacher(String[] ids);

	//模糊查询
		List<Teacher> mhselect(@Param("name")String name);
		
		List<Teacher> getTeachernot(Teacher teacher);
		
    //评价
		List<Teacher> getTeachering(Teacher teacher);
	//修改个人信息
		int myupdate(Teacher teacher);
		
		//查看得分
				List<Teacher_V> getClassa(int teacherid);
				List<Score_V> getScore(@Param("teachingid")int teachingid,@Param("classid")int classid);	
		
		
}
