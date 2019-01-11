package com.ucar.smadmin.remote.client;

import com.ucar.smapi.common.vo.PageVO;
import com.ucar.smapi.gds.dto.AdminHotTagListDTO;
import com.ucar.smapi.gds.dto.CategoryDTO;
import com.ucar.smapi.gds.dto.CommentDTO;
import com.ucar.smapi.gds.dto.CommentListDTO;
import com.ucar.smapi.gds.dto.CommentReplyDTO;
import com.ucar.smapi.gds.dto.CommentReplyModelDTO;
import com.ucar.smapi.gds.dto.GoodsAndPropertyDTO;
import com.ucar.smapi.gds.dto.GoodsDTO;
import com.ucar.smapi.gds.dto.GoodsListDTO;
import com.ucar.smapi.gds.dto.GoodsPicDTO;
import com.ucar.smapi.gds.dto.HotTagDTO;
import com.ucar.smapi.gds.dto.PropertyDTO;
import com.ucar.smapi.gds.re.AdminGoodsPicRE;
import com.ucar.smapi.gds.re.AdminGoodsRE;
import com.ucar.smapi.gds.re.CategoryRE;
import com.ucar.smapi.gds.re.CommentAvgRE;
import com.ucar.smapi.gds.re.CommentCountRE;
import com.ucar.smapi.gds.re.CommentDetailRE;
import com.ucar.smapi.gds.re.CommentPicRE;
import com.ucar.smapi.gds.re.CommentRE;
import com.ucar.smapi.gds.re.GoodsDetailRE;
import com.ucar.smapi.gds.re.GoodsRE;
import com.ucar.smapi.gds.re.GoodsStokeRE;
import com.ucar.smapi.gds.re.HotTagRE;
import com.ucar.smadmin.gds.vo.CommentListVO;
import com.ucar.smadmin.gds.vo.CommentReplyVO;
import com.ucar.smadmin.gds.vo.CommentVO;
import com.ucar.smadmin.gds.vo.GoodsStokeVO;
import com.ucar.smadmin.remote.remoteclient.GoodsRemote;
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
public class GdsClient {
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
            return  goodsRemote.getCommentCountByOrderGoodsId(orderGoodsId).getRe();
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
            return  goodsRemote.getAdminReplyContent(commentId).getRe();
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
            return  goodsRemote.getCommentDetailByCommentId(commentReplyDTO).getRe();
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
            return  goodsRemote.insertCommentReply(commentReplyModelDTO).getRe();
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
            return  goodsRemote.editCommentReply(commentReplyModelDTO).getRe();
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
            return goodsRemote.deleteCommentReplyById(commentReplyDTO).getRe();
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
            return  goodsRemote.getHotRecommend().getRe();
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
            return  goodsRemote.getGoodsPageByCategory(goodsListDTO).getRe();
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
            return  goodsRemote.getGoodsDetailByPropertyId(propertyId).getRe();
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
            return  goodsRemote.getGoodsDetailByGoodsId(goodsId).getRe();
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
            return  goodsRemote.getGoodsDetailByPropertyIds(propertyIds).getRe();
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
            return  goodsRemote.searchByGoodsName(goodsListDTO).getRe();
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
            return  goodsRemote.selectHotTag().getRe();
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
            return  goodsRemote.selectGoodsStatus(goodsAndPropertyDTO).getRe();
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
            return  goodsRemote.updateAndDeductStoke(goodsAndPropertyDTO).getRe();
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
            return  goodsRemote.getAdminGoodsList(goodsListDTO).getRe();
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
            return  goodsRemote.adminUpdateGoods(adminUpdateGoodsDTO).getRe();
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
            return  goodsRemote.adminInsertGoods(goods).getRe();
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
            return  goodsRemote.logicDeleteGoods(id).getRe();
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
            return  goodsRemote.updateSales(goodsAndPropertyDTO).getRe();
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
            return  goodsRemote.getAdminGoodsListCount(GoodsListDTO).getRe();
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
            return  goodsRemote.pullOnGoods(goodsDTO).getRe();
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
            return  goodsRemote.pullOffGoods(goodsDTO).getRe();
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
            return  goodsRemote.getAllHotTagList(adminHotTagListDTO).getRe();
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
            return  goodsRemote.updateHotTag(hotTagDTO).getRe();
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
            return  goodsRemote.deleteHotTagById(id).getRe();
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
            return  goodsRemote.insertHotTag(hotTagDTO).getRe();
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
            return  goodsRemote.insertGoodsPic(goodsPic).getRe();
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
            return  goodsRemote.updateGoodsPic(goodsPic).getRe();
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
            return  goodsRemote.getGoodsPicListByPropertyId(propertyId).getRe();
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
            return  goodsRemote.deleteGoodsPicById(id).getRe();
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
            return  goodsRemote.deleteGoodsPicByPropertyId(propertyId).getRe();
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
            return  goodsRemote.getPropertyIdCountById(id).getRe();
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
            return  goodsRemote.getPropertyIdById(id).getRe();
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
            return  goodsRemote.insertProperty(property).getRe();
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
            return  goodsRemote.updateProperty(property).getRe();
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
            return  goodsRemote.getPropertyIdListByGoodsId(goodsId).getRe();
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
            return  goodsRemote.getPropertyListByGoodsId(goodsId).getRe();
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
            return  goodsRemote.queryCategory(id).getRe();
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
            return  goodsRemote.getCountByGoodsPic(goodsPic).getRe();
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
            return  goodsRemote.getGoodsById(id).getRe();
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
            return  goodsRemote.getPropertyById(id).getRe();
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
            return  goodsRemote.getCategoryList().getRe();
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
            return  goodsRemote.addCategory(category).getRe();
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
            return  goodsRemote.logicDeleteCategory(id).getRe();
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
            return  goodsRemote.updateCategory(category).getRe();
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
            return  goodsRemote.getCountByNameAndParentId(category).getRe();
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
            return  goodsRemote.getCountByProperty(property).getRe();
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
}