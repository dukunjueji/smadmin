package com.uc.training.bd.controller;

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
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/getMemberList.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageVO<Member>> getMemberList(@Validated MemberListVO memberListVO){
        if (!StringUtils.isEmpty(memberListVO.getTelephone())) {
            String regex = "^1[3456789]\\d{9}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(memberListVO.getTelephone());
            if (!m.matches()) {
                return Result.getBusinessException("手机号格式不正确", null);
            }
        }
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
