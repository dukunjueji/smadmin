package com.uc.training.smadmin.bd.service;

import com.uc.training.smadmin.bd.model.MemberGrade;
import com.uc.training.smadmin.bd.vo.MemberGradeListVO;

import java.util.List;

/**
 * @author 余旭东
 */
public interface MemberGradeService {
    /**
     * 获取会员等级列表
     * @return
     */
    public List<MemberGrade> getList();

    /**
     * 修改会员等级
     * @param grade
     * @return
     */
    public Integer modifyMemberGrade(MemberGrade grade);

    /**
     * 查找数据总记录数
     */
    public Long queryMemberGradeCount();
}
