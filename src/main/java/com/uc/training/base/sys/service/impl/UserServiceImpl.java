package com.uc.training.base.sys.service.impl;

import com.uc.training.base.sys.dto.SysUserDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.re.SysUserRE;
import com.uc.training.base.sys.service.UserService;
import com.uc.training.remote.client.BaseClient;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:42
 * @Description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public SysUserRE getUserLogin(SysUserDTO sysUserDTO) {
        return BaseClient.getUserLogin(sysUserDTO);
    }

    @Override
    public SysUserRE getById(Long id) {
        return BaseClient.getSysUserById(id);
    }

    @Override
    public int updatePassword(SysUserDTO user) {
        return BaseClient.updatePassword(user);
    }

    @Override
    public List<SysUserRE> getUserList(SysUserDTO sysUserDTO) {
        return BaseClient.getUserList(sysUserDTO);
    }

    @Override
    public Long queryUserCount(SysUserDTO sysUserDTO) {
        return BaseClient.queryUserCount(sysUserDTO);
    }

    @Override
    public Long addUser(SysUserDTO user) {
        return BaseClient.addUser(user);
    }

    @Override
    public Integer deleteById(Long id) {
        return BaseClient.deleteSysUserById(id);
    }

    @Override
    public Integer updateUser(SysUserDTO user) {
        return BaseClient.updateUser(user);
    }

    @Override
    public List<SysMenuRE> getMenuListByUserId(Long uid) {
        return BaseClient.getMenuListByUserId(uid);
    }

}
