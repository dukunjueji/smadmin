package com.uc.training.base.sys.service.impl;

import com.uc.training.base.sys.re.SysRoleRE;
import com.uc.training.base.sys.service.RoleService;
import com.uc.training.base.sys.vo.RoleListVO;
import com.uc.training.base.sys.vo.RoleVO;
import com.uc.training.remote.client.BaseClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:42
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<String> getUserRoles(Long userId) {
        return BaseClient.getUserRoles(userId);
    }

    @Override
    public List<SysRoleRE> getRolePage(RoleListVO roleListVO) {
        return BaseClient.getRolePage(roleListVO);
    }

    @Override
    public Long getCount(RoleListVO roleListVO) {
        return BaseClient.getSysRoleCount(roleListVO);
    }

    @Override
    public Integer updateRole(RoleVO role) {
        return BaseClient.updateRole(role);
    }

    @Override
    public Integer deleteById(Long id) {
        return BaseClient.deleteSysRoleById(id);
    }

    @Override
    public Long addRole(RoleVO role, Long createEmp) {
        return BaseClient.addRole(role, createEmp);
    }

    @Override
    public Long addRoleAuth(Long rid, List<Long> ids) {
        return BaseClient.addRoleAuth(rid, ids);
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
    public Long addUserRole(Long uid, List<Long> list, Long createEmp) {
        return BaseClient.addUserRole(uid, list, createEmp);
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
