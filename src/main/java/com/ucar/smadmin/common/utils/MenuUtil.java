package com.ucar.smadmin.common.utils;

import com.ucar.smadmin.base.sys.re.SysMenuRE;
import com.ucar.smadmin.enums.MenuEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 14:04
 * @Description: 菜单树
 */
public final class MenuUtil {
    /**
     * 生成树
     * @param allMenu 所有菜单列表
     * @return 返回根菜单列表
     */
    public static List<SysMenuRE> findTree(List<SysMenuRE> allMenu){
        // 根节点
        List<SysMenuRE> rootMenu = new ArrayList<>();
        for (int i=0;i<allMenu.size();i++) {
            if (allMenu.get(i).getParentId() == MenuEnum.ROOTPID.getNumber()) {
                // 父节点为0的节点为根节点
                rootMenu.add(allMenu.get(i));
            }
        }
        allMenu.removeAll(rootMenu);
        // 为跟节点设置子节点，递归调用getChild方法
        for (SysMenuRE menu : rootMenu) {
            List<SysMenuRE> childList = getChild(menu.getId(), allMenu);
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
    public static List<SysMenuRE> getChild(Long id, List<SysMenuRE> allMenu) {
        // 子菜单
        List<SysMenuRE> childList = new ArrayList<>();
        for (int i=0;i<allMenu.size();i++) {
            if (allMenu.get(i).getParentId().equals(id)) {
                childList.add(allMenu.get(i));
            }
        }
        allMenu.removeAll(childList);
        // 递归
        for (SysMenuRE menu : childList) {
            menu.setChildren(getChild(menu.getId(), allMenu));
        }
        return childList;
    }

    /**
     * 通过要删除的节点的ID获取所有子孙节点的ID
     * @param id
     * @return
     */
    public static List<Long> getDeleteList(Long id, List<SysMenuRE> allMenu) {
        List<Long> deleteList = new ArrayList<>();
        deleteList.add(id);
        deleteChildren(id, deleteList, allMenu);
        return deleteList;
    }

    private static void deleteChildren(Long pid, List<Long> deleteList, List<SysMenuRE> allMenu){
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

    private MenuUtil(){}

}
