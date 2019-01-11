package com.ucar.smadmin.base.sys.service;

import com.ucar.smapi.base.sys.re.SysRoleRE;
import com.ucar.smadmin.base.sys.vo.RoleListVO;
import com.ucar.smadmin.base.sys.vo.RoleVO;

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
     * @param roleListVO
     * @return
     */
    List<SysRoleRE> getRolePage(RoleListVO roleListVO);

    /**
     * 获取角色总数
     * @param roleListVO
     * @return
     */
    Long getCount(RoleListVO roleListVO);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    Integer updateRole(RoleVO role);

    /**
     * 根据ID删除角色
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 新增角色
     * @param role
     * @param createEmp
     * @return
     */
    Long addRole(RoleVO role, Long createEmp);

    /**
     * 通过用户ID和菜单ID列表添加用户权限
     * @param rid
     * @param ids
     * @return
     */
    Long addRoleAuth(Long rid, List<Long> ids);

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
     * @param uid
     * @param list
     * @param createEmp
     * @return
     */
    Long addUserRole(Long uid, List<Long> list, Long createEmp);

    /**
     * 通过角色名查找角色数量
     * @param name
     * @return
     */
    Long queryCountByName(String name);

    /**
     * 通过id查找
     * @param id
     * @return
     */
    SysRoleRE getById(Long id);
}
