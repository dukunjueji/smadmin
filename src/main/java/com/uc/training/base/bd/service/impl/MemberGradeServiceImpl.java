package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.re.MemberGradeRE;
import com.uc.training.base.bd.service.MemberGradeService;
import com.uc.training.base.bd.vo.MemberGradeVO;
import com.uc.training.remote.client.BaseClient;
import com.uc.training.smadmin.bd.dao.MemberGradeDao;
import com.uc.training.smadmin.bd.model.MemberGrade;
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

    @Override
    public List<MemberGradeRE> getList(MemberGradeVO memberGradeVO) {
        return BaseClient.queryMemberGradeList(memberGradeVO);
    }

    @Override
    public Integer modifyMemberGrade(MemberGradeVO grade) {
        return BaseClient.modifyMemberGrade(grade);
    }

}
