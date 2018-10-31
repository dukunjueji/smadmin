package com.uc.training.smadmin.sys.controller;

import com.uc.training.smadmin.sys.model.SysMenu;
import com.uc.training.smadmin.sys.pojo.MenuTree;
import com.uc.training.smadmin.sys.service.SysMenuService;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.uc.training.smadmin.sys.pojo.MenuTree.findTree;

/**
 * @Author: 余旭东
 * @Date: 2018/10/30 10:33
 * @Description:
 */
@Controller
@RequestMapping("/admin/sys/menu")
public class MenuController {
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
}
