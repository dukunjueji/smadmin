package com.uc.training.base.bd.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.model.MemberGrade;
import com.uc.training.smadmin.bd.service.MemberGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/17 13:50
 * @Description:
 */
@Controller
@RequestMapping("/admin/bd/memberGrade")
public class MemberGradeController extends BaseController {

    @Autowired
    private MemberGradeService memberGradeService;

    /**
     * 显示会员等级列表
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getMemberGrade.do_", method = RequestMethod.GET)
    public Result<List<MemberGrade>> getDemoPage() {
        return Result.getSuccessResult(memberGradeService.getList());
    }


    /**
     * 修改会员等级信息
     * @param grade
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/modifyGrade.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> modifyGrade(@Validated MemberGrade grade){
        if (grade.getId() == null) {
            return Result.getBusinessException("会员ID不能为空", null);
        }
        grade.setModifyEmp(getUid());
        return Result.getSuccessResult(memberGradeService.modifyMemberGrade(grade));
    }
}
