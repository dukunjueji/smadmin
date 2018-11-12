package com.uc.training.smadmin.bd.dao.impl;
import com.uc.training.smadmin.bd.model.MemberGrade;
import com.uc.training.smadmin.bd.dao.MemberGradeDao;
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

	private static final String NAMESPACE = "com.uc.training.smadmin.bd.dao.MemberGradeDao.";

	/**
	 * 查询列表
	 */
	 @Override
	 public List<MemberGrade>  queryMemberGradeList(){
		  return this.queryForList(NAMESPACE + "queryMemberGradeList");
	 }

	/**
	 * 查找数据总记录数
	 */
	 @Override
	public Long queryMemberGradeCount(){
		  return (Long) this.queryForObject(NAMESPACE + "queryMemberGradeCount");
	 }

	/**
	 * 新增
	 */
	 @Override
	public Long insertMemberGrade( MemberGrade record ){
		  return (Long) this.insert(NAMESPACE + "insertMemberGrade", record);
	 }

	/**
	 * 更新
	 */
	 @Override
	public Integer updateMemberGradeById( MemberGrade record ){
		  return this.update(NAMESPACE + "updateMemberGradeById", record);
	 }

	@Override
	public MemberGrade getByUId(Long id) {
		return (MemberGrade) this.queryForObject(NAMESPACE + "getByUId", id);
	}

}