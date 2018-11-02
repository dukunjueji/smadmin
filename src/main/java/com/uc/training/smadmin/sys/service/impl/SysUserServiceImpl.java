package com.uc.training.smadmin.sys.service.impl;

import com.uc.training.smadmin.sys.dao.SysUserDao;
import com.uc.training.smadmin.sys.model.SysUser;
import com.uc.training.smadmin.sys.service.SysUserService;
import com.uc.training.smadmin.sys.vo.UserListVO;
import com.uc.training.smadmin.sys.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统用户逻辑判断实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao userDao;

    @Override
    public SysUser getUserLogin(UserLoginVO userLoginVO) {
        return userDao.getUserLogin(userLoginVO);
    }

    @Override
    public SysUser getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public int updatePassword(SysUser user) {
        return userDao.updatePassword(user);
    }

    @Override
    public List<SysUser> getUserList(UserListVO userListVO) {
        return userDao.getUserList(userListVO);
    }

    @Override
    public Long queryUserCount(UserListVO userListVO) {
        return userDao.queryUserCount(userListVO);
    }

    @Override
    public Long addUser(SysUser user) {
        return userDao.addUser(user);
    }

    @Override
    public Integer deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public Integer updateUser(SysUser user) {
        return userDao.updateUser(user);
    }
}
