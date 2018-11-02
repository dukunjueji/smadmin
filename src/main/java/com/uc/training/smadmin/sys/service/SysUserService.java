package com.uc.training.smadmin.sys.service;

import com.uc.training.smadmin.sys.model.SysUser;
import com.uc.training.smadmin.sys.vo.UserListVO;
import com.uc.training.smadmin.sys.vo.UserLoginVO;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统用户功能逻辑判断接口
 */
public interface SysUserService {
    /**
     * 用户登录获取用户
     *
     * @version 1.0 2018/10/30 15:51 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param userLoginVO 用户登录请求参数
     * @return com.uc.training.smadmin.sys.model.SysUser
     */
    SysUser getUserLogin(UserLoginVO userLoginVO);

    /**
     * 根据用户id查询用户
     *
     * @version 1.0 2018/10/18 19:43 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param id 用户id
     * @return com.uc.training.smadmin.sys.model.SysUser
     */
    SysUser getById(Long id);

    /**
     * 修改密码
     *
     * @version 1.0 2018/10/24 20:29 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param user 用户
     * @return java.lang.Long
     */
    int updatePassword(SysUser user);

    /**
     * 获取用户分页列表
     * @param userListVO
     * @return
     */
    List<SysUser> getUserList(UserListVO userListVO);

    /**
     * 获取用户数量
     * @param userListVO
     * @return
     */
    Long queryUserCount(UserListVO userListVO);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Long addUser(SysUser user);

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
    Integer updateUser(SysUser user);
}
