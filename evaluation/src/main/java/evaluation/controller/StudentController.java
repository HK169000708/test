package evaluation.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import evaluation.entity.Student;
import evaluation.entity.Teacher;
import evaluation.entity.Teaching;
import evaluation.service.ClasstbService;
import evaluation.service.ScoreService;
import evaluation.service.StudentService;
import evaluation.util.Excelutil;
import evaluation.entity.Classtb;
import evaluation.entity.Result;
import evaluation.entity.ResultMsg;
import evaluation.entity.Score;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	//注入service
	@Autowired
	private StudentService getStus;
	@Autowired
	private ClasstbService claser;
	@Autowired
	private ScoreService scoreService;
	//学生列表
	@RequestMapping("/studentlist")
	public ModelAndView studentlist() throws ParseException {
		List<Student> students = getStus.getStus();
		ModelAndView mv = new ModelAndView("student/studentlist");
		mv.addObject("students", students);
		return mv;
	}
	
	
	@RequestMapping("studentlogin")
	 public ModelAndView  studentlogin(Model model,Student student){
	      Student student2=getStus.getStudent(student);
		 model.addAttribute("student",student);
		 ModelAndView mv=new ModelAndView("student/index");
		 mv.addObject("student2", student2);
		 boolean islogin=getStus.login(student.getStudentnumber(), student.getPassword());
		 if (islogin) {
			return mv;
		}else {
			return mv;
		}
	}
	
	@RequestMapping("/welcome")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView("student/welcome");
		return mv;
	}
	
	//学生登录
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("student/login");
		return mv;
	}
	
	@RequestMapping("/evalist")	
	public ModelAndView evalist(Integer studentid,String coursename,String name,Integer teachingid) {
		Score score=new Score();
		score.setStudentid(studentid);
		score.setTeachingid(teachingid);
		int i=scoreService.getScore2(score);		
		if(i>0) {
			ModelAndView mv1=new ModelAndView("student/evaresult");
			return mv1;
		}else {
		ModelAndView mv = new ModelAndView("student/evalist");
		mv.addObject("studentid",studentid);
		mv.addObject("coursename",coursename);
		mv.addObject("name",name);
		mv.addObject("teachingid",teachingid);	
		return mv;
		}
	}
	
	//学生主页面
		@RequestMapping("/studenteva")
		public ModelAndView studenteva(Integer classid,Integer studentid) {
			List<Teaching> tc=getStus.getTeacher(classid);
			ModelAndView mv = new ModelAndView("student/studenteva");
			mv.addObject("tc",tc);
			mv.addObject("studentid",studentid);
			return mv;
		}

	//年龄计算
	@RequestMapping("/getAge")
	public ModelAndView getAge(String  birthday) throws ParseException{
		  System.out.println("1");
		 SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		 Date mydate = myFormatter.parse(birthday);
		 ModelAndView mv = new ModelAndView();
		 int age = getAgeByBirth(mydate);
	     mv.addObject("age", age);
         return mv;	
	}

	//修改提交
	private int getAgeByBirth(Date mydate) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 修改学生界面
	@RequestMapping("/studentedit")
	public ModelAndView studentedit(int studentid) {
		Student stu = getStus.getStudentBystuid(studentid);
		List<Classtb> clas = claser.getclass();
		ModelAndView mv = new ModelAndView("student/studentedit");
		mv.addObject("stu", stu);
     	mv.addObject("clas",clas);
		return mv;
	}

	// 修改学生
	@RequestMapping("updatestucontroll")
	@ResponseBody
	public ResultMsg update(Student student) {
		int i = getStus.updateStudent(student);
		if (i > 0) {

			return new ResultMsg(1, "更新学生成功");

		}
		return new ResultMsg(0, "更新学生失败");

	}

	// 增加学生界面
	@RequestMapping("/studentadd")
	public ModelAndView studentadd() {
		ModelAndView mv = new ModelAndView("student/studentadd");
		return mv;
	}

	// 增加学生
	@RequestMapping("addstudent")
	@ResponseBody
	public ResultMsg addstudent(Student student) {

		int i = getStus.studentadd(student);
		if (i > 0) {
			return new ResultMsg(1, "更新成功");
		}
		return new ResultMsg(0, "更新失败");
	}

	

	
	//删除

	@RequestMapping("studentdel")
	@ResponseBody
	public ResultMsg studentdel(int studentid) {

		int i = getStus.studentdel(studentid);

		if (i > 0) {
			return new ResultMsg(1, "删除成功");
		}
		return new ResultMsg(0, "删除失败");

	}

	// 学生批量删除

	
	
	//批量删除

	@RequestMapping("studentall")
	@ResponseBody
	public ResultMsg studentall(String aa) {

		String[] aa1 = aa.split(",");
		int i = getStus.studentall(aa1);
		System.out.println(aa);
		System.out.println(aa.length());
		if (i > 0) {
			return new ResultMsg(1, "删除成功");
		}
		return new ResultMsg(0, "删除失败");
	}
	
	//Excel
	@RequestMapping("studentimport")
	public String test() {
		return "/student/student-import";
	}
	
	//Excelutil 
	@RequestMapping("Excelstudent")
	public String excelin(MultipartFile file,ModelMap map) throws Exception {
		InputStream in = file.getInputStream();
		Student student =null;
        List<List<Object>> listob = null;
        listob=new Excelutil().getBankListByExcel(in, file.getOriginalFilename());
        in.close();
        int result = 0;
        for(int i=0;i<listob.size();i++) {
        	student = new Student();
        	List<Object> li = listob.get(i);
        	student.setStudentnumber(String.valueOf(li.get(0)));
        	student.setName(String.valueOf(li.get(1)));
        	student.setPassword(String.valueOf(li.get(2)));
        	student.setPower(Integer.valueOf((String) li.get(3)));
        	student.setClassid(Integer.valueOf((String) li.get(4)));
        	result = getStus.studentadd(student);
        }
        //System.out.println(result);
        if (result>0) {
         	map.put("reslut1", 1);
 		}else {
 			map.put("reslut1", 2);
 		}
       return "student/student-import";
	}
	
	//模糊查询
	@RequestMapping("mselect")
	public ModelAndView mselect(String name) {
		List<Student> list =getStus.mhselect(name);
		ModelAndView mv = new ModelAndView("student/studentlist");
		mv.addObject("students",list);
		return mv;
	}
	//学生信息修改
			@RequestMapping("/studenteupdate")
			@ResponseBody
			public ResultMsg Studenteupdate(String studentnumber,String name,String sex,String tel,String password) {	
				Student student=new Student();
				student.setStudentnumber(studentnumber);
				student.setName(name);
				student.setSex(sex);
				student.setTel(tel);
				student.setPassword(password);
				int i = getStus.Studentupdate(student);
				if (i > 0) {
					return new ResultMsg(1, "更新成功");
				}
				return new ResultMsg(0, "更新失败");
			}
			
			//学生信息
			@RequestMapping("/info")
			public ModelAndView info(String studentnumber) {
				Student student=getStus.getStudentBystuno(studentnumber);
				ModelAndView mv = new ModelAndView("student/myinfo");
				mv.addObject("student", student);
				return mv;
			}
			//重置密码
			 @RequestMapping("/resetpwd")
			 @ResponseBody
			 public Result resetpwd(int teacherid) {
				   int i=getStus.resetpwd(teacherid);
				   if(i>0) {
					   return new Result(1, "重置成功");
				   }else {
					   return new Result(0, "重置失败");
				   }
			 }	

}
