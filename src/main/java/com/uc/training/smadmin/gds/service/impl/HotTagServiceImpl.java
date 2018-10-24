package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.smadmin.gds.dao.HotTagDao;
import com.uc.training.smadmin.gds.re.HotTagRE;
import com.uc.training.smadmin.gds.service.HotTagService;
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
public class HotTagServiceImpl implements HotTagService{
    @Autowired
    private HotTagDao hotTagDao;


    /**
     * 获取热门标签
     *
     * @return
     */
    @Override
    public List<HotTagRE> selectHotTag() {
        return hotTagDao.selectHotTag();
    }
}
