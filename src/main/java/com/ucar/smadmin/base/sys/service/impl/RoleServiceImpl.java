package com.ucar.smadmin.base.sys.service.impl;

import com.ucar.smapi.base.sys.dto.SysRoleDTO;
import com.ucar.smapi.base.sys.dto.SysUserRoleDTO;
import com.ucar.smapi.base.sys.re.SysRoleRE;
import com.ucar.smadmin.base.sys.service.RoleService;
import com.ucar.smadmin.base.sys.vo.RoleListVO;
import com.ucar.smadmin.base.sys.vo.RoleVO;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:42
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private BaseClient baseClient;
    @Override
    public List<String> getUserRoles(Long userId) {
        return baseClient.getUserRoles(userId).getRe();
    }

    @Override
    public List<SysRoleRE> getRolePage(RoleListVO roleListVO) {
        SysRoleDTO roleDTO = new SysRoleDTO();
        BeanUtils.copyProperties(roleListVO, roleDTO);
        return baseClient.getRolePage(roleDTO).getRe();
    }

    @Override
    public Long getCount(RoleListVO roleListVO) {
        SysRoleDTO roleDTO = new SysRoleDTO();
        BeanUtils.copyProperties(roleListVO, roleDTO);
        return baseClient.getSysRoleCount(roleDTO).getRe();
    }

    @Override
    public Integer updateRole(RoleVO role) {
        SysRoleDTO roleDTO = new SysRoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        return baseClient.updateRole(roleDTO).getRe();
    }

    @Override
    public Integer deleteById(Long id) {
        return baseClient.deleteSysRoleById(id).getRe();
    }

    @Override
    public Long addRole(RoleVO role, Long createEmp) {
        SysRoleDTO roleDTO = new SysRoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        roleDTO.setCreateEmp(createEmp);
        return baseClient.addRole(roleDTO).getRe();
    }

    @Override
    public Long addRoleAuth(Long rid, List<Long> ids) {
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        sysRoleDTO.setId(rid);
        sysRoleDTO.setMid(ids);
        return baseClient.batchInsertAuth(sysRoleDTO).getRe();
    }

    @Override
    public List<Long> getRoleMenuIdByRid(Long rid) {
        return baseClient.getRoleMenuIdByRid(rid).getRe();
    }

    @Override
    public List<SysRoleRE> getRoleList() {
        return baseClient.getRoleList().getRe();
    }

    @Override
    public List<Long> getRoleListByUid(Long uid) {
        return baseClient.getRoleListByUid(uid).getRe();
    }

    @Override
    public Long addUserRole(Long uid, List<Long> list, Long createEmp) {
        SysUserRoleDTO sysUserRoleDTO = new SysUserRoleDTO();
        sysUserRoleDTO.setUserId(uid);
        sysUserRoleDTO.setRoleId(list);
        sysUserRoleDTO.setCreateEmp(createEmp);
        return baseClient.addUserRole(sysUserRoleDTO).getRe();
    }

    @Override
    public Long queryCountByName(String name) {
        return baseClient.querySysRoleCount(name).getRe();
    }

    @Override
    public SysRoleRE getById(Long id) {
        return baseClient.getSysRoleById(id).getRe();
    }
}
