package com.uc.training.smadmin.sys.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.sys.model.SysRole;
import com.uc.training.smadmin.sys.service.SysRoleService;
import com.uc.training.smadmin.sys.vo.RoleListVO;
import com.ycc.base.common.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public Result<PageVO<SysRole>> getRolePage(RoleListVO roleListVO) {
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
            res = Result.getBusinessException("获取smsTemplate分页失败", null);
        }
        return res;
    }

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "addRole.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> addRole(SysRole sysRole){
        if (sysRole == null || StringUtils.isEmpty(sysRole.getName())){
            return Result.getBusinessException("角色名为空", null);
        }
        return Result.getSuccessResult(sysRoleService.addRole(sysRole));
    }

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "updateRole.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> updateRole(SysRole sysRole){
        if (sysRole == null || StringUtils.isEmpty(sysRole.getName())){
            return Result.getBusinessException("角色名为空", null);
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
}
