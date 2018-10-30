package com.uc.training.smadmin.bd.dao;
import com.uc.training.smadmin.bd.model.MemberGrade;
import com.uc.training.smadmin.bd.vo.MemberGradeListVO;

import java.util.List;
/**
 * @author xudong.yu@ucarinc.com
 * @date 2018-10-17 星期三 15:41
 * @description:MemberGradeDao数据库操作接口类
 */
public interface MemberGradeDao{

	/**
	 * 通过主键来查找查找
	 */
	 public List<MemberGrade>  getMemberGradeById(Long id);

	/**
	 * 查询列表
	 */
	 public List<MemberGrade>  queryMemberGradeList();

	/**
	 * 查找数据总记录数
	 */
	public Long queryMemberGradeCount();

	/**
	 * 保存
	 */
	public Long insertMemberGrade(MemberGrade record);

	/**
	 * 更新
	 */
	public Integer updateMemberGradeById(MemberGrade record);

	/**
	 * 通过用户ID获取对应的等级信息
	 * @param id
	 * @return
	 */
	public MemberGrade getByUId(Long id);

}