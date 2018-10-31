package com.uc.training.smadmin.sys.pojo;

import com.uc.training.smadmin.sys.model.SysMenu;
import com.uc.training.smadmin.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @Author: 余旭东
 * @Date: 2018/10/30 9:38
 * @Description: 菜单树
 */
public class MenuTree {


    /**
     * 根据sorNum进行排序
     * @return
     */
    public static Comparator<SysMenu> order() {
        Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                if (!o1.getSortNum().equals(o2.getSortNum())){
                    return (int) (o1.getSortNum() - o2.getSortNum());
                }
                return 0;
            }
        };
        return comparator;
    }

    /**
     * 生成树
     * @param allMenu 所有菜单列表
     * @return 返回根菜单列表
     */
    public static List<SysMenu> findTree(List<SysMenu> allMenu){
        // 根节点
        List<SysMenu> rootMenu = new ArrayList<>();
        for (SysMenu menu : allMenu) {
            if (menu.getParentId() == 0) {
                // 父节点为0的节点为根节点
                rootMenu.add(menu);
            }
        }
        // 根据菜单的 orderNum 进行排序
        Collections.sort(rootMenu, order());
        // 为跟节点设置子节点，递归调用getChild方法
        for (SysMenu menu : rootMenu) {
            List<SysMenu> childList = getChild(menu.getId(), allMenu);
            menu.setChildren(childList);
        }
        return rootMenu;
    }

    /**
     * 获取子节点
     * @param id 父节点ID
     * @param allMenu 所有的菜单列表
     * @return
     */
    public static List<SysMenu> getChild(Long id, List<SysMenu> allMenu) {
        // 子菜单
        List<SysMenu> childList = new ArrayList<>();
        for (SysMenu menu : allMenu) {
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
        }
        // 递归
        for (SysMenu menu : childList) {
            menu.setChildren(getChild(menu.getId(), allMenu));
        }
        // 排序
        Collections.sort(childList, order());
        // 如果子节点为空，退出递归
        if (childList.size() == 0) {
            return new ArrayList<SysMenu>();
        }
        return childList;
    }

    private MenuTree(){}
}
