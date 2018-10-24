package com.uc.training.smadmin.sys.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.bean.AccessToken;
import com.uc.training.smadmin.sys.model.SysUser;
import com.uc.training.smadmin.sys.re.UserInfoRE;
import com.uc.training.smadmin.sys.re.UserLoginRE;
import com.uc.training.smadmin.sys.service.SysMenuService;
import com.uc.training.smadmin.sys.service.SysRoleService;
import com.uc.training.smadmin.sys.service.SysUserService;
import com.uc.training.smadmin.sys.vo.UserLoginVO;
import com.uc.training.smadmin.utils.EncryptUtil;
import com.uc.training.smadmin.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/18 15:13 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
@Controller
@RequestMapping(value = "api/sys/user/")
public class SysUserController extends BaseController {
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
    @RequestMapping("login.do_")
    public Result<UserLoginRE> login(UserLoginVO userLoginVO) {
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
    @AccessLogin
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
                userLoginRE.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80");
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

}
