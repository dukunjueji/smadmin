package com.uc.training.smadmin.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.MemberListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ycc.base.common.Result;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/29 16:44
 * @Description:
 */
@RequestMapping("/admin/member")
@Controller
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/getMemberList.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageVO<Member>> getMemberList(MemberListVO memberListVO){
        Result<PageVO<Member>> res;
        try {
            PageVO<Member> pageVO = new PageVO<Member>();
            pageVO.setPageIndex(memberListVO.getPageIndex());
            pageVO.setPageSize(memberListVO.getPageSize());
            pageVO.setTotal(memberService.queryMemberCount(memberListVO));
            pageVO.setDataList(memberService.getMemberList(memberListVO));
            res = Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取smsTemplate分页失败", null);
        }
        return res;
    }
}
