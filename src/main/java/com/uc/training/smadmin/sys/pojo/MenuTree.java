package com.uc.training.smadmin.sys.pojo;

import com.uc.training.smadmin.sys.model.SysMenu;

import java.util.*;

/**
 * @Author: 余旭东
 * @Date: 2018/10/30 9:38
 * @Description: 菜单树
 */
public class MenuTree {

    /**
     * 生成树
     * @param allMenu 所有菜单列表
     * @return 返回根菜单列表
     */
    public static List<SysMenu> findTree(List<SysMenu> allMenu){
        // 根节点
        List<SysMenu> rootMenu = new ArrayList<>();
        for (int i=0;i<allMenu.size();i++) {
            if (allMenu.get(i).getParentId() == 0) {
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
        // 如果子节点为空，退出递归
        if (childList.size() == 0) {
            return new ArrayList<SysMenu>();
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
            }
        }
        if (childList.size() == 0) {
            return;
        }
        allMenu.removeAll(childList);
        deleteList.addAll(childList);
        for (int i=0;i<childList.size();i++) {
            deleteChildren(childList.get(i), deleteList, allMenu);
        }
    }

    private MenuTree(){}
}
