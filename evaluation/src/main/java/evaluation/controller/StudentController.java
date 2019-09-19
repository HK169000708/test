package evaluation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import evaluation.entity.Classtb;
import evaluation.entity.Student;
import evaluation.service.ClasstbService;
import evaluation.service.StudentService;

import evaluation.entity.ResultMsg;





@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService getStus;
	private ClasstbService claser;
	
	
	@RequestMapping("/studentlist")
	public ModelAndView studentlist() {
		List<Student> students =getStus.getStus();
		
		ModelAndView mv = new ModelAndView("student/studentlist");
		mv.addObject("students", students);
		return mv;
	}
	
	@RequestMapping("/studentedit")
	public ModelAndView studentedit(int studentid) {
		
		Student stu= getStus.getStudentBystuid(studentid);
		/*List<Classtb> clas = claser.getclass();*/
		
		ModelAndView mv = new ModelAndView("student/studentedit");	
		mv.addObject("stu",stu);
		/*mv.addObject("clas", clas);*/
	return mv;
	}
	
	
	@RequestMapping("updatestucontroll")
	@ResponseBody
	public ResultMsg update(Student student) {
		//更新雇员
		int i=getStus.updateStudent(student);
		if (i > 0) {
			return new ResultMsg(1, "更新雇员成功");
		}
		return new ResultMsg(0, "更新雇员失败");
	}
	
	@RequestMapping("studentdel")
	@ResponseBody
	public ResultMsg studentdel(int studentid) {
		//更新雇员
		int i=getStus.studentdel(studentid);
		
		if (i > 0) {
			return new ResultMsg(1, "删除成功");
		}
		return new ResultMsg(0, "删除失败");
	}
	@RequestMapping("studentall")
	@ResponseBody
	public ResultMsg studentall(String aa) {
		//更新雇员
		int i=getStus.studentall(aa);
		System.out.println(aa);
		System.out.println(aa.length());
		if (i > 0) {
			return new ResultMsg(1, "删除成功");
		}
		return new ResultMsg(0, "删除失败");
	}
	
}
