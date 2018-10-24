package com.uc.training.smadmin.sys.dao;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统角色接口
 */
public interface SysRoleDao {
    /**
     * 根据用户id获取用户的角色
     *
     * @param userId
     * @return java.lang.String[]
     * @version 1.0 2018/10/18 20:41 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<String> getUserRoles(Long userId);
}
