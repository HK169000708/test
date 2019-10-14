package evaluation.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;

import evaluation.entity.Result;
import evaluation.entity.ResultMsg;
import evaluation.entity.Score;
import evaluation.entity.Teacher;
import evaluation.entity.Teacher_V;
import evaluation.service.ScoreService;
import evaluation.service.TeacherService;



import evaluation.util.Excelutil;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	//注入service
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ScoreService scoreService;
	//教师主页面
		@RequestMapping("/index")
		public ModelAndView Index() {
			ModelAndView mv = new ModelAndView("teacher/index");
			return mv;
		}
//欢迎主页面
		@RequestMapping("/welcome")
		public ModelAndView welcome() {
			ModelAndView mv = new ModelAndView("teacher/welcome");
			return mv;
				}
	
	/*//教师列表
	@RequestMapping("/teacher-list")
	 public ModelAndView Teacher() {
		List<Teacher> teachers=teacherService.getTeachers();
		 ModelAndView mv=new ModelAndView("teacher/teacher-list");
		 mv.addObject("teachers", teachers);
		 return mv;
	 }*/
		
	@RequestMapping("/delete")
	 public ModelAndView Delete(String teachernumber) {
		 teacherService.delTeacher(teachernumber);
		 ModelAndView mv=new ModelAndView("teacher/teacher-list");
		 return mv;
	 }

	//新增页面
	@RequestMapping("/add")
	 public ModelAndView Add() {
		 ModelAndView mv=new ModelAndView("teacher/add");
		 return mv;
	 }
	//新增提交
	@RequestMapping("/add-submit")
	 public ResultMsg Add_submit(Teacher teacher) {
		//新增教师
			int i=teacherService.addTeacher(teacher);
			if(i>0){
				return new ResultMsg(1,"添加成功");
			}else if(i==-2){
				return new ResultMsg(2,"该教师已经存在");
			}
			return new ResultMsg(0,"添加失败");
		}

	//修改页面
	@RequestMapping("/update")
	 public ModelAndView Update(int teacherid) {
		 Teacher teacher=teacherService.getTeacherByid(teacherid);
		 ModelAndView mv=new ModelAndView("teacher/update");
		 mv.addObject("teacher", teacher);
		 return mv;
	 }
	
	//修改提交
	@RequestMapping("/update-submit")
	 public ResultMsg Update_submit(Teacher teacher) {
		//新增教师
			int i=teacherService.updateTeacher(teacher);
			if(i>0){
				return new ResultMsg(1,"修改成功");
			}else if(i==-2){
				return new ResultMsg(2,"该教师已经存在");
			}
			return new ResultMsg(0,"修改失败");
		}
	

	 @RequestMapping("/resetpwd")
	 @ResponseBody
	 public Result resetpwd(int teacherid) {
		   int i=teacherService.resetpwd(teacherid);
		   if(i>0) {
			   return new Result(1, "重置成功");
		   }else {
			   return new Result(0, "重置失败");
		   }
	 }
	
	//登录页面   

	@RequestMapping("/teacherlist")
	public ModelAndView Teacherlist() {
		List<Teacher> teachers =teacherService.getTeachersmajor();
		ModelAndView mv = new ModelAndView("teacher/teacher-list");
		mv.addObject("teachers", teachers);
		return mv;
	}
        
	 @RequestMapping("/login")
     public ModelAndView Login() {
    	 ModelAndView mv=new ModelAndView("teacher/login");
    	 return mv;
}

	//登录判断
		 @RequestMapping("/login_submit")
		 public ModelAndView  Login_submit(Model model,Teacher teacher){
			 Teacher teach=teacherService.geTeacher(teacher);
			 
			 model.addAttribute("teach",teach);
			 ModelAndView mv=new ModelAndView("manager/index");
			 ModelAndView mv2=new ModelAndView("teacher/index");
			 int isLogin=teacherService.login(teacher.getTeachernumber(),teacher.getPassword());
			 if(isLogin==1) {
				 System.out.println("hello");
				 return mv2;
			 }
			 return mv;
			 
		 }
	
	 
	//批量删除
		@RequestMapping("delallteacher")
		@ResponseBody
		public ResultMsg Byincourse(String ids) {
			//System.out.println(ids);
			String[] teacherids = ids.split(",");
			System.out.println(ids);
			int i = teacherService.delAllTeacher(teacherids);
			if(i>0) {
				return new ResultMsg(1, "删除成功");
			}else {
				return new ResultMsg(2, "删除 失败");
			}
		}
	 
		//模糊查询
		@RequestMapping("mselect")
		public ModelAndView Mselect(String name) {
			List<Teacher> list = teacherService.mhselect(name);							
			ModelAndView mv = new ModelAndView("teacher/teacher-list");
			mv.addObject("teachers",list);
			return mv;
		}
		
		//Excel
		@RequestMapping("teacherimport")
		public String test() {
			return "/teacher/teacher-import";
		}
		
		//Excelutil 
		@RequestMapping("Excelteacher")
		public String excelin(MultipartFile file,ModelMap map) throws Exception {
			InputStream in = file.getInputStream();
	        Teacher teacher =null;
	        List<List<Object>> listob = null;
	        listob=new Excelutil().getBankListByExcel(in, file.getOriginalFilename());
	        in.close();
	        int result = 0;
	        for(int i=0;i<listob.size();i++) {
	        	teacher = new Teacher();
	        	List<Object> li = listob.get(i);
	        	teacher.setName(String.valueOf(li.get(0)));
	        	teacher.setTeachernumber(String.valueOf(li.get(1)));
	        	teacher.setSex(String.valueOf(li.get(2)));
	        	teacher.setPassword(String.valueOf(li.get(3)));
	        	teacher.setPower(Integer.valueOf((String) li.get(4)));
	        	teacher.setMajorid(Integer.valueOf((String) li.get(5)));
	        	result = teacherService.addTeacher(teacher);
	        }
	        //System.out.println(result);
	        if (result>0) {
	         	map.put("reslut1", 1);
	 		}else {
	 			map.put("reslut1", 2);
	 		}
	       return "teacher/teacher-import";
		}
		
		@RequestMapping("evateacher")
		public ModelAndView evateacherlist(Teacher teacher) {
			
			List<Teacher> evatea =teacherService.getTeachering(teacher);
            ModelAndView mv = new ModelAndView("teacher/evateacherlist");		
            mv.addObject("evatea",evatea);
            mv.addObject("teacherid",teacher.getTeacherid());
           
			return mv;
		}
		
		//保存教师评价
		@RequestMapping("questions")
		public ModelAndView questions(int teacherid,int teachingid) {
			System.out.println(teachingid);
			Score score=new Score();
			score.setTeacherid(teacherid);
			score.setTeachingid(teachingid);
			int i=scoreService.getScorewithtea(score);
			if(i>0) {
				ModelAndView mv1=new ModelAndView("teacher/evaresult");
				return mv1;
			}else {
			
			 ModelAndView mv = new ModelAndView("teacher/questions");
			 mv.addObject("teacherid",teacherid);
	         mv.addObject("teachingid",teachingid);
			 return mv;
			}
		}
		//个人信息修改
		
				//int teacherid = 1;
				@RequestMapping("myteacherup")
				public ModelAndView myteacherup(int teacherid) {
					Teacher teacher =  teacherService.getTeacherByid(teacherid);
					ModelAndView mv = new ModelAndView("teacher/myteacherup");
					mv.addObject("teacher",teacher);
					return mv;
				}
				@RequestMapping("/myupdate")
				@ResponseBody
				public ResultMsg myupdate(String name,String teachernumber,String sex,String password,String phone) {
					Teacher teacher =new Teacher();
					teacher.setName(name);
					teacher.setPassword(password);
					teacher.setTeachernumber(teachernumber);
					teacher.setSex(sex);
					teacher.setPhone(phone);
					int i = teacherService.myupdate(teacher);
					if(i>0) {
						return new ResultMsg(1, "修改成功");
					}else {
						return new ResultMsg(2, "修改失败");
					}
				}
				
				//查看得分
				@RequestMapping("score")
				public  ModelAndView getClass(int teacherid) {
					List<Object> Tv=teacherService.getClassa(teacherid);
					List<Teacher_V> Tv1=teacherService.getClass(teacherid);
					ModelAndView mView=new ModelAndView("teacher/score");
					mView.addObject("Tv", Tv);
					mView.addObject("Tv1", Tv1);
					return mView;
				}
}

