package com.uc.training.base.sys.service;

import com.uc.training.base.sys.dto.SysMenuDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.vo.MenuVO;

import java.util.List;

/**
 * @author 余旭东
 * @description: 菜单服务
 */
public interface MenuService {
    /**
     * 获取菜单列表
     *
     * @return 菜单列表
     */
    List<SysMenuRE> getMenuList();

    /**
     * 通过菜单名查询是否含有与name重名的菜单
     * @param name
     * @return
     */
    Long queryCountByName(String name);

    /**
     * 根据用户id获取用户权限列表
     *
     * @param userId
     * @return java.lang.String[]
     * @version 1.0 2018/10/18 20:29 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<String> getUserPerms(Long userId);

    /**
     * 通过ID获取菜单
     * @param id
     * @return
     */
    SysMenuRE getById(Long id);

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    Long addMenu(MenuVO menu);

    /**
     * 通过ID删除菜单
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    Integer updateMenu(MenuVO menu);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer batchDelete(List<Long> ids);
}
