package com.uc.training.base.bd.controller;

import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.service.MemberService;
import com.uc.training.base.bd.vo.MemberListVO;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 余旭东
 * @Date: 2018/10/29 16:44
 * @Description:
 */
@RequestMapping("/admin/bd/member")
@RestController
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
    public Result<PageVO<MemberRE>> getMemberList(MemberListVO memberListVO) {
        try {
            PageVO<MemberRE> pageVO = new PageVO<>();
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
