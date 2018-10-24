package com.uc.training.smadmin.bd.dao.impl;
import com.uc.training.smadmin.bd.model.MemberGrade;
import com.uc.training.smadmin.bd.dao.MemberGradeDao;
import com.uc.training.smadmin.bd.vo.MemberGradeListVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author xudong.yu@ucarinc.com
 * @date 2018-10-17 星期三 15:41
 * @description:MemberGradeDao数据库操作接口类
 */
@Repository
public class MemberGradeDaoImpl extends CarIsIbatisDaoImpl implements MemberGradeDao {

	/**
	 * 通过主键来查找查找
	 */
	 @Override
	 public List<MemberGrade> getMemberGradeById(Long id){
		  return (List<MemberGrade>) this.queryForObject("com.uc.training.smadmin.bd.dao.MemberGradeDao.getMemberGradeById", id);
	 }

	/**
	 * 查询列表
	 */
	 @Override
	 public List<MemberGrade>  queryMemberGradeList(MemberGradeListVO memberGradeListVO){
		  return this.queryForList("com.uc.training.smadmin.bd.dao.MemberGradeDao.queryMemberGradeList", memberGradeListVO);
	 }

	/**
	 * 查找数据总记录数
	 */
	 @Override
	public Long queryMemberGradeCount(){
		  return (Long) this.queryForObject("com.uc.training.smadmin.bd.dao.MemberGradeDao.queryMemberGradeCount");
	 }

	/**
	 * 保存
	 */
	 @Override
	public Long insertMemberGrade( MemberGrade record ){
		  return (Long) this.insert("com.uc.training.smadmin.bd.dao.MemberGradeDao.insertMemberGrade", record);
	 }

	/**
	 * 更新
	 */
	 @Override
	public Integer updateMemberGradeById( MemberGrade record ){
		  return this.update("com.uc.training.smadmin.bd.dao.MemberGradeDao.updateMemberGradeById", record);
	 }

}