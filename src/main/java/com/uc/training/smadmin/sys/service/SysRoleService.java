package com.uc.training.smadmin.sys.service;

import com.uc.training.smadmin.sys.model.SysRole;
import com.uc.training.smadmin.sys.vo.RoleListVO;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统角色功能逻辑判断接口
 */
public interface SysRoleService {
    /**
     *
     * 获取根据用户id用户角色列表
     * @version 1.0 2018/10/18 20:28 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param userId 用户id
     * @return java.lang.String[]
     */
    List<String> getUserRoles(Long userId);

    /**
     * 获取角色列表页面
     * @param roleListVO
     * @return
     */
    List<SysRole> getRolePage(RoleListVO roleListVO);

    /**
     * 获取角色总数
     * @param roleListVO
     * @return
     */
    Long getCount(RoleListVO roleListVO);

    /**
     * 更新角色信息
     * @param sysRole
     * @return
     */
    Integer updateRole(SysRole sysRole);

    /**
     * 根据ID删除角色
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    Long addRole(SysRole sysRole);
}
