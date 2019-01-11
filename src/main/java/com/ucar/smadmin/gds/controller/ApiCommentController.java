package com.ucar.smadmin.gds.controller;

import com.ucar.smadmin.base.bd.re.MemberRE;
import com.ucar.smadmin.base.bd.service.MemberService;
import com.ucar.smadmin.base.bd.vo.MemberVO;
import com.ucar.smadmin.common.annotation.AccessLogin;
import com.ucar.smadmin.common.base.controller.BaseController;
import com.ucar.smadmin.common.vo.Result;
import com.ucar.smadmin.enums.CommentEnum;
import com.ucar.smadmin.enums.OrderGoodsCommentEnum;
import com.ucar.smadmin.gds.re.CommentAvgRE;
import com.ucar.smadmin.gds.re.CommentCountRE;
import com.ucar.smadmin.gds.re.GoodsDetailRE;
import com.ucar.smadmin.gds.service.CommentService;
import com.ucar.smadmin.gds.service.GoodsService;
import com.ucar.smadmin.gds.vo.AddCommentVO;
import com.ucar.smadmin.gds.vo.CommentListVO;
import com.ucar.smadmin.gds.vo.CommentVO;
import com.ucar.smadmin.ord.service.OrderGoodsService;
import com.ucar.smadmin.ord.vo.OrdOrderGoodsVO;
import com.ucar.smadmin.gds.re.CommentRE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author hhj
 * @Version 1.0
 * @date 2018/11/17
 */
@Controller
@RequestMapping("api/gds/comment")
public class ApiCommentController extends BaseController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    /**
     * hhj
     * 添加评论
     * @param addCommentVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addComment.do_", method = RequestMethod.POST)
    public Result addComment(@Validated AddCommentVO addCommentVO) {
        CommentVO commentVo = new CommentVO();
        OrdOrderGoodsVO ordOrderGoodsVo = new OrdOrderGoodsVO();
        ordOrderGoodsVo.setId(addCommentVO.getOrderGoodsId());
        //校验是否已有评论
        List<CommentCountRE> list = commentService.getCommentCountByOrderGoodsId(addCommentVO.getOrderGoodsId());
        if (addCommentVO.getStatus().equals(OrderGoodsCommentEnum.HAVE_COMMENT.getKey()) && !CollectionUtils.isEmpty(list)) {
            //更改商品订单表评论状态
            ordOrderGoodsVo.setCommentStatus(OrderGoodsCommentEnum.HAVE_COMMENT.getKey());
            orderGoodsService.upOrdGoodsCommentStatus(ordOrderGoodsVo);
            return Result.getBusinessException("该商品已经评论过不能再评论", null);
        }

        // 追评校验
        if (addCommentVO.getStatus().equals(OrderGoodsCommentEnum.AFTER_HAVE_COMMENT.getKey())) {
            if (CollectionUtils.isEmpty(list)) {
                ordOrderGoodsVo.setCommentStatus(OrderGoodsCommentEnum.NO_COMMENT.getKey());
                orderGoodsService.upOrdGoodsCommentStatus(ordOrderGoodsVo);
                return Result.getBusinessException("该商品尚未评论不能追评", null);
            } else {
                if (list.get(0).getParentId() != null) {
                    ordOrderGoodsVo.setCommentStatus(OrderGoodsCommentEnum.AFTER_HAVE_COMMENT.getKey());
                    orderGoodsService.upOrdGoodsCommentStatus(ordOrderGoodsVo);
                    return Result.getBusinessException("该商品已有追论不能再追评", null);
                }
                commentVo.setParentId(list.get(0).getId());
            }
        }

        // 拉取商品信息插入commentVo
        GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(addCommentVO.getGoodsPropertyId());
        if (gdDTO == null) {
            return Result.getBusinessException("获取不到商品信息", null);
        }
        commentVo.setOrderGoodsId(addCommentVO.getOrderGoodsId());
        commentVo.setGoodsPropertyId(addCommentVO.getGoodsPropertyId());
        commentVo.setGoodsPropertyName(gdDTO.getProperty());
        commentVo.setGoodsId(gdDTO.getGoodsId());
        //插入用户信息
        MemberVO member = new MemberVO();
        member.setId(getUid());
        MemberRE memberInfoRE = memberService.queryOneMember(member);
        if (memberInfoRE == null) {
            return Result.getBusinessException("获取不到用户信息", null);
        }
        commentVo.setMemberId(getUid());
        commentVo.setMemberNickname(memberInfoRE.getNickname());
        commentVo.setMemberImageurl(memberInfoRE.getImageUrl());
        commentVo.setContent(addCommentVO.getContent());
        commentVo.setGoodsSroce(addCommentVO.getGoodsSroce());
        commentVo.setStatus(CommentEnum.COMMENT_NO.getKey());
        commentVo.setUrl(addCommentVO.getUrlList());
        //更改订单商品表的状态
        if (commentService.addComment(commentVo) <= 0) {
            return Result.getBusinessException("评论失败请重试", null);
        }
        return Result.getSuccessResult(null);
    }

    /**
     *hhj
     * 根据订单商品表Id获取商品评分
     * @param orderGoodsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getgoodsSroce.do_", method = RequestMethod.GET)
    public Result getGoodsSroce(Long orderGoodsId) {
        List<CommentCountRE> list = commentService.getCommentCountByOrderGoodsId(orderGoodsId);
        if (CollectionUtils.isEmpty(list)) {
            return Result.getBusinessException("获取信息失败", null);
        }
        return Result.getSuccessResult(list.get(0).getGoodsSroce());
    }
    @ResponseBody
    @AccessLogin(required = false)
    @RequestMapping(value = "/getCommentList.do_", method = RequestMethod.GET)
    public Result getCommentList(CommentListVO commentListVO){
        Map map = new HashMap();
        List<CommentRE> list = commentService.getCommentList(commentListVO);
        CommentAvgRE commentAvgRE = commentService.getCountBySroce(commentListVO);
        map.put("list",list);
        map.put("countObj",commentAvgRE);
        return Result.getSuccessResult(map);
    }

}
