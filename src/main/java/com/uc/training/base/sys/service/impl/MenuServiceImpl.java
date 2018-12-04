package com.uc.training.base.sys.service.impl;

import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.service.MenuService;
import com.uc.training.base.sys.vo.MenuVO;
import com.uc.training.remote.client.BaseClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:41
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public List<SysMenuRE> getMenuList() {
        return BaseClient.getMenuList();
    }

    @Override
    public Integer queryCountByName(String name) {
        return BaseClient.queryMenuCountByName(name);
    }

    @Override
    public List<String> getUserPerms(Long userId) {
        return BaseClient.getUserPerms(userId);
    }

    @Override
    public SysMenuRE getById(Long id) {
        return BaseClient.getSysMenuById(id);
    }

    @Override
    public Long addMenu(MenuVO menu) {
        return BaseClient.addMenu(menu);
    }

    @Override
    public Integer deleteById(Long id) {
        return BaseClient.deleteSysMenuById(id);
    }

    @Override
    public Integer updateMenu(MenuVO menu) {
        return BaseClient.updateMenu(menu);
    }

    @Override
    public Integer batchDelete(List<Long> ids) {
        return BaseClient.batchDeleteSysMenu(ids);
    }

}