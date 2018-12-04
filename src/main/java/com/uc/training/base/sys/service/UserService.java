package com.uc.training.base.sys.service;

import com.uc.training.base.sys.dto.SysUserDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.re.SysUserRE;
import com.uc.training.base.sys.vo.UserListVO;
import com.uc.training.base.sys.vo.UserLoginVO;
import com.uc.training.base.sys.vo.UserVO;

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
     * @param userLoginVO 用户登录请求参数
     * @return com.uc.training.smbase.sys.model.SysUser
     */
    SysUserRE getUserLogin(UserLoginVO userLoginVO);

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
     * @param userListVO
     * @return
     */
    List<SysUserRE> getUserList(UserListVO userListVO);

    /**
     * 获取用户数量
     * @param userListVO
     * @return
     */
    Long queryUserCount(UserListVO userListVO);

    /**
     * 新增用户
     * @param user
     * @param uid
     * @param pwd
     * @return
     */
    Long addUser(UserVO user, Long uid, String pwd);

    /**
     * 通过ID删除用户
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 更新用户信息
     * @param user
     * @param uid
     * @return
     */
    Integer updateUser(UserVO user, Long uid);

    /**
     * 通过用户ID获取用户的菜单权限
     * @param uid
     * @return
     */
    List<SysMenuRE> getMenuListByUserId(Long uid);

    /**
     * 根据用户名查询用户数量
     * @param name
     * @return
     */
    Long queryUserCountByName(String name);

}
