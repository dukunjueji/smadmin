package com.uc.training.smadmin.sys.service;

import com.uc.training.smadmin.sys.model.SysMenu;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统菜单功能逻辑判断接口
 */
public interface SysMenuService {
    /**
     * 根据用户id获取用户权限列表
     *
     * @param userId
     * @return java.lang.String[]
     * @version 1.0 2018/10/18 20:29 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<String> getUserPerms(Long userId);

    /**
     * 获取菜单列表
     * @return
     */
    List<SysMenu> getMenuList();

    /**
     * 通过ID获取菜单
     * @param id
     * @return
     */
    SysMenu getById(Long id);

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    Long addMenu(SysMenu menu);

    /**
     * 通过ID删除菜单
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    Integer updateMenu(SysMenu menu);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer batchDelete(List<Long> ids);

    /**
     * 通过菜单名查找用户数量
     * @param name
     * @return
     */
    Integer queryCountByName(String name);

    /**
     * 获取ID列表
     * @return
     */
    List<Long> getIdList();
}
