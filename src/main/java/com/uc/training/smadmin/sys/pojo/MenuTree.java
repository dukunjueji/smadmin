package com.uc.training.smadmin.sys.pojo;

import com.uc.training.common.enums.MenuEnum;
import com.uc.training.smadmin.sys.model.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/30 9:38
 * @Description: 菜单树
 */
public final class MenuTree {

    /**
     * 生成树
     * @param allMenu 所有菜单列表
     * @return 返回根菜单列表
     */
    public static List<SysMenu> findTree(List<SysMenu> allMenu){
        // 根节点
        List<SysMenu> rootMenu = new ArrayList<>();
        for (int i=0;i<allMenu.size();i++) {
            if (allMenu.get(i).getParentId() == MenuEnum.ROOTPID.getNumber()) {
                // 父节点为0的节点为根节点
                rootMenu.add(allMenu.get(i));
            }
        }
        allMenu.removeAll(rootMenu);
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
        for (int i=0;i<allMenu.size();i++) {
            if (allMenu.get(i).getParentId().equals(id)) {
                childList.add(allMenu.get(i));
            }
        }
        allMenu.removeAll(childList);
        // 递归
        for (SysMenu menu : childList) {
            menu.setChildren(getChild(menu.getId(), allMenu));
        }
        return childList;
    }

    /**
     * 通过要删除的节点的ID获取所有子孙节点的ID
     * @param id
     * @return
     */
    public static List<Long> getDeleteList(Long id, List<SysMenu> allMenu) {
        List<Long> deleteList = new ArrayList<>();
        deleteList.add(id);
        deleteChildren(id, deleteList, allMenu);
        return deleteList;
    }

    private static void deleteChildren(Long pid, List<Long> deleteList, List<SysMenu> allMenu){
        List<Long> childList = new ArrayList<>();
        for (int i=0;i<allMenu.size();i++) {
            // 父节点ID匹配
            if (allMenu.get(i).getParentId().equals(pid)) {
                childList.add(allMenu.get(i).getId());
                allMenu.remove(i--);
            }
        }
        if (childList.size() == 0) {
            return;
        }
        deleteList.addAll(childList);
        for (int i=0;i<childList.size();i++) {
            deleteChildren(childList.get(i), deleteList, allMenu);
        }
    }

    private MenuTree(){}
}
