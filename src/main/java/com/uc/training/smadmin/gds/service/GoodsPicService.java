package com.uc.training.smadmin.gds.service;

import com.uc.training.smadmin.gds.model.GoodsPic;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/30
 */
public interface GoodsPicService {
    /**
     * 新增商品图片
     * @param goodsPic
     * @return
     */
    Long insertGoodsPic(GoodsPic goodsPic);

    /**
     * 更新商品图片
     * @param goodsPic
     * @return
     */
    Integer updateGoodsPic(GoodsPic goodsPic);

}
