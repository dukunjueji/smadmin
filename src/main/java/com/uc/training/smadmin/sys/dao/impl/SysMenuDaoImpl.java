package com.uc.training.smadmin.sys.dao.impl;

import com.uc.training.smadmin.sys.dao.SysMenuDao;
import com.uc.training.smadmin.sys.model.SysMenu;
import com.uc.training.smadmin.sys.model.SysUser;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统菜单持久化实现类
 */
@Repository
public class SysMenuDaoImpl extends CarIsIbatisDaoImpl implements SysMenuDao {

    private static final String NAMESPACE = "com.uc.training.smadmin.sys.dao.SysMenuDao.";
    @Override
    public List<String> getUserPerms(Long userId) {
        return (List<String>) this.queryForList(NAMESPACE +"getUserPerms", userId);
    }

    @Override
    public List<SysMenu> getMenuList() {
        return this.queryForList(NAMESPACE + "getMenuList");
    }

    @Override
    public Long addMenu(SysMenu menu) {
        return (Long) this.insert(NAMESPACE + "addMenu", menu);
    }

    @Override
    public Integer updateMenu(SysMenu menu) {
        return this.update(NAMESPACE + "updateById", menu);
    }

    @Override
    public Integer deleteMenuById(Long id) {
        return this.deleteObject(NAMESPACE + "deleteById", id);
    }

    @Override
    public SysMenu getMenuById(Long id) {
        return (SysMenu) this.queryForObject(NAMESPACE + "getById", id);
    }

    @Override
    public Integer batchDelete(List<Long> ids) {
        return this.deleteObject(NAMESPACE + "batchDelete", ids);
    }


}
