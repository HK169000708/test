package evaluation.entity;

public class Teacher_V {
	private int teachingid;
	public int getTeachingid() {
		return teachingid;
	}
	public void setTeachingid(int teachingid) {
		this.teachingid = teachingid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	private int courseid;
	private String coursename;
	private int classid;
	private String classname;
	private int teacherid;
	private String teachername;
	private Score_V sv;
	public Score_V getSv() {
		return sv;
	}
	public void setSv(Score_V sv) {
		this.sv = sv;
	}

}
