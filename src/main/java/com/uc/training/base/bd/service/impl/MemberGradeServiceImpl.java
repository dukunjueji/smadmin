package com.uc.training.base.bd.service.impl;

import com.uc.training.smadmin.bd.dao.MemberGradeDao;
import com.uc.training.smadmin.bd.model.MemberGrade;
import com.uc.training.smadmin.bd.service.MemberGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/17 15:20
 * @Description:
 */
@Service
public class MemberGradeServiceImpl implements MemberGradeService {

    @Autowired
    private MemberGradeDao memberGradeDao;

    @Override
    public List<MemberGrade> getList() {
        return memberGradeDao.queryMemberGradeList();
    }

    @Override
    public Integer modifyMemberGrade(MemberGrade grade) {
        return memberGradeDao.updateMemberGradeById(grade);
    }

    @Override
    public Long queryMemberGradeCount() {
        return memberGradeDao.queryMemberGradeCount();
    }

    @Override
    public Double getDiscountByUId(Long id) {
        return memberGradeDao.getByUId(id).getDiscount();
    }


}
