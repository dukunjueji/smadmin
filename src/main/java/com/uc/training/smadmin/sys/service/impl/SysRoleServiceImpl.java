package com.uc.training.smadmin.sys.service.impl;

import com.uc.training.smadmin.sys.dao.SysRoleDao;
import com.uc.training.smadmin.sys.model.SysRole;
import com.uc.training.smadmin.sys.service.SysRoleService;
import com.uc.training.smadmin.sys.vo.RoleListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统角色逻辑判断实现类
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao roleDao;

    @Override
    public List<String> getUserRoles(Long userId) {
        return roleDao.getUserRoles(userId);
    }

    @Override
    public List<SysRole> getRolePage(RoleListVO roleListVO) {
        return roleDao.getRolePage(roleListVO);
    }

    @Override
    public Long getCount(RoleListVO roleListVO) {
        return roleDao.getCount(roleListVO);
    }

    @Override
    public Integer updateRole(SysRole sysRole) {
        return roleDao.updateRole(sysRole);
    }

    @Override
    public Integer deleteById(Long id) {
        roleDao.deleteUserRoleByRid(id);
        return roleDao.deleteById(id);
    }

    @Override
    public Long addRole(SysRole sysRole) {
        return roleDao.addRole(sysRole);
    }

    @Override
    public Long addRoleAuth(Long rid, List<Long> mid, Long createEmp) {
        roleDao.deleteAuthByRid(rid);
        return roleDao.batchInsertAuth(rid, mid, createEmp);
    }

    @Override
    public List<Long> getRoleMenuIdByRid(Long rid) {
        return roleDao.queryMenuAuthByRid(rid);
    }

    @Override
    public List<SysRole> getRoleList() {
        return roleDao.getRoleList();
    }

    @Override
    public List<Long> getRoleListByUid(Long uid) {
        return roleDao.getRoleListByUid(uid);
    }

    @Override
    public Long addUserRole(Long uid, List<Long> rid, Long createEmp) {
        roleDao.deleteRoleByUid(uid);
        return roleDao.batchInsertRole(uid, rid, createEmp);
    }
}
