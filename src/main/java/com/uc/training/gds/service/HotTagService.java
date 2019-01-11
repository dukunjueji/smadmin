package com.uc.training.gds.service;

import com.uc.training.common.vo.PageVO;
import com.uc.training.gds.dto.AdminHotTagListDTO;
import com.uc.training.gds.dto.HotTagDTO;
import com.uc.training.gds.re.HotTagRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/24
 */
public interface HotTagService {
    /**
     * 获取热门标签
     * @return
     */
    List<HotTagRE> selectHotTag();


    /**
     * 后台获取商品标签
     * @param adminHotTagListDTO
     * @return
     */
    PageVO<HotTagRE> getAllHotTagList(AdminHotTagListDTO adminHotTagListDTO);

    /**
     * 后台更新商品标签
     * @param hotTag
     * @return
     */
    Integer updateHotTag(HotTagDTO hotTag);

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
    Long insertHotTag(HotTagDTO hotTag);
}
