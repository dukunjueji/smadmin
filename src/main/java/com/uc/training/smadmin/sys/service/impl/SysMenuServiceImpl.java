package com.uc.training.smadmin.sys.service.impl;

import com.uc.training.smadmin.sys.dao.SysMenuDao;
import com.uc.training.smadmin.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统菜单逻辑判断实现类
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao menuDao;
    @Override
    public List<String> getUserPerms(Long userId) {
        return menuDao.getUserPerms(userId);
    }
}
