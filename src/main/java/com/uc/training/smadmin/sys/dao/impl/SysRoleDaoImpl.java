package com.uc.training.smadmin.sys.dao.impl;

import com.uc.training.smadmin.sys.dao.SysRoleDao;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统角色持久化实现类
 */
@Repository
public class SysRoleDaoImpl extends CarIsIbatisDaoImpl implements SysRoleDao {
    @Override
    public List<String> getUserRoles(Long userId) {
        return (List<String>) this.queryForList("com.uc.training.smadmin.sys.dao.SysRoleDao.getUserRoles", userId);
    }
}
