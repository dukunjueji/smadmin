package com.uc.training.base.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.MemberListVO;
import com.ycc.base.common.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 余旭东
 * @Date: 2018/10/29 16:44
 * @Description:
 */
@RequestMapping("/admin/bd/member")
@Controller
public class AdminMemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    /**
     * 获取会员列表
     *
     * @param memberListVO
     * @return com.ycc.base.common.Result<com.uc.training.common.vo.PageVO < com.uc.training.smadmin.bd.model.Member>>
     */
    @RequestMapping(value = "/getMemberList.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageVO<Member>> getMemberList(MemberListVO memberListVO) {
        try {
            PageVO<Member> pageVO = new PageVO<Member>();
            pageVO.setPageIndex(memberListVO.getPageIndex());
            pageVO.setPageSize(memberListVO.getPageSize());
            pageVO.setTotal(memberService.queryMemberCount(memberListVO));
            pageVO.setDataList(memberService.getMemberList(memberListVO));
            return Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            return Result.getBusinessException("获取smsTemplate分页失败", null);
        }
    }
}
