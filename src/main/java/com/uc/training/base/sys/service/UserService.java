package com.uc.training.base.sys.service;

import com.uc.training.base.sys.dto.SysUserDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.re.SysUserRE;

import java.util.List;

/**
 *
 * @author：yxd
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统用户功能逻辑判断接口
 */
public interface UserService {
    /**
     * 用户登录获取用户
     *
     * @version 1.0 2018/10/30 15:51 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param SysUserDTO 用户登录请求参数
     * @return com.uc.training.smbase.sys.model.SysUser
     */
    SysUserRE getUserLogin(SysUserDTO SysUserDTO);

    /**
     * 根据用户id查询用户
     *
     * @version 1.0 2018/10/18 19:43 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param id 用户id
     * @return com.uc.training.smbase.sys.model.SysUser
     */
    SysUserRE getById(Long id);

    /**
     * 修改密码
     *
     * @version 1.0 2018/10/24 20:29 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param user 用户
     * @return java.lang.Long
     */
    int updatePassword(SysUserDTO user);

    /**
     * 获取用户分页列表
     * @param sysUserDTO
     * @return
     */
    List<SysUserRE> getUserList(SysUserDTO sysUserDTO);

    /**
     * 获取用户数量
     * @param sysUserDTO
     * @return
     */
    Long queryUserCount(SysUserDTO sysUserDTO);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Long addUser(SysUserDTO user);

    /**
     * 通过ID删除用户
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer updateUser(SysUserDTO user);

    /**
     * 通过用户ID获取用户的菜单权限
     * @param uid
     * @return
     */
    List<SysMenuRE> getMenuListByUserId(Long uid);

}
