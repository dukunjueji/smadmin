package com.uc.training.smadmin.sys.service.impl;

import com.uc.training.smadmin.sys.dao.SysMenuDao;
import com.uc.training.smadmin.sys.model.SysMenu;
import com.uc.training.smadmin.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统菜单逻辑判断实现类
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao menuDao;
    @Override
    public List<String> getUserPerms(Long userId) {
        return menuDao.getUserPerms(userId);
    }

    @Override
    public List<SysMenu> getMenuList() {
        return menuDao.getMenuList();
    }

    @Override
    public SysMenu getById(Long id) {
        return menuDao.getMenuById(id);
    }

    @Override
    public Long addMenu(SysMenu menu) {
        return menuDao.addMenu(menu);
    }

    @Override
    public Integer deleteById(Long id) {
        return menuDao.deleteMenuById(id);
    }

    @Override
    public Integer updateMenu(SysMenu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public Integer batchDelete(List<Long> ids) {
        int count = menuDao.batchDelete(ids);
        menuDao.deleteRoleMenuByMenuList(ids);
        return count;
    }

    @Override
    public Integer queryCountByName(String name) {
        return menuDao.queryCountByName(name);
    }

    @Override
    public List<Long> getIdList() {
        return menuDao.getIdList();
    }


}
