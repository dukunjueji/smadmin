package com.uc.training.smadmin.sys.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.constant.Constant;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.sys.model.SysRole;
import com.uc.training.smadmin.sys.service.SysRoleService;
import com.uc.training.smadmin.sys.vo.RoleListVO;
import com.ycc.base.common.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: 余旭东
 * @Date: 2018/10/30 18:03
 * @Description:
 */
@Controller
@RequestMapping("/admin/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "getRolePage.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageVO<SysRole>> getRolePage(@Validated RoleListVO roleListVO) {
        if (roleListVO.getName().length() > Constant.LONGEST_ROLE_NAME) {
            return Result.getBusinessException("", null);
        }
        Result<PageVO<SysRole>> res;
        try {
            PageVO<SysRole> pageVO = new PageVO<SysRole>();
            pageVO.setPageIndex(roleListVO.getPageIndex());
            pageVO.setPageSize(roleListVO.getPageSize());
            pageVO.setTotal(sysRoleService.getCount(roleListVO));
            pageVO.setDataList(sysRoleService.getRolePage(roleListVO));
            res = Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取分页失败", null);
        }
        return res;
    }

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "getRoleList.do_", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SysRole>> getRoleList() {
        return Result.getSuccessResult(sysRoleService.getRoleList());
    }

    /**
     * 获取指定用户的角色列表
     * @param uid
     * @return
     */
    @RequestMapping(value = "getRoleByUid.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Long>> getRoleByUid(Long uid) {
        if (uid == null){
            return Result.getBusinessException("用户ID为空", null);
        }
        return Result.getSuccessResult(sysRoleService.getRoleListByUid(uid));
    }

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "addRole.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> addRole(@Validated SysRole sysRole){
        if (sysRoleService.queryCountByName(sysRole.getName()) > 0) {
            return Result.getBusinessException("该角色已存在", null);
        }
        sysRole.setCreateEmp(getUid());
        return Result.getSuccessResult(sysRoleService.addRole(sysRole));
    }

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "updateRole.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> updateRole(@Validated SysRole sysRole){
        String oldName = sysRoleService.getById(sysRole.getId()).getName();
        if (sysRoleService.queryCountByName(sysRole.getName()) >0 && !sysRole.getName().equals(oldName)){
            return Result.getBusinessException("该角色已存在", null);
        }
        return Result.getSuccessResult(sysRoleService.updateRole(sysRole));
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteRole.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> deleteRole(Long id){
        if (id == null){
            return Result.getBusinessException("id为空", null);
        }
        return Result.getSuccessResult(sysRoleService.deleteById(id));
    }

    /**
     * 添加角色权限
     * @param rid
     * @param ids
     * @return
     */
    @RequestMapping(value = "addAuth.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> addAuth(Long rid, String[] ids){
        // 判空
        if (ids == null) {
            return Result.getBusinessException("删除失败", null);
        }
        List<Long> list = new ArrayList<>();
        for (String s : ids) {
            list.add(Long.parseLong(s));
        }
        return Result.getSuccessResult(sysRoleService.addRoleAuth(rid, list, getUid()));
    }

    /**
     * 获取角色菜单权限ID
     * @param rid
     * @return
     */
    @RequestMapping(value = "getRoleMenu.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Long>> getRoleMenu(Long rid){
        if (rid == null) {
            return Result.getBusinessException("查询失败", null);
        }
        return Result.getSuccessResult(sysRoleService.getRoleMenuIdByRid(rid));
    }


    /**
     * 添加用户角色
     * @param uid
     * @param ids
     * @return
     */
    @RequestMapping(value = "addUserRole.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> addUserRole(Long uid, String[] ids){
        // 判空
        if (ids == null) {
            return Result.getBusinessException("角色添加失败", null);
        }
        List<Long> list = new ArrayList<>();
        for (String s : ids) {
            list.add(Long.parseLong(s));
        }
        return Result.getSuccessResult(sysRoleService.addUserRole(uid, list, getUid()));
    }
}
