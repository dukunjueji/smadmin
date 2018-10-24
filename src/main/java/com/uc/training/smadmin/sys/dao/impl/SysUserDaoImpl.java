package com.uc.training.smadmin.sys.dao.impl;

import com.uc.training.smadmin.sys.dao.SysUserDao;
import com.uc.training.smadmin.sys.model.SysUser;
import com.uc.training.smadmin.sys.service.SysUserService;
import com.uc.training.smadmin.sys.vo.UserLoginVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统用户持久化实现类
 */
@Repository
public class SysUserDaoImpl extends CarIsIbatisDaoImpl implements SysUserDao {
    @Override
    public SysUser getUserLogin(UserLoginVO userLoginVO) {
        return (SysUser) this.queryForObject("com.uc.training.smadmin.sys.dao.SysUserDao.getUserLogin", userLoginVO);
    }

    @Override
    public SysUser getById(Long id) {
        return (SysUser) this.queryForObject("com.uc.training.smadmin.sys.dao.SysUserDao.getById", id);
    }

    @Override
    public int updatePassword(SysUser user) {
        return this.update("com.uc.training.smadmin.sys.dao.SysUserDao.updatePassword", user);
    }
}
