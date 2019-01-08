package com.uc.training.remote.client;

import com.uc.training.common.vo.PageVO;
import com.uc.training.gds.dto.AdminHotTagListDTO;
import com.uc.training.gds.dto.CategoryDTO;
import com.uc.training.gds.dto.CommentDTO;
import com.uc.training.gds.dto.CommentListDTO;
import com.uc.training.gds.dto.CommentReplyDTO;
import com.uc.training.gds.dto.CommentReplyModelDTO;
import com.uc.training.gds.dto.GoodsAndPropertyDTO;
import com.uc.training.gds.dto.GoodsDTO;
import com.uc.training.gds.dto.GoodsListDTO;
import com.uc.training.gds.dto.GoodsPicDTO;
import com.uc.training.gds.dto.HotTagDTO;
import com.uc.training.gds.dto.PropertyDTO;
import com.uc.training.gds.re.AdminGoodsPicRE;
import com.uc.training.gds.re.AdminGoodsRE;
import com.uc.training.gds.re.CategoryRE;
import com.uc.training.gds.re.CommentAvgRE;
import com.uc.training.gds.re.CommentCountRE;
import com.uc.training.gds.re.CommentDetailRE;
import com.uc.training.gds.re.CommentPicRE;
import com.uc.training.gds.re.CommentRE;
import com.uc.training.gds.re.GoodsDetailRE;
import com.uc.training.gds.re.GoodsRE;
import com.uc.training.gds.re.GoodsStokeRE;
import com.uc.training.gds.re.HotTagRE;
import com.uc.training.gds.vo.CommentListVO;
import com.uc.training.gds.vo.CommentReplyVO;
import com.uc.training.gds.vo.CommentVO;
import com.uc.training.gds.vo.GoodsStokeVO;
import com.uc.training.remote.remoteclient.GoodsRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
@Component
public final class GdsClient {
    @Autowired
    GoodsRemote goodsRemote;

    private final Logger logger = LoggerFactory.getLogger(GdsClient.class.getName());

