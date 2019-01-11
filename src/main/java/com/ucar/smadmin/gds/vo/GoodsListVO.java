package com.ucar.smadmin.gds.vo;

import com.ucar.smadmin.common.bean.PageQuery;

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

    /**
     * 商品编号
     */
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "GoodsListVO{" + "categoryId=" + categoryId + ", name='" + name + '\'' + ", code='" + code + '\'' + ", offset=" + offset + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize + '}';
    }
}
