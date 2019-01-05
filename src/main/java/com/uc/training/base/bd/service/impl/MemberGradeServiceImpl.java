package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.dto.MemberGradeDTO;
import com.uc.training.base.bd.re.MemberGradeRE;
import com.uc.training.base.bd.service.MemberGradeService;
import com.uc.training.base.bd.vo.MemberGradeVO;
import com.uc.training.remote.client.BaseClient;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
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
    private  BaseClient baseClient;

    @Override
    public List<MemberGradeRE> getList(MemberGradeVO memberGradeVO) {
        MemberGradeDTO memberGradeDTO = new MemberGradeDTO();
        BeanUtils.copyProperties(memberGradeVO, memberGradeDTO);
        return baseClient.queryMemberGradeList(memberGradeVO).getRe();
    }

    @Override
    public Integer modifyMemberGrade(MemberGradeVO grade) {
        MemberGradeDTO memberGradeDTO = new MemberGradeDTO();
        BeanUtils.copyProperties(grade, memberGradeDTO);
        return baseClient.modifyMemberGrade(memberGradeDTO).getRe();
    }

    /**
     * 查找数据总记录数
     *
     * @return
     */
    @Override
    public Long queryMemberGradeCount() {
        return null;
    }

    /**
     * 通过用户ID获取折扣
     *
     * @param id
     * @return
     */
    @Override
    public Double getDiscountByUId(Long id) {
        return baseClient.getDiscountByUId(id).getRe();
    }

}
