package com.uc.training.smadmin.gds.vo;

import com.uc.training.common.bean.PageQuery;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月22日 09:02
 */
public class GoodsListVO extends PageQuery {
    /**
     * 商品类别
     */
    private Long categoryId;
    /**
     * 商品名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
