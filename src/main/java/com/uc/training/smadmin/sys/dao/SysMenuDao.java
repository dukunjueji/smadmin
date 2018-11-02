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

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    Long addMenu(SysMenu menu);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    Integer updateMenu(SysMenu menu);

    /**
     * 通过菜单ID删除菜单
     * @param id
     * @return
     */
    Integer deleteMenuById(Long id);

    /**
     * 通过ID获取菜单
     * @param id
     * @return
     */
    SysMenu getMenuById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer batchDelete(List<Long> ids);

    /**
     * 批量删除角色菜单中的权限
     * @param mid
     * @return
     */
    Integer deleteRoleMenuByMenuList(List<Long> mid);
}
