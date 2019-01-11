package com.ucar.smadmin.base.sys.controller;

import com.ucar.smapi.base.sys.re.SysMenuRE;
import com.ucar.smadmin.base.sys.service.MenuService;
import com.ucar.smadmin.base.sys.vo.MenuVO;
import com.ucar.smadmin.common.base.controller.BaseController;
import com.ucar.smadmin.common.utils.MenuUtil;
import com.ucar.smadmin.enums.MenuEnum;
import com.ucar.smapi.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:39
 * @Description: 后台系统菜单
 */
@Controller
@RequestMapping("/admin/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;


    /**
     * 获取菜单列表
     * @return
     */
    @RequestMapping(value = "/getMenu.do_", method = RequestMethod.GET)
    @ResponseBody
    public Result getMenu(){
        List<SysMenuRE> allMenu = menuService.getMenuList();
        List<SysMenuRE> rootMenu = MenuUtil.findTree(allMenu);
        return Result.getSuccessResult(rootMenu);
    }

    /**
     * 添加菜单
     * @param menu
     * @return 被插入菜单的id
     */
    @RequestMapping(value = "addMenu.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result addMenu(@Validated MenuVO menu) {
        // 判空
        boolean verify = (menu.getParentId() == null || menu.getLevel() == null || menu.getType() == null );
        if (verify) {
            return Result.getBusinessException("条件不完整", null);
        }
        // 判断菜单类型是否合法
        if (menu.getType() != MenuEnum.CATALOG.getNumber() && menu.getType() != MenuEnum.BUTTON.getNumber()){
            return Result.getBusinessException("菜单类型不合法", null);
        }
        if (menuService.queryCountByName(menu.getName()) > 0) {
            return Result.getBusinessException("该菜单名已存在", null);
        }
        return Result.getSuccessResult(menuService.addMenu(menu));
    }

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    @RequestMapping(value = "updateMenu.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> updateMenu(@Validated MenuVO menu) {
        String oldName = menuService.getById(menu.getId()).getName();
        if (menuService.queryCountByName(menu.getName()) >0 && !menu.getName().equals(oldName)){
            return Result.getBusinessException("该菜单名已存在", null);
        }
        return Result.getSuccessResult(menuService.updateMenu(menu));
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
        List<Long> deleteList = MenuUtil.getDeleteList(id, menuService.getMenuList());
        return Result.getSuccessResult(menuService.batchDelete(deleteList));
    }


}
