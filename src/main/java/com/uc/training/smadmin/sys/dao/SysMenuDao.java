package com.uc.training.smadmin.sys.dao;

import com.uc.training.smadmin.sys.model.SysMenu;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统菜单接口
 */
public interface SysMenuDao {
    /**
     * 根据用户id获取用户权限
     *
     * @param userId 用户id
     * @return java.lang.String[]
     * @version 1.0 2018/10/18 20:30 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<String> getUserPerms(Long userId);

    /**
     * 获取菜单列表
     * @return
     */
    List<SysMenu> getMenuList();
}
