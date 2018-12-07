package com.uc.training.gds.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.CommentEnum;
import com.uc.training.common.enums.CommentReplyEnum;
import com.uc.training.gds.re.CommentAvgRE;
import com.uc.training.gds.service.CommentReplyService;
import com.uc.training.gds.service.CommentService;
import com.uc.training.gds.vo.CommentListVO;
import com.uc.training.gds.vo.CommentReplyInsertVO;
import com.uc.training.gds.vo.CommentReplyVO;
import com.uc.training.gds.vo.CommentVO;
import com.uc.training.gds.re.CommentRE;
import com.ycc.base.common.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: TODO
 *
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/11/21  14:10
 **/

@Controller
@RequestMapping("/admin/gds/comment")
public class AdminCommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentReplyService commentReplyService;

    /**
     * hhj
     * 获取列表
     *
     * @param commentListVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCommentList.do_", method = RequestMethod.GET)
    public Result getCommentList(CommentListVO commentListVO) {
        Map map = new HashMap(16);
        List<CommentRE> list = commentService.getCommentList(commentListVO);
        CommentAvgRE commentAvgVO = commentService.getCommentCountAndAvg(commentListVO);
        map.put("commentList", list);
        map.put("count", commentAvgVO.getCount());
        return Result.getSuccessResult(map);
    }

    /**
     * 添加/修改后台回复
     * @param commentReplyInsertVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCommentReply.do_", method = RequestMethod.POST)
    public Result insertCommentReply(@Validated CommentReplyInsertVO commentReplyInsertVO) {
        CommentReplyVO commentReplyVO = new CommentReplyVO();
        BeanUtils.copyProperties(commentReplyInsertVO, commentReplyVO);
        commentReplyVO.setType(CommentReplyEnum.ADMIN_RELY.getKey());
        commentReplyVO.setMemberId(getUid());
        Integer count;
        //添加
        if (commentReplyService.getAdminReplyContent(commentReplyInsertVO.getCommentId()) == null) {
            count = commentReplyService.insertCommentReply(commentReplyVO).intValue();
            //修改评论表状态
            CommentVO commentVO = new CommentVO();
            commentVO.setId(commentReplyInsertVO.getCommentId());
            commentVO.setStatus(CommentEnum.COMMENT_RE.getKey());
            count = commentService.editCommentById(commentVO);
        } else {
            //修改
            count = commentReplyService.editCommentReply(commentReplyVO);
        }
        if (count > 0) {
            return Result.getSuccessResult(null);
        } else {
            return Result.getBusinessException("回复/修改失败！！！！",null);
        }
    }

}
