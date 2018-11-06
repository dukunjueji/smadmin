package com.uc.training.smadmin.gds.dao;

import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.AdminHotTagListRE;
import com.uc.training.smadmin.gds.re.HotTagRE;
import com.uc.training.smadmin.gds.vo.AdminHotTagListVO;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/24
 */
public interface HotTagDao {
    /**
     * 获取热门标签
     * @return
     */
    List<HotTagRE> selectHotTag();

    /**
     * 后台获取标签列表数量
     * @param adminHotTagListVO
     * @return
     */
    Long getAdminHotTagCount(AdminHotTagListVO adminHotTagListVO);

    /**
     * 后台获取商品标签
     * @param adminHotTagListVO
     * @return
     */
    List<AdminHotTagListRE> getAllHotTagList(AdminHotTagListVO adminHotTagListVO);

    /**
     * 后台更新商品标签
     * @param hotTag
     * @return
     */
    Integer updateHotTag(HotTag hotTag);

    /**
     * 后台根据主键id删除商品标签
     * @param id
     * @return
     */
    Integer deleteHotTagById(Long id);

    /**
     * 后台新增商品标签
     * @param hotTag
     * @return
     */
    Long insertHotTag(HotTag hotTag);
}
