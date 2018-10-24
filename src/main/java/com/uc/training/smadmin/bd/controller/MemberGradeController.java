package com.uc.training.smadmin.bd.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.bd.model.MemberGrade;
import com.uc.training.smadmin.bd.service.MemberGradeService;
import com.uc.training.smadmin.bd.vo.MemberGradeListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 余旭东
 * @Date: 2018/10/17 13:50
 * @Description:
 */
@Controller
@RequestMapping("/api/memberGrade")
public class MemberGradeController extends BaseController {

    @Autowired
    private MemberGradeService memberGradeService;

    /**
     * 显示会员等级列表
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getDemoPage.do_", method = RequestMethod.GET)
    public Result<PageVO<MemberGrade>> getDemoPage(MemberGradeListVO memberGradeListVO) {
        Result<PageVO<MemberGrade>> res;
        try {
            PageVO<MemberGrade> pageVO = new PageVO<MemberGrade>();
            pageVO.setPageIndex(memberGradeListVO.getPageIndex());
            pageVO.setPageSize(memberGradeListVO.getPageSize());
            pageVO.setTotal(memberGradeService.queryMemberGradeCount());
            pageVO.setDataList(memberGradeService.getList(memberGradeListVO));
            res = Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取demo分页失败", null);
        }
        return res;
    }


    /**
     * 修改会员等级信息
     * @param grade
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/modifyGrade.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> modifyGrade(MemberGrade grade){
        grade.setModifyEmp(getUid());
        return Result.getSuccessResult(memberGradeService.modifyMemberGrade(grade));
    }
}
