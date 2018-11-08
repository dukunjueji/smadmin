package com.uc.training.smadmin.bd.dao;
import com.uc.training.smadmin.bd.model.MemberGrade;

import java.util.List;
/**
 * @author xudong.yu@ucarinc.com
 * @date 2018-10-17 星期三 15:41
 * @description:MemberGradeDao数据库操作接口类
 */
public interface MemberGradeDao{

	/**
	 * 查询会员等级列表
	 * @return 会员等级列表
	 */
	List<MemberGrade>  queryMemberGradeList();

	/**
	 * 查询会员等级数量
	 * @return
	 */
	public Long queryMemberGradeCount();

	/**
	 * 新增会员等级
	 * @param record 会员等级信息
	 * @return
	 */
	Long insertMemberGrade(MemberGrade record);

	/**
	 * 更新会员等级
	 * @param record 会员等级信息
	 * @return
	 */
	Integer updateMemberGradeById(MemberGrade record);

	/**
	 * 通过用户ID获取对应的等级信息
	 * @param id
	 * @return
	 */
	MemberGrade getByUId(Long id);

}