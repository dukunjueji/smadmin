package com.uc.training.base.sys.service.impl;

import com.uc.training.base.sys.dto.SysMenuDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.service.MenuService;
import com.uc.training.base.sys.vo.MenuVO;
import com.uc.training.remote.client.BaseClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:41
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private BaseClient baseClient;

    @Override
    public List<SysMenuRE> getMenuList() {
        return baseClient.getMenuList().getRe();
    }

    @Override
    public Long queryCountByName(String name) {
        SysMenuDTO menuDTO = new SysMenuDTO();
        menuDTO.setName(name);
        return baseClient.queryMenuCountByName(menuDTO).getRe();
    }

    @Override
    public List<String> getUserPerms(Long userId) {
        return baseClient.getUserPerms(userId).getRe();
    }

    @Override
    public SysMenuRE getById(Long id) {
        return baseClient.getSysMenuById(id).getRe();
    }

    @Override
    public Long addMenu(MenuVO menu) {
        SysMenuDTO sysMenuDTO = new SysMenuDTO();
        BeanUtils.copyProperties(menu, sysMenuDTO);
        return baseClient.addMenu(sysMenuDTO).getRe();
    }

    @Override
    public Integer deleteById(Long id) {
        return baseClient.deleteSysMenuById(id).getRe();
    }

    @Override
    public Integer updateMenu(MenuVO menu) {
        SysMenuDTO menuDTO = new SysMenuDTO();
        BeanUtils.copyProperties(menu, menuDTO);
        return baseClient.updateMenu(menuDTO).getRe();
    }

    @Override
    public Integer batchDelete(List<Long> ids) {
        return baseClient.batchDeleteSysMenu(ids).getRe();
    }

}
