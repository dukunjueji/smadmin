package com.uc.training.smadmin.sys.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.constant.Constant;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.sys.model.SysMenu;
import com.uc.training.smadmin.sys.model.SysUser;
import com.uc.training.smadmin.sys.pojo.MenuTree;
import com.uc.training.smadmin.sys.re.UserInfoRE;
import com.uc.training.smadmin.sys.re.UserLoginRE;
import com.uc.training.smadmin.sys.service.SysMenuService;
import com.uc.training.smadmin.sys.service.SysRoleService;
import com.uc.training.smadmin.sys.service.SysUserService;
import com.uc.training.smadmin.sys.vo.UserEditPasswordVo;
import com.uc.training.smadmin.sys.vo.UserListVO;
import com.uc.training.smadmin.sys.vo.UserLoginVO;
import com.uc.training.smadmin.utils.EncryptUtil;
import com.uc.training.smadmin.utils.TokenUtil;
import com.ycc.base.common.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/18 15:13 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
@Controller
@RequestMapping(value = "admin/sys/user/")
public class UserController extends BaseController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysMenuService menuService;
    /**
     * 用户登录
     *
     * @param userLoginVO 登录请求参数
     * @return com.ycc.base.common.Result<com.uc.training.smadmin.sys.re.UserLoginRE>
     * @version 1.0 2018/10/18 15:22 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    @ResponseBody
    @AccessLogin(required = false)
    @RequestMapping("login.do_")
    public Result<UserLoginRE> login(@Validated UserLoginVO userLoginVO) {
        Result<UserLoginRE> res;
        try {
            String password = EncryptUtil.md5(userLoginVO.getPassword());
            userLoginVO.setPassword(password);
            // 判断账号密码是否匹配
            SysUser user = userService.getUserLogin(userLoginVO);
            if (user == null) {
                logger.info("用户名或密码不正确");
                res = Result.getBusinessException("用户名或密码不正确", null);
            } else {
                String token = TokenUtil.sign(user.getId());
                UserLoginRE userLoginRE = new UserLoginRE();
                userLoginRE.setToken(token);
                res = Result.getSuccessResult(userLoginRE);
            }
        } catch (Exception e) {
            logger.error("用户登录异常！", e);
            res = Result.getServiceError("用户登录异常", null);
        }
        return res;
    }

    /**
     * 获取用户信息
     *
     * @param
     * @return com.ycc.base.common.Result<com.uc.training.smadmin.sys.re.UserInfoRE>
     * @version 1.0 2018/10/18 20:18 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    @ResponseBody
    @RequestMapping("getUserInfo.do_")
    public Result<UserInfoRE> getUserInfo() {
        Result<UserInfoRE> res;
        try {
            Long userId = getUid();
            // 获取用户信息
            SysUser user = userService.getById(userId);
            if (user == null) {
                logger.info("用户不存在");
                res = Result.getBusinessException("用户不存在", null);
            } else {
                // 用户角色
                List<String> roles = roleService.getUserRoles(userId);
                // 用户权限
                List<String> perms = menuService.getUserPerms(userId);

                UserInfoRE userLoginRE = new UserInfoRE();
                userLoginRE.setUsername(user.getUserName());
                userLoginRE.setAdmin(user.getIsAdmin() == 1 ? true : false);
                userLoginRE.setRoles(roles);
                userLoginRE.setPerms(perms);
                res = Result.getSuccessResult(userLoginRE);
            }
        } catch (Exception e) {
            logger.error("获取用户信息异常！", e);
            res = Result.getServiceError("获取用户信息异常", null);
        }
        return res;
    }

    /**
     * 用户修改密码
     *
     * @version 1.0 2018/10/24 20:38 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param userEditPasswordVo 用户修改密码参数vo
     * @return com.ycc.base.common.Result<java.lang.Integer>
     */
    @ResponseBody
    @RequestMapping("editPassword.do_")
    public Result<Integer> editPassword(@Validated UserEditPasswordVo userEditPasswordVo) {
        Result<Integer> res;
        try {
            Long userId = getUid();
            SysUser user = userService.getById(userId);
            if (user == null) {
                res = Result.getBusinessException("用户不存在", null);
            } else {
                String oldPwd = EncryptUtil.md5(userEditPasswordVo.getOldPwd());
                if (user.getPassword().equals(oldPwd)) {
                    SysUser sysUser = new SysUser();
                    sysUser.setId(userId);
                    sysUser.setPassword(EncryptUtil.md5(userEditPasswordVo.getNewPwd()));
                    sysUser.setModifyEmp(userId);
                    res = Result.getSuccessResult(userService.updatePassword(sysUser));
                } else {
                    res = Result.getBusinessException("原始密码不正确", null);
                }
            }
        } catch (Exception e) {
            logger.error("用户修改密码异常！", e);
            res = Result.getServiceError("用户修改密码异常", null);
        }
        return res;
    }

    /**
     * 获取用户列表
     * @param userListVO
     * @return
     */
    @RequestMapping(value = "/getUserPage.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageVO<SysUser>> getUserList(@Validated UserListVO userListVO){
        Result<PageVO<SysUser>> res;
        try {
            PageVO<SysUser> pageVO = new PageVO<SysUser>();
            pageVO.setPageIndex(userListVO.getPageIndex());
            pageVO.setPageSize(userListVO.getPageSize());
            pageVO.setTotal(userService.queryUserCount(userListVO));
            pageVO.setDataList(userService.getUserList(userListVO));
            res = Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取分页失败", null);
        }
        return res;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/addUser.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> addUser(@Validated SysUser user){
        // 判空
        if (user == null || StringUtils.isEmpty(user.getUserName())){
            return Result.getBusinessException("输入用户不能为空", null);
        }
        String pwd = EncryptUtil.md5(Constant.DEFAULT_PASSWORD);
        Integer count = userService.queryCountByName(user.getUserName());
        if (count > 0) {
            return Result.getBusinessException("该用户已存在", null);
        }
        user.setCreateEmp(getUid());
        user.setPassword(pwd);
        Long id = userService.addUser(user);
        return Result.getSuccessResult(id);
    }

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/deleteUser.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> deleteUser(Long id){
        return Result.getSuccessResult(userService.deleteById(id));
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/editUser.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> editUser(@Validated SysUser user){
        // 判空
        if (user == null || StringUtils.isEmpty(user.getUserName())|| user.getId() == null){
            return Result.getBusinessException("用户更新失败", null);
        }
        String oldName = userService.getById(user.getId()).getUserName();
        if (userService.queryCountByName(user.getUserName()) >0 && !user.getUserName().equals(oldName)){
            return Result.getBusinessException("该用户已存在", null);
        }
        user.setModifyEmp(getUid());
        return Result.getSuccessResult(userService.updateUser(user));
    }

    /**
     * 根据用户ID获取用户的菜单权限
     * @param uid
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/getUserMenuAuth.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<SysMenu>> getUserMenuAuth(Long uid){
        if (uid == null) {
            return Result.getBusinessException("权限获取失败", null);
        }
        List<SysMenu> allMenu = userService.getMenuListByUserId(uid);
        List<SysMenu> rootMenu = MenuTree.findTree(allMenu);
        return Result.getSuccessResult(rootMenu);
    }
}
