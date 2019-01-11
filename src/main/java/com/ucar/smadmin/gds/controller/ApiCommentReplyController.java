package com.ucar.smadmin.gds.controller;

import com.ucar.smapi.base.bd.re.MemberRE;
import com.ucar.smadmin.base.bd.service.MemberService;
import com.ucar.smadmin.base.bd.vo.MemberVO;
import com.ucar.smadmin.common.annotation.AccessLogin;
import com.ucar.smadmin.common.base.controller.BaseController;
import com.ucar.smapi.common.vo.Result;
import com.ucar.smadmin.enums.CommentReplyEnum;
import com.ucar.smadmin.gds.service.CommentReplyService;
import com.ucar.smadmin.gds.vo.CommentReplyInsertVO;
import com.ucar.smadmin.gds.vo.CommentReplyVO;
import com.ucar.smapi.gds.re.CommentDetailRE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
@Controller
@RequestMapping("api/gds/commentReply")
public class ApiCommentReplyController extends BaseController {

    @Autowired
    private CommentReplyService commentReplyService;

    @Autowired
    private MemberService memberService;

    /**
     * 根据评论id获取评论详情
     * @param commentId
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "/getCommentDetailByCommentId.do_", method = RequestMethod.POST)
    public Result<CommentDetailRE> getCommentDetailByCommentId(Long commentId) {
        if (commentId == null) {
            return Result.getBusinessException("请重新选择评论！", null);
        }
        CommentReplyVO commentReplyVO = new CommentReplyVO();
        commentReplyVO.setCommentId(commentId);
        commentReplyVO.setMemberId(getUid());
        return Result.getSuccessResult(commentReplyService.getCommentDetailByCommentId(commentReplyVO));
    }
    /**
     * 新增游客商品详情回复
     * @param commentReplyInsertVO
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "/insertCommentReply.do_", method = RequestMethod.POST)
    public Result<Long> insertCommentReply(@Validated CommentReplyInsertVO commentReplyInsertVO) {

        CommentReplyVO commentReply = new CommentReplyVO();
        BeanUtils.copyProperties(commentReplyInsertVO, commentReply);
        commentReply.setType(CommentReplyEnum.OTHER_REPLY.getKey());
        commentReply.setMemberId(getUid());
        commentReply.setCreateEmp(getUid());
        commentReply.setModifyEmp(getUid());

        //获取会员信息
        MemberVO member = new MemberVO();
        member.setId(getUid());
        MemberRE memberRE = memberService.queryOneMember(member);
        commentReply.setMemberNickname(memberRE.getNickname());
        commentReply.setMemberImageUrl(memberRE.getImageUrl());

        return Result.getSuccessResult(commentReplyService.insertCommentReply(commentReply));
    }
    /**
     * 删除商品详情回复
     * @param id
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "/deleteCommentReplyById.do_", method = RequestMethod.POST)
    public Result<Integer> deleteCommentReplyById(Long id) {
        if (id == null) {
            return Result.getBusinessException("删除失败！", null);
        }
        CommentReplyVO commentReplyVO = new CommentReplyVO();
        commentReplyVO.setId(id);
        commentReplyVO.setMemberId(getUid());
        return Result.getSuccessResult(commentReplyService.deleteCommentReplyById(commentReplyVO));
    }

}
