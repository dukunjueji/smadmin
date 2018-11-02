package com.uc.training.smadmin.sys.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.MenuEnum;
import com.uc.training.smadmin.sys.model.SysMenu;
import com.uc.training.smadmin.sys.pojo.MenuTree;
import com.uc.training.smadmin.sys.service.SysMenuService;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/30 10:33
 * @Description:
 */
@Controller
@RequestMapping("/admin/sys/menu")
public class MenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取菜单列表
     * @return
     */
    @RequestMapping(value = "/getMenu.do_", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SysMenu>> getMenu(){
        List<SysMenu> allMenu = sysMenuService.getMenuList();
        List<SysMenu> rootMenu = MenuTree.findTree(allMenu);
        return Result.getSuccessResult(rootMenu);
    }

    /**
     * 添加菜单
     * @param menu
     * @return 被插入菜单的id
     */
    @RequestMapping(value = "addMenu.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> addMenu(@Validated SysMenu menu) {
        // 判空
        boolean verify = (menu.getParentId() == null || menu.getLevel() == null || menu.getType() == null );
        if (verify) {
            return Result.getBusinessException("条件不完整", null);
        }
        // 判断菜单类型是否合法
        if (menu.getType() != MenuEnum.CATALOG.getNumber() && menu.getType() != MenuEnum.BUTTON.getNumber()){
            return Result.getBusinessException("菜单类型不合法", null);
        }
        return Result.getSuccessResult(sysMenuService.addMenu(menu));
    }

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    @RequestMapping(value = "updateMenu.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> updateMenu(@Validated SysMenu menu) {
        return Result.getSuccessResult(sysMenuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteMenu.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> deleteMenu(Long id) {
        if (id == null) {
            return Result.getBusinessException("条件不完整", null);
        }
        List<Long> deleteList = MenuTree.getDeleteList(id, sysMenuService.getMenuList());
        return Result.getSuccessResult(sysMenuService.batchDelete(deleteList));
    }
}
