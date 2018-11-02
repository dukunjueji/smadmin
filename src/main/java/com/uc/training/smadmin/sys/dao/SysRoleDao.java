package com.uc.training.smadmin.sys.dao;

import com.uc.training.smadmin.sys.model.SysRole;
import com.uc.training.smadmin.sys.vo.RoleListVO;

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

    /**
     * 获取角色页面
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

    /**
     * 通过角色ID获取角色菜单权限ID列表
     * @param rid
     * @return
     */
    List<Long> queryMenuAuthByRid(Long rid);

    /**
     * 通过RID删除角色权限
     * @param rid
     * @return
     */
    Integer deleteAuthByRid(Long rid);

    /**
     * 批量新增角色权限
     * @param rid 角色ID
     * @param mid 菜单ID
     * @param createEmp 创建角色ID
     * @return
     */
    Long batchInsertAuth(Long rid, List<Long> mid, Long createEmp);

    /**
     * 获取角色列表
     * @return
     */
    List<SysRole> getRoleList();

    /**
     * 通过用户ID获取角色ID
     * @param uid
     * @return
     */
    List<Long> getRoleListByUid(Long uid);

    /**
     * 通过RID删除角色权限
     * @param uid
     * @return
     */
    Integer deleteRoleByUid(Long uid);

    /**
     * 批量新增角色权限
     * @param uid 角色ID
     * @param rid 菜单ID
     * @param createEmp 创建角色ID
     * @return
     */
    Long batchInsertRole(Long uid, List<Long> rid, Long createEmp);

    /**
     * 通过角色ID删除用户角色关联数据
     * @param rid
     * @return
     */
    Integer deleteUserRoleByRid(Long rid);
}
