package com.ucar.smadmin.base.bd.service.impl;

import com.ucar.smadmin.base.bd.dto.MemberGradeDTO;
import com.ucar.smadmin.base.bd.re.MemberGradeRE;
import com.ucar.smadmin.base.bd.service.MemberGradeService;
import com.ucar.smadmin.base.bd.vo.MemberGradeVO;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
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
    private BaseClient baseClient;

    @Override
    public List<MemberGradeRE> getList(MemberGradeVO memberGradeVO) {
        MemberGradeDTO memberGradeDTO = new MemberGradeDTO();
        BeanUtils.copyProperties(memberGradeVO, memberGradeDTO);
        return baseClient.queryMemberGradeList(memberGradeDTO).getRe();
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
