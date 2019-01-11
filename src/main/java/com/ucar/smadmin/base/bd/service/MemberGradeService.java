package com.ucar.smadmin.base.bd.service;

import com.ucar.smadmin.base.bd.re.MemberGradeRE;
import com.ucar.smadmin.base.bd.vo.MemberGradeVO;

import java.util.List;

/**
 * @author 余旭东
 */
public interface MemberGradeService {
    /**
     * 获取会员等级列表
     * @param memberGradeVO
     * @return
     */
    List<MemberGradeRE> getList(MemberGradeVO memberGradeVO);

    /**
     * 修改会员等级
     * @param grade 会员等级信息
     * @return 被修改的行数
     */
    Integer modifyMemberGrade(MemberGradeVO grade);

    /**
     * 查找数据总记录数
     * @return
     */
    Long queryMemberGradeCount();

    /**
     * 通过用户ID获取折扣
     * @param id
     * @return
     */
    Double getDiscountByUId(Long id);
}
