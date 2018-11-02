package com.uc.training.common.enums;

/**
 * @Author: 余旭东
 * @Date: 2018/11/2 17:09
 * @Description:
 */
public enum MenuEnum {
    /**
     * 根菜单节点的父节点ID
     */
    ROOTPID(0),
    /**
     * 菜单类型节点
     */
    CATALOG(1),
    /**
     * 按钮类习性节点
     */
    BUTTON(2);


    /**
     * 类型编号
     */
    private int number;
    MenuEnum(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
