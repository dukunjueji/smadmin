package com.uc.training.smadmin.gds.dao;

import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.HotTagRE;

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
}
