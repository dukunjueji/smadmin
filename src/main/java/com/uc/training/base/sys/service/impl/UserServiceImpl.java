package com.uc.training.base.sys.service.impl;

import com.uc.training.base.sys.dto.SysUserDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.re.SysUserRE;
import com.uc.training.base.sys.service.UserService;
import com.uc.training.base.sys.vo.UserListVO;
import com.uc.training.base.sys.vo.UserLoginVO;
import com.uc.training.base.sys.vo.UserVO;
import com.uc.training.remote.client.BaseClient;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:42
 * @Description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public SysUserRE getUserLogin(UserLoginVO userLoginVO) {
        return BaseClient.getUserLogin(userLoginVO);
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
    public List<SysUserRE> getUserList(UserListVO userListVO) {
        return BaseClient.getUserList(userListVO);
    }

    @Override
    public Long queryUserCount(UserListVO userListVO) {
        return BaseClient.queryUserCount(userListVO);
    }

    @Override
    public Long addUser(UserVO user, Long uid, String pwd) {
        return BaseClient.addUser(user, uid, pwd);
    }

    @Override
    public Integer deleteById(Long id) {
        return BaseClient.deleteSysUserById(id);
    }

    @Override
    public Integer updateUser(UserVO user, Long uid) {
        return BaseClient.updateUser(user, uid);
    }

    @Override
    public List<SysMenuRE> getMenuListByUserId(Long uid) {
        return BaseClient.getMenuListByUserId(uid);
    }

    @Override
    public Long queryUserCountByName(String name) {
        return BaseClient.queryUserCountByName(name);
    }

}
