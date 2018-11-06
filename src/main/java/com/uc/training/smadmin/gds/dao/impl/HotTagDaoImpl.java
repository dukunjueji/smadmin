package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.HotTagDao;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.AdminHotTagListRE;
import com.uc.training.smadmin.gds.re.HotTagRE;
import com.uc.training.smadmin.gds.vo.AdminHotTagListVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/24
 */
@Repository
public class HotTagDaoImpl extends CarIsIbatisDaoImpl implements HotTagDao{

    /**
     * 获取热门标签
     *
     * @return
     */
    @Override
    public List<HotTagRE> selectHotTag() {
        return this.queryForList("com.uc.training.smadmin.gds.dao.HotTagDao.selectHotTag");
    }

    /**
     * 后台获取标签列表数量
     *
     * @param adminHotTagListVO
     * @return
     */
    @Override
    public Long getAdminHotTagCount(AdminHotTagListVO adminHotTagListVO) {
        return (Long) this.queryForObject("com.uc.training.smadmin.gds.dao.HotTagDao.getAdminHotTagCount", adminHotTagListVO);
    }

    /**
     * 后台获取商品标签
     *
     * @param adminHotTagListVO
     * @return
     */
    @Override
    public List<AdminHotTagListRE> getAllHotTagList(AdminHotTagListVO adminHotTagListVO) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.HotTagDao.getAllHotTagList", adminHotTagListVO);
    }

    /**
     * 后台更新商品标签
     *
     * @param hotTag
     * @return
     */
    @Override
    public Integer updateHotTag(HotTag hotTag) {
        return this.update("com.uc.training.smadmin.gds.dao.HotTagDao.updateHotTag", hotTag);
    }

    /**
     * 后台根据主键id删除商品标签
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteHotTagById(Long id) {
        return this.deleteObject("com.uc.training.smadmin.gds.dao.HotTagDao.deleteHotTagById", id);
    }

    /**
     * 后台新增商品标签
     *
     * @param hotTag
     * @return
     */
    @Override
    public Long insertHotTag(HotTag hotTag) {
        return (Long) this.insert("com.uc.training.smadmin.gds.dao.HotTagDao.insertHotTag", hotTag);
    }
}
