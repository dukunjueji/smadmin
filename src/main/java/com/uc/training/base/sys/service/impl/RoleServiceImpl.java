package com.uc.training.base.sys.service.impl;

import com.uc.training.base.sys.dto.SysRoleDTO;
import com.uc.training.base.sys.dto.SysRoleMenuDTO;
import com.uc.training.base.sys.dto.SysUserRoleDTO;
import com.uc.training.base.sys.re.SysRoleRE;
import com.uc.training.base.sys.service.RoleService;
import com.uc.training.remote.client.BaseClient;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:42
 * @Description:
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public List<String> getUserRoles(Long userId) {
        return BaseClient.getUserRoles(userId);
    }

    @Override
    public List<SysRoleRE> getRolePage(SysRoleDTO sysRoleDTO) {
        return BaseClient.getRolePage(sysRoleDTO);
    }

    @Override
    public Long getCount(SysRoleDTO sysRoleDTO) {
        return BaseClient.getSysRoleCount(sysRoleDTO);
    }

    @Override
    public Integer updateRole(SysRoleDTO roleDTO) {
        return BaseClient.updateRole(roleDTO);
    }

    @Override
    public Integer deleteById(Long id) {
        return BaseClient.deleteSysRoleById(id);
    }

    @Override
    public Long addRole(SysRoleDTO roleDTO) {
        return BaseClient.addRole(roleDTO);
    }

    @Override
    public Long addRoleAuth(SysRoleMenuDTO sysRoleMenuDTO) {
        return BaseClient.addRoleAuth(sysRoleMenuDTO);
    }

    @Override
    public List<Long> getRoleMenuIdByRid(Long rid) {
        return BaseClient.getRoleMenuIdByRid(rid);
    }

    @Override
    public List<SysRoleRE> getRoleList() {
        return BaseClient.getRoleList();
    }

    @Override
    public List<Long> getRoleListByUid(Long uid) {
        return BaseClient.getRoleListByUid(uid);
    }

    @Override
    public Long addUserRole(SysUserRoleDTO sysUserRoleDTO) {
        return BaseClient.addUserRole(sysUserRoleDTO);
    }

    @Override
    public Integer queryCountByName(String name) {
        return BaseClient.querySysRoleCount(name);
    }

    @Override
    public SysRoleRE getById(Long id) {
        return BaseClient.getSysRoleById(id);
    }
}
