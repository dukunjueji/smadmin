package com.uc.training.smadmin.bd.vo;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/7
 */
public class AddressUpdateVO extends AddressInsertVO{
    /**
     * 主键id
     */
    private Long id;

    @Override
    public String toString() {
        return "AddressUpdateVO{" +
                "id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
