package com.uc.training.smadmin.sys.dao.impl;

import com.uc.training.smadmin.sys.dao.SysRoleDao;
import com.uc.training.smadmin.sys.model.SysRole;
import com.uc.training.smadmin.sys.vo.RoleListVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.apache.poi.ss.formula.functions.Na;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：系统角色持久化实现类
 */
@Repository
public class SysRoleDaoImpl extends CarIsIbatisDaoImpl implements SysRoleDao {
    private static final String NAMESPACE = "com.uc.training.smadmin.sys.dao.SysRoleDao.";

    @Override
    public List<String> getUserRoles(Long userId) {
        return (List<String>) this.queryForList(NAMESPACE + "getUserRoles", userId);
    }

    @Override
    public List<SysRole> getRolePage(RoleListVO roleListVO) {
        return this.queryForList(NAMESPACE + "getRolePage", roleListVO);
    }

    @Override
    public Long getCount(RoleListVO roleListVO) {
        return (Long) this.queryForObject(NAMESPACE + "queryCount", roleListVO);
    }

    @Override
    public Integer updateRole(SysRole sysRole) {
        return this.update(NAMESPACE + "updateRole", sysRole);
    }

    @Override
    public Integer deleteById(Long id) {
        return this.deleteObject(NAMESPACE + "deleteById", id);
    }

    @Override
    public Long addRole(SysRole sysRole) {
        return (Long) this.insert(NAMESPACE + "addRole", sysRole);
    }

    @Override
    public List<Long> queryMenuAuthByRid(Long rid) {
        return this.queryForList(NAMESPACE + "queryMenuAuthByRid", rid);
    }

    @Override
    public Integer deleteAuthByRid(Long rid) {
        return this.deleteObject(NAMESPACE + "deleteAuthByRid", rid);
    }

    @Override
    public Long batchInsertAuth(Long rid, List<Long> mid, Long createEmp) {
        Map map = new HashMap();
        map.put("rid", rid);
        map.put("mid", mid);
        map.put("createEmp", createEmp);
        return (Long) this.insert(NAMESPACE + "batchInsertAuth", map);
    }

    @Override
    public List<SysRole> getRoleList() {
        return this.queryForList(NAMESPACE + "getRoleList");
    }

    @Override
    public List<Long> getRoleListByUid(Long uid) {
        return this.queryForList(NAMESPACE + "getRoleListByUid", uid);
    }

    @Override
    public Integer deleteRoleByUid(Long uid) {
        return this.deleteObject(NAMESPACE + "deleteRoleByUid", uid);
    }

    @Override
    public Long batchInsertRole(Long uid, List<Long> rid, Long createEmp) {
        Map map = new HashMap();
        map.put("rid", rid);
        map.put("uid", uid);
        map.put("createEmp", createEmp);
        return (Long) this.insert(NAMESPACE + "batchInsertRole", map);
    }

}
