package evaluation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluation.dao.StudentDao;
import evaluation.entity.Student;
import evaluation.entity.Teacher;
import evaluation.entity.Teaching;

@Service
public class StudentService {

	// 注入接口
	@Autowired
	private StudentDao studentDao;

	// 查询所有
	public List<Student> getStus() throws ParseException  {
		return studentDao.getStus();
	}

	// 根据id查询
	public Student getStudentBystuid(int studentid) {
		return studentDao.getStudentBystuid(studentid);

	}

	public Student getStudent(Student student) {
		return studentDao.getStudent(student);
	}

	// 修改
	public int updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

	// 删除
	public int studentdel(int studentid) {
		return studentDao.studentdel(studentid);
	}

	// 批量删除
	public int studentall(String[] aa1) {

		return studentDao.studentall(aa1);
	}

	public int studentadd(Student student) {

		return studentDao.studentadd(student);
	}

	// 模糊查询
	public List<Student> mhselect(String name) {
		List<Student> list = studentDao.mhselect(name);
		return list;

	}

	public List<Teaching> getTeacher(int classid) {
		return studentDao.getTeacher(classid);
	}

	// 登录判断
	public boolean login(String studentnumber, String password) {
		Student student = new Student();
		student.setStudentnumber(studentnumber);
		student.setPassword(password);
		Student s = studentDao.getStudent(student);
		if (s != null) {
			return true;
		}
		return false;
	}

	// 学生修改
	public int Studentupdate(Student student) {
		return studentDao.Studentupdate(student);
	}

	// 根据number查询
	public Student getStudentBystuno(String studentnumber) {
		return studentDao.getStudentBystuno(studentnumber);

	}
	//重置密码
	public int resetpwd(int studentid) {
		return studentDao.resetpwd(studentid);
	}
	

}
