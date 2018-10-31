package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.HotTagDao;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.HotTagRE;
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
}
