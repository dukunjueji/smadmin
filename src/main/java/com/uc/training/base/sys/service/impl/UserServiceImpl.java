package com.uc.training.base.sys.service.impl;

import com.uc.training.base.sys.dto.SysUserDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.re.SysUserRE;
import com.uc.training.base.sys.service.UserService;
import com.uc.training.base.sys.vo.UserListVO;
import com.uc.training.base.sys.vo.UserLoginVO;
import com.uc.training.base.sys.vo.UserVO;
import com.uc.training.remote.remoteclient.BaseClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:42
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BaseClient baseClient;
    @Override
    public SysUserRE getUserLogin(UserLoginVO userLoginVO) {
        SysUserDTO userDTO = new SysUserDTO();
        BeanUtils.copyProperties(userLoginVO, userDTO);
        return baseClient.getUserLogin(userDTO).getRe();
    }

    @Override
    public SysUserRE getById(Long id) {
        return baseClient.getSysUserById(id).getRe();
    }

    @Override
    public int updatePassword(SysUserDTO user) {
        return baseClient.updatePassword(user).getRe();
    }

    @Override
    public List<SysUserRE> getUserList(UserListVO userListVO) {
        SysUserDTO sysUserDTO = new SysUserDTO();
        BeanUtils.copyProperties(userListVO, sysUserDTO);
        return baseClient.getUserList(sysUserDTO).getRe();
    }

    @Override
    public Long queryUserCount(UserListVO userListVO) {
        SysUserDTO sysUserDTO = new SysUserDTO();
        BeanUtils.copyProperties(userListVO, sysUserDTO);
        return baseClient.queryUserCount(sysUserDTO).getRe();
    }

    @Override
    public Long addUser(UserVO user, Long uid, String pwd) {
        SysUserDTO userDTO = new SysUserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setCreateEmp(uid);
        userDTO.setPassword(pwd);
        return baseClient.addUser(userDTO).getRe();
    }

    @Override
    public Integer deleteById(Long id) {
        return baseClient.deleteSysUserById(id).getRe();
    }

    @Override
    public Integer updateUser(UserVO user, Long uid) {
        SysUserDTO userDTO = new SysUserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setModifyEmp(uid);
        return baseClient.updateUser(userDTO).getRe();
    }

    @Override
    public List<SysMenuRE> getMenuListByUserId(Long uid) {
        return baseClient.getMenuListByUserId(uid).getRe();
    }

    @Override
    public Long queryUserCountByName(String name) {
        return baseClient.queryUserCountByName(name).getRe();
    }

}