    /**
     * 添加评论
     * hhj
     *
     * @param commentVO
     * @return
     */
    public Integer addComment(CommentVO commentVO) {
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO, commentDTO);
        try {
            return goodsRemote.addComment(commentDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据订单商品表id获取评论List
     *
     * @param orderGoodsId
     * @return
     */
    public List<CommentCountRE> getCommentCountByOrderGoodsId(Long orderGoodsId) {
        try {
            return (List<CommentCountRE>) goodsRemote.getCommentCountByOrderGoodsId(orderGoodsId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 获取评论列表
     *
     * @param commentListVO
     * @return
     */
    public List<CommentRE> getCommentList(CommentListVO commentListVO) {
        CommentListDTO commentListDTO = new CommentListDTO();
        BeanUtils.copyProperties(commentListVO, commentListDTO);
        try {
            return goodsRemote.getCommentList(commentListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评分上下限获取计数
     *
     * @param commentListVO
     * @return
     */
    public CommentAvgRE getCountBySroce(CommentListVO commentListVO) {
        CommentListDTO commentListDTO = new CommentListDTO();
        BeanUtils.copyProperties(commentListVO, commentListDTO);
        try {
            return goodsRemote.getCountBySroce(commentListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 获取总评价数
     *
     * @param commentListVO
     * @return
     */
    public CommentAvgRE getCommentCountAndAvg(CommentListVO commentListVO) {
        CommentListDTO commentListDTO = new CommentListDTO();
        BeanUtils.copyProperties(commentListVO, commentListDTO);
        try {
            return goodsRemote.getCommentCountAndAvg(commentListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据ID修改评论
     *
     * @param commentVO
     * @return
     */
    public Integer editCommentById(CommentVO commentVO) {
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO, commentDTO);
        try {
            return goodsRemote.editCommentById(commentDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论id获取评论图片
     *
     * @param commentId
     * @return
     */
    public List<CommentPicRE> getCommentPicByCommentId(Long commentId) {
        try {
            return goodsRemote.getCommentPicByCommentId(commentId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论Id获取评论的后台回复
     *
     * @param commentId
     * @return
     */
    public String getAdminReplyContent(Long commentId) {
        try {
            return (String) goodsRemote.getAdminReplyContent(commentId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论id获取评论详情
     *
     * @param commentReplyVO
     * @return
     */
    public CommentDetailRE getCommentDetailByCommentId(CommentReplyVO commentReplyVO) {
        CommentReplyDTO commentReplyDTO = new CommentReplyDTO();
        BeanUtils.copyProperties(commentReplyVO, commentReplyDTO);
        try {
            return (CommentDetailRE) goodsRemote.getCommentDetailByCommentId(commentReplyDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 新增商品详情回复
     *
     * @param commentReplyVO
     * @return
     */
    public Long insertCommentReply(CommentReplyVO commentReplyVO) {
        CommentReplyModelDTO commentReplyModelDTO = new CommentReplyModelDTO();
        BeanUtils.copyProperties(commentReplyVO, commentReplyModelDTO);
        try {
            return (Long) goodsRemote.insertCommentReply(commentReplyModelDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论ID修改商家评论信息
     *
     * @param commentReplyVO
     * @return
     */
    public Integer editCommentReply(CommentReplyVO commentReplyVO) {
        CommentReplyModelDTO commentReplyModelDTO = new CommentReplyModelDTO();
        BeanUtils.copyProperties(commentReplyVO, commentReplyModelDTO);
        try {
            return (Integer) goodsRemote.editCommentReply(commentReplyModelDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 删除商品详情回复
     *
     * @param commentReplyVO
     * @return
     */
    public Integer deleteCommentReplyById(CommentReplyVO commentReplyVO) {
        CommentReplyDTO commentReplyDTO = new CommentReplyDTO();
        BeanUtils.copyProperties(commentReplyVO, commentReplyDTO);
        try {
            return (Integer) goodsRemote.deleteCommentReplyById(commentReplyDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 获取商品详情
     */
    public List<GoodsRE> getHotRecommend() {
        try {
            return (List<GoodsRE>) goodsRemote.getHotRecommend();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过分类来获取商品
     */
    public PageVO<GoodsRE> getGoodsPageByCategory(GoodsListDTO goodsListDTO) {
        try {
            return (PageVO<GoodsRE>) goodsRemote.getGoodsPageByCategory(goodsListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过单个属性id商品详情
     */
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId) {
        try {
            return (GoodsDetailRE) goodsRemote.getGoodsDetailByPropertyId(propertyId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品id获取所有规格的商品详情
     */
    public List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId) {
        try {
            return (List<GoodsDetailRE>) goodsRemote.getGoodsDetailByGoodsId(goodsId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过多个属性id获取商品详情
     */
    public List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds) {
        try {
            return (List<GoodsDetailRE>) goodsRemote.getGoodsDetailByPropertyIds(propertyIds).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 模糊查询商品列表
     */
    public PageVO<GoodsRE> searchByGoodsName(GoodsListDTO goodsListDTO) {
        try {
            return (PageVO<GoodsRE>) goodsRemote.searchByGoodsName(goodsListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取热门标签
     */
    public List<HotTagRE> selectHotTag() {
        try {
            return (List<HotTagRE>) goodsRemote.selectHotTag().getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 获取商品上架删除库存信息
     */
    public GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsStokeVO) {
        GoodsAndPropertyDTO goodsAndPropertyDTO = new GoodsAndPropertyDTO();
        BeanUtils.copyProperties(goodsStokeVO, goodsAndPropertyDTO);
        try {
            return (GoodsStokeRE) goodsRemote.selectGoodsStatus(goodsAndPropertyDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 更新减库存
     */
    public Integer updateAndDeductStoke(GoodsStokeVO goodsStokeVO) {
        GoodsAndPropertyDTO goodsAndPropertyDTO = new GoodsAndPropertyDTO();
        BeanUtils.copyProperties(goodsStokeVO, goodsAndPropertyDTO);
        try {
            return (Integer) goodsRemote.updateAndDeductStoke(goodsAndPropertyDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 获取商品详情
     */
    public List<AdminGoodsRE> getAdminGoodsList(GoodsListDTO goodsListDTO) {
        try {
            return (List<AdminGoodsRE>) goodsRemote.getAdminGoodsList(goodsListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public Integer adminUpdateGoods(GoodsDTO adminUpdateGoodsDTO) {
        try {
            return (Integer) goodsRemote.adminUpdateGoods(adminUpdateGoodsDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public Long adminInsertGoods(GoodsDTO goods) {
        try {
            return (Long) goodsRemote.adminInsertGoods(goods).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public Integer logicDeleteGoods(Long id) {
        try {
            return (Integer) goodsRemote.logicDeleteGoods(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public Integer updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO) {
        try {
            return (Integer) goodsRemote.updateSales(goodsAndPropertyDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public Long getAdminGoodsListCount(GoodsListDTO GoodsListDTO) {
        try {
            return (Long) goodsRemote.getAdminGoodsListCount(GoodsListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public Integer pullOnGoods(GoodsDTO goodsDTO) {
        try {
            return (Integer) goodsRemote.pullOnGoods(goodsDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public Integer pullOffGoods(GoodsDTO goodsDTO) {
        try {
            return (Integer) goodsRemote.pullOffGoods(goodsDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 后台获取商品标签
     */
    public PageVO<HotTagRE> getAllHotTagList(AdminHotTagListDTO adminHotTagListDTO) {
        try {
            return (PageVO<HotTagRE>) goodsRemote.getAllHotTagList(adminHotTagListDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台更新商品标签
     */
    public Integer updateHotTag(HotTagDTO hotTagDTO) {
        try {
            return (Integer) goodsRemote.updateHotTag(hotTagDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台根据主键id删除商品标签
     */
    public Integer deleteHotTagById(Long id) {
        try {
            return (Integer) goodsRemote.deleteHotTagById(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台新增商品标签
     */
    public Long insertHotTag(HotTagDTO hotTagDTO) {
        try {
            return (Long) goodsRemote.insertHotTag(hotTagDTO).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 新增商品图片
     */
    public Long insertGoodsPic(GoodsPicDTO goodsPic) {
        try {
            return (Long) goodsRemote.insertGoodsPic(goodsPic).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 更新商品图片
     */
    public Integer updateGoodsPic(GoodsPicDTO goodsPic) {
        try {
            return (Integer) goodsRemote.updateGoodsPic(goodsPic).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据商品属性id获取图片信息
     */
    public List<AdminGoodsPicRE> getGoodsPicListByPropertyId(Long propertyId) {
        try {
            return (List<AdminGoodsPicRE>) goodsRemote.getGoodsPicListByPropertyId(propertyId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过主键id删除商品属性图片
     */
    public Integer deleteGoodsPicById(Long id) {
        try {
            return (Integer) goodsRemote.deleteGoodsPicById(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品属性id删除图片
     */
    public Integer deleteGoodsPicByPropertyId(Long propertyId) {
        try {
            return (Integer) goodsRemote.deleteGoodsPicByPropertyId(propertyId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     */
    public Integer getPropertyIdCountById(Long id) {
        try {
            return (Integer) goodsRemote.getPropertyIdCountById(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过主键id获取商品属性id
     */
    public Long getPropertyIdById(Long id) {
        try {
            return (Long) goodsRemote.getPropertyIdById(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 新增商品属性
     */
    public Long insertProperty(PropertyDTO property) {
        try {
            return (Long) goodsRemote.insertProperty(property).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 更新商品
     */
    public Integer updateProperty(PropertyDTO property) {
        try {
            return (Integer) goodsRemote.updateProperty(property).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品id获取类型id
     */
    public List<Long> getPropertyIdListByGoodsId(Long goodsId) {
        try {
            return (List<Long>) goodsRemote.getPropertyIdListByGoodsId(goodsId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品id获取商品属性
     */
    public List<GoodsDetailRE> getPropertyListByGoodsId(Long goodsId) {
        try {
            return (List<GoodsDetailRE>) goodsRemote.getPropertyListByGoodsId(goodsId).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据id获取分类
     */
    public CategoryRE queryCategory(Long id) {
        try {
            return (CategoryRE) goodsRemote.queryCategory(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品属性id查找商品图片的数量
     */
    public Integer getCountByGoodsPic(GoodsPicDTO goodsPic) {
        try {
            return (Integer) goodsRemote.getCountByGoodsPic(goodsPic).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据id获取商品信息
     */
    public GoodsRE getGoodsById(Long id) {
        try {
            return (GoodsRE) goodsRemote.getGoodsById(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过id获取属性
     */
    public GoodsDetailRE getPropertyById(Long id) {
        try {
            return (GoodsDetailRE) goodsRemote.getPropertyById(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品分类
     */
    public List<CategoryRE> getCategoryList() {
        try {
            return (List<CategoryRE>) goodsRemote.getCategoryList().getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 增加分类
     */
    public Long addCategory(CategoryDTO category) {
        try {
            return (Long) goodsRemote.addCategory(category).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 删除分类
     */
    public Integer logicDeleteCategory(Long id) {
        try {
            return (Integer) goodsRemote.logicDeleteCategory(id).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 编辑分类
     */
    public Integer updateCategory(CategoryDTO category) {
        try {
            return (Integer) goodsRemote.updateCategory(category).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据name和parentId查找数量
     */
    public Integer getCountByNameAndParentId(CategoryDTO category) {
        try {
            return (Integer) goodsRemote.getCountByNameAndParentId(category).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品该名称规格的数量
     */
    public Integer getCountByProperty(PropertyDTO property) {
        try {
            return (Integer) goodsRemote.getCountByProperty(property).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
}