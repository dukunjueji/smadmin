package com.uc.training.smadmin.sys.service.impl;

import com.uc.training.smadmin.sys.dao.SysRoleDao;
import com.uc.training.smadmin.sys.service.SysRoleService;
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
}
