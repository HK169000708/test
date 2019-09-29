package evaluation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluation.dao.ScoreDao;

import evaluation.entity.Score;
@Service
public class ScoreService {
	@Autowired
	private ScoreDao scoreDao;
	//删除
	public int deleteScore(int scoreid) {
		return scoreDao.deleteScore(scoreid);
	}
	//查询所有 关联授课 教师 学生
	public  List<Score> getScore(){
	   return scoreDao.getScore();
	}
	//查询所有 关联教师
//	 public  List<Score> getScore2(){
//		 return scoreDao.getScore2();
//	 }
//	//查询所有 关联学生
//	 public  List<Score> getScore3(){
//		 return scoreDao.getScore3();
//	 }
//	 //查询授课 关联教师
//	public List<Teaching> getTeacher(){
//		 return scoreDao.getTeacher();
//	 }
	//根据id批量删除
	public int delAllScore(String[] ids) {
			return scoreDao.delAllScore(ids);
		}
	//模糊查询
	public List<Score> mhselect(String teachername){
				List<Score> list = scoreDao.mhselect(teachername);
				return list;
				
			}
	//问题保存
	public int insertquestion(Score score) {
		return scoreDao.insertquestion(score);
	}
}
