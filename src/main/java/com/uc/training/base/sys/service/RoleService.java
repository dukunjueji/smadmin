package com.uc.training.base.sys.service;

import com.uc.training.base.sys.dto.SysRoleDTO;
import com.uc.training.base.sys.dto.SysRoleMenuDTO;
import com.uc.training.base.sys.dto.SysUserRoleDTO;
import com.uc.training.base.sys.re.SysRoleRE;

import java.util.List;

/**
 * @author 余旭东
 * @description: 角色服务
 */
public interface RoleService {
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
     * @param sysRoleDTO
     * @return
     */
    List<SysRoleRE> getRolePage(SysRoleDTO sysRoleDTO);

    /**
     * 获取角色总数
     * @param sysRoleDTO
     * @return
     */
    Long getCount(SysRoleDTO sysRoleDTO);

    /**
     * 更新角色信息
     * @param roleDTO
     * @return
     */
    Integer updateRole(SysRoleDTO roleDTO);

    /**
     * 根据ID删除角色
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 新增角色
     * @param roleDTO
     * @return
     */
    Long addRole(SysRoleDTO roleDTO);

    /**
     * 通过用户ID和菜单ID列表添加用户权限
     * @param sysRoleMenuDTO
     * @return
     */
    Long addRoleAuth(SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * 通过角色ID获取该ID所有的菜单权限
     * @param rid
     * @return
     */
    List<Long> getRoleMenuIdByRid(Long rid);

    /**
     * 获取角色列表
     * @return
     */
    List<SysRoleRE> getRoleList();

    /**
     * 通过用户ID获取角色ID
     * @param uid
     * @return
     */
    List<Long> getRoleListByUid(Long uid);

    /**
     * 通过用户ID和菜单ID列表添加用户权限
     * @param sysUserRoleDTO
     * @return
     */
    Long addUserRole(SysUserRoleDTO sysUserRoleDTO);

    /**
     * 通过角色名查找角色数量
     * @param name
     * @return
     */
    Integer queryCountByName(String name);

    /**
     * 通过id查找
     * @param id
     * @return
     */
    SysRoleRE getById(Long id);
}
