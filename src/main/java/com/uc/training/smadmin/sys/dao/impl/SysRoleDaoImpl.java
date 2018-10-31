package com.uc.training.smadmin.sys.dao.impl;

import com.uc.training.smadmin.sys.dao.SysRoleDao;
import com.uc.training.smadmin.sys.model.SysRole;
import com.uc.training.smadmin.sys.vo.RoleListVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.apache.poi.ss.formula.functions.Na;
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
    private static final String NAMESPACE = "com.uc.training.smadmin.sys.dao.SysRoleDao.";

    @Override
    public List<String> getUserRoles(Long userId) {
        return (List<String>) this.queryForList(NAMESPACE + "getUserRoles", userId);
    }

    @Override
    public List<SysRole> getRolePage(RoleListVO roleListVO) {
        return this.queryForList(NAMESPACE + "getRolePage", roleListVO);
    }

    @Override
    public Long getCount(RoleListVO roleListVO) {
        return (Long) this.queryForObject(NAMESPACE + "queryCount", roleListVO);
    }

    @Override
    public Integer updateRole(SysRole sysRole) {
        return this.update(NAMESPACE + "updateRole", sysRole);
    }

    @Override
    public Integer deleteById(Long id) {
        return this.deleteObject(NAMESPACE + "deleteById", id);
    }

    @Override
    public Long addRole(SysRole sysRole) {
        return (Long) this.insert(NAMESPACE + "addRole", sysRole);
    }
}
