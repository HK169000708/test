package evaluation.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import evaluation.dao.TeacherDao;
import evaluation.entity.Course;
import evaluation.entity.Score_V;
import evaluation.entity.Teacher;
import evaluation.entity.Teacher_V;

@Service
public class TeacherService {
	
	//注入接口
	@Autowired
	private TeacherDao teacherDao;

	//查询所有教师	
	public List<Teacher> getTeachers(){
		return teacherDao.getTeachers();
	}

	//分页

	public List<Teacher> getTeachersmajor(){
		return teacherDao.getTeachersmajor();
	}
		
	
	//删除
	public int delTeacher(String teachernumber) {
		return teacherDao.delTeacher(teachernumber);
	}
	
	//查询一组
	public int getCount() {
		return teacherDao.getCount();
	}
	
	//新增
	public int addTeacher(Teacher teacher) {
		return teacherDao.addTeacher(teacher);
	}
	
	//修改
	public int updateTeacher(Teacher teacher) {
		return teacherDao.updateTeacher(teacher);
	}
	


	public int resetpwd(int teacherid) {
		return teacherDao.resetpwd(teacherid);
	}
	

	//根据id查询

	public Teacher getTeacherByid(int teacherid) {
		return teacherDao.getTeacherByid(teacherid);
	}	
   
    
   
	public Teacher geTeacher(Teacher teacher) {
		return teacherDao.getTeacher(teacher);
	}

	public List<Teacher> getteacherpower(){
	return	teacherDao.getteacherpower();
		
	}
	//权限修改
	public int updatepower(Teacher teacher) {
		return teacherDao.updatepower(teacher);
	}
		


	//批量删除	
	public int delAllTeacher(String[] ids) {
		return teacherDao.delAllTeacher(ids);
	}
	
	//模糊查询
		public List<Teacher> mhselect(String name){
			List<Teacher> list = teacherDao.mhselect(name);
			return list;
			
		}
		public List<Teacher> getTeachernot(Teacher teacher) {
			return teacherDao.getTeachernot(teacher);
		}	
	//评价
		public List<Teacher> getTeachering(Teacher teacher){
			
			return teacherDao.getTeachering(teacher);
		}
	//個人信息修改
public int myupdate(Teacher teacher){
			
			return teacherDao.myupdate(teacher);
		}
//登录判断
	public int login(String teachernumber,String password) {
		Teacher teacher=new Teacher();
		teacher.setTeachernumber(teachernumber);
		teacher.setPassword(password);
		Teacher t=teacherDao.getTeacher(teacher);
		if(t.getPower()==1){
			return 1;
		}if(t.getPower()==2) {
			return 2;
		}
		return 2;			
	}
	
	//查看得分
			public List<Teacher_V> getClass(int teacherid){
				return teacherDao.getClassa(teacherid);
			}
			//查询某班积分数列表
			public List<Score_V> getScore(int teachingid,int classid){
				return teacherDao.getScore(teachingid, classid);
			}
			
			public List<Object> getClassa(int teacherid){
				List<Teacher_V> list =teacherDao.getClassa(teacherid);
				
				List<Score_V> list2 = new ArrayList<>();
				int q0sum=0;
				int q1sum=0;
				int q2sum=0;
				int q3sum=0;
				int q4sum=0;
				int q5sum=0;
				int q6sum=0;
				int q7sum=0;
				int q8sum=0;
				int q9sum=0;
				//获得  
				for(Teacher_V t:list) {
					List<Score_V> tempList = teacherDao.getScore(t.getTeachingid(), t.getClassid());
					for(Score_V s:tempList) {
						list2.add(s);
					}
					
				}
				//算出某班总得分
				for(Score_V s:list2) {
						q0sum+=s.getQuestion0();
						q1sum+=s.getQuestion1();
						q2sum+=s.getQuestion2();
						q3sum+=s.getQuestion3();
						q4sum+=s.getQuestion4();
						q5sum+=s.getQuestion5();
						q6sum+=s.getQuestion6();
						q7sum+=s.getQuestion7();
						q8sum+=s.getQuestion8();
						q9sum+=s.getQuestion9();
					
					
				}
				//获得某班各项总得分
				List<Object> list3 =new ArrayList<>();
				int qsum=q0sum+q1sum+q2sum+q3sum+q4sum+q5sum+q6sum+q7sum+q8sum+q9sum;
				list3.add(q0sum);
				list3.add(q1sum);
				list3.add(q2sum);
				list3.add(q3sum);
				list3.add(q4sum);
				list3.add(q5sum);
				list3.add(q6sum);
				list3.add(q7sum);
				list3.add(q8sum);
				list3.add(q9sum);
				list3.add(qsum);
				return list3;
			}
			
			
			







}

