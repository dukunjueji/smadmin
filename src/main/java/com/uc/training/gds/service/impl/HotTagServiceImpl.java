package com.uc.training.gds.service.impl;

import com.smgoods.common.vo.PageVO;
import com.uc.training.gds.dto.AdminHotTagListDTO;
import com.uc.training.gds.dto.HotTagDTO;
import com.uc.training.gds.re.HotTagRE;
import com.uc.training.gds.service.HotTagService;
import com.uc.training.remote.client.GdsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/24
 */
@Service
public class HotTagServiceImpl implements HotTagService {
    @Autowired
    GdsClient gdsClient;

    /**
     * 获取热门标签
     *
     * @return
     */
    @Override
    public List<HotTagRE> selectHotTag() {
        return gdsClient.selectHotTag();
    }


    /**
     * 后台获取商品标签
     *
     * @param adminHotTagListDTO
     * @return
     */
    @Override
    public PageVO<HotTagRE> getAllHotTagList(AdminHotTagListDTO adminHotTagListDTO) {
        return gdsClient.getAllHotTagList(adminHotTagListDTO);
    }

    /**
     * 后台更新商品标签
     *
     * @param hotTag
     * @return
     */
    @Override
    public Integer updateHotTag(HotTagDTO hotTag) {
        return gdsClient.updateHotTag(hotTag);
    }

    /**
     * 后台根据主键id删除商品标签
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteHotTagById(Long id) {
        return gdsClient.deleteHotTagById(id);
    }

    /**
     * 后台新增商品标签
     *
     * @param hotTag
     * @return
     */
    @Override
    public Long insertHotTag(HotTagDTO hotTag) {
        return gdsClient.insertHotTag(hotTag);
    }
}
