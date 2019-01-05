package com.uc.training.remote.client;

import com.uc.training.base.bd.dto.AddressDTO;
import com.uc.training.base.bd.dto.BannerDTO;
import com.uc.training.base.bd.dto.GrowthDetailDTO;
import com.uc.training.base.bd.dto.IntegralDetaillDTO;
import com.uc.training.base.bd.dto.LoginLogDTO;
import com.uc.training.base.bd.dto.MemberDTO;
import com.uc.training.base.bd.dto.MemberGradeDTO;
import com.uc.training.base.bd.dto.MemberRechargeHistoryDTO;
import com.uc.training.base.bd.dto.MemberRechargeHistoryModelDTO;
import com.uc.training.base.bd.dto.MessageDTO;
import com.uc.training.base.bd.re.AddressRE;
import com.uc.training.base.bd.re.BannerRE;
import com.uc.training.base.bd.re.MemberDetailRE;
import com.uc.training.base.bd.re.MemberGradeRE;
import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.re.MemberRechargeHistoryListRE;
import com.uc.training.base.bd.re.MessageRE;
import com.uc.training.base.sms.dto.SmsDTO;
import com.uc.training.base.sms.dto.SmsTemplateDTO;
import com.uc.training.base.sms.re.SmsRE;
import com.uc.training.base.sms.re.SmsTemplateRE;
import com.uc.training.base.sys.dto.SysMenuDTO;
import com.uc.training.base.sys.dto.SysRoleDTO;
import com.uc.training.base.sys.dto.SysUserDTO;
import com.uc.training.base.sys.dto.SysUserRoleDTO;
import com.uc.training.base.sys.re.SysMenuRE;
import com.uc.training.base.sys.re.SysRoleRE;
import com.uc.training.base.sys.re.SysUserRE;
import com.uc.training.common.vo.RemoteResult;
import com.uc.training.remote.client.fallback.BaseClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
@FeignClient(name = "provider-smbase", fallback = BaseClientFallback.class)
public interface BaseClient {
    /**
     * 根据会员信息进行查找
     *
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryOneMember")
    RemoteResult<MemberRE> queryOneMember(@RequestBody MemberDTO memberDTO);

    /**
     * 获取会员数量
     *
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryMemberCount")
    RemoteResult<Long> queryMemberCount(@RequestBody MemberDTO memberDTO);

    /**
     * 获取会员数量
     *
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryMemberList")
    RemoteResult<List<MemberRE>> queryMemberList(@RequestBody MemberDTO memberDTO);

    /**
     * 根据会员信息进行查找
     *
     * @param memberDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updateMember")
    RemoteResult<Integer> updateMember(@RequestBody MemberDTO memberDTO);

    /**
     * 新增会员
     *
     * @param member
     * @return
     */
    @PostMapping(value = "smbase/api/insertMember")
    RemoteResult<Long> insertMember(@RequestBody MemberDTO member);

    /**
     * 获取会员详细信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/getMemberDetailById")
    RemoteResult<MemberDetailRE> getMemberDetailById(@RequestBody Long id);

    /**
     * 根据会员信息进行查找
     *
     * @param memberGradeDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryMemberGradeList")
    RemoteResult<List<MemberGradeRE>> queryMemberGradeList(@RequestBody MemberGradeDTO memberGradeDTO);

    /**
     * 修改会员等级信息
     *
     * @param memberGradeDTO
     * @return
     */
    @PostMapping(value = "smbase/api/modifyMemberGrade")
    RemoteResult<Integer> modifyMemberGrade(@RequestBody MemberGradeDTO memberGradeDTO);

    /**
     * 通过用户ID获取折扣
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/getDiscountByUId")
    RemoteResult<Double> getDiscountByUId(@RequestBody Long id);

    /**
     * 查找消息数量
     *
     * @param messageDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryMessageCount")
    RemoteResult<Integer> queryMessageCount(@RequestBody MessageDTO messageDTO);

    /**
     * 查询指定会员的所有消息
     *
     * @param messageDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryMessageList")
    RemoteResult<List<MessageRE>> queryMessageList(@RequestBody MessageDTO messageDTO);

    /**
     * 更新消息
     *
     * @param messageDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updateMessage")
    RemoteResult<Integer> updateMessage(@RequestBody MessageDTO messageDTO);

    /**
     * 新增消息
     *
     * @param messageDTO
     * @return
     */
    @PostMapping(value = "smbase/api/insertMessage")
    RemoteResult<Long> insertMessage(@RequestBody MessageDTO messageDTO);

    /**
     * 通过消息id获取消息的详情
     *
     * @param messageDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryOneMessageById")
    RemoteResult<MessageRE> queryOneMessageById(@RequestBody MessageDTO messageDTO);

    /**
     * 根据会员信息进行查找
     *
     * @param loginLogDTO
     * @return
     */
    @PostMapping(value = "smbase/api/insertLoginLog")
    RemoteResult<Long> insertLoginLog(@RequestBody LoginLogDTO loginLogDTO);

    /**
     * 查找登陆日志数据
     *
     * @param loginLogDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryLoginCount")
    RemoteResult<Long> queryLoginCount(@RequestBody LoginLogDTO loginLogDTO);

    /**
     * 添加积分详情
     *
     * @param integralDetaillDTO
     * @return
     */
    @PostMapping(value = "smbase/api/addIntegralDetail")
    RemoteResult<Long> addIntegralDetail(@RequestBody IntegralDetaillDTO integralDetaillDTO);

    /**
     * 获取轮播图信息(前台)
     *
     * @param bannerDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getBannerList")
    RemoteResult<List<BannerRE>> getBannerList(@RequestBody BannerDTO bannerDTO);

    /**
     * 获取所有轮播图(后台)
     *
     * @param bannerDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getAllBannerList")
    RemoteResult<List<BannerRE>> getAllBannerList(@RequestBody BannerDTO bannerDTO);

    /**
     * 更新图片
     *
     * @param bannerDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updateBanner")
    RemoteResult<Integer> updateBanner(@RequestBody BannerDTO bannerDTO);

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/deleteBannerById")
    RemoteResult<Integer> deleteBannerById(@RequestBody Long id);

    /**
     * 新增轮播图
     *
     * @param bannerDTO
     * @return
     */
    @PostMapping(value = "smbase/api/insertBanner")
    RemoteResult<Long> insertBanner(@RequestBody BannerDTO bannerDTO);

    /**
     * 获取图片数量
     *
     * @param bannerDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getBannerCount")
    RemoteResult<Long> getBannerCount(@RequestBody BannerDTO bannerDTO);

    /**
     * 添加积分详情
     *
     * @param growthDetailDTO
     * @return
     */
    @PostMapping(value = "smbase/api/saveGrowthDetail")
    RemoteResult<Long> saveGrowthDetail(@RequestBody GrowthDetailDTO growthDetailDTO);

    /**
     * 根据id查询地址信息
     *
     * @param addressDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getAddressById")
    RemoteResult<AddressRE> getAddressById(@RequestBody AddressDTO addressDTO);

    /**
     * 获取用户地址
     *
     * @param addressDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getAddressList")
    RemoteResult<List<AddressRE>> getAddressList(@RequestBody AddressDTO addressDTO);

    /**
     * 新增地址
     *
     * @param addressDTO
     * @return
     */
    @PostMapping(value = "smbase/api/insertAddress")
    RemoteResult<Long> insertAddress(@RequestBody AddressDTO addressDTO);

    /**
     * 修改地址
     *
     * @param addressDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updateAddressById")
    RemoteResult<Integer> updateAddressById(@RequestBody AddressDTO addressDTO);

    /**
     * 删除地址
     *
     * @param addressDTO
     * @return
     */
    @PostMapping(value = "smbase/api/deleteAddressById")
    RemoteResult<Integer> deleteAddressById(@RequestBody AddressDTO addressDTO);

    /**
     * hhj
     * 根据会员id获取充值记录
     *
     * @param memberRechargeHistoryDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getRechargeHistoryListByMemberId")
    RemoteResult<List<MemberRechargeHistoryListRE>> getRechargeHistoryListByMemberId(@RequestBody MemberRechargeHistoryDTO memberRechargeHistoryDTO);

    /**
     * hhj
     * 根据会员id获取总记录数
     *
     * @param memberId
     * @return
     */
    @PostMapping(value = "smbase/api/getCountByMemberId")
    RemoteResult<Long> getCountByMemberId(@RequestBody Long memberId);

    /**
     * hhj
     * 新增充值记录
     *
     * @param memberRechargeHistoryModelDTO
     * @return
     */
    @PostMapping(value = "smbase/api/insertMemberRechargeHistory")
    RemoteResult<Long> insertMemberRechargeHistory(@RequestBody MemberRechargeHistoryModelDTO memberRechargeHistoryModelDTO);

    /**
     * 查询短信列表
     *
     * @param smsDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getSmsList")
    RemoteResult<List<SmsRE>> getSmsList(@RequestBody SmsDTO smsDTO);

    /**
     * 查询短信记录总数
     *
     * @param smsDTO
     * @return
     */
    @PostMapping(value = "smbase/api/querySmsCount")
    RemoteResult<Long> querySmsCount(@RequestBody SmsDTO smsDTO);

    /**
     * 新增短信
     *
     * @param smsDTO
     * @return
     */
    @PostMapping(value = "smbase/api/insertSms")
    RemoteResult<Long> insertSms(@RequestBody SmsDTO smsDTO);

    /**
     * 新增短信模板
     *
     * @param smsTemplateDTO
     * @return
     */
    @PostMapping(value = "smbase/api/addTemplate")
    RemoteResult<Long> addTemplate(@RequestBody SmsTemplateDTO smsTemplateDTO);

    /**
     * 通过ID删除短信模板
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/deleteTemplateById")
    RemoteResult<Integer> deleteTemplateById(@RequestBody Long id);

    /**
     * 修改短信模板
     *
     * @param smsTemplateDTO
     * @return
     */
    @PostMapping(value = "smbase/api/modifyTemplate")
    RemoteResult<Integer> modifyTemplate(@RequestBody SmsTemplateDTO smsTemplateDTO);

    /**
     * 通过ID获取短信模板
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/getTemplateById")
    RemoteResult<SmsTemplateRE> getTemplateById(@RequestBody Long id);

    /**
     * 获取短信模板列表
     *
     * @param smsTemplateDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getTemplateList")
    RemoteResult<List<SmsTemplateRE>> getTemplateList(@RequestBody SmsTemplateDTO smsTemplateDTO);

    /**
     * 查询列表总记录数
     *
     * @param smsTemplateDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getTemplateListCount")
    RemoteResult<Long> getTemplateListCount(@RequestBody SmsTemplateDTO smsTemplateDTO);

    /**
     * 根据ID列表批量删除短信模板
     *
     * @param list
     * @return
     */
    @PostMapping(value = "smbase/api/batchDeleteSmsTempleById")
    RemoteResult<Integer> batchDeleteSmsTempleById(@RequestBody List<Long> list);

    /**
     * 生成短信
     *
     * @param smsTemplateDTO
     * @return
     */
    @PostMapping(value = "smbase/api/generateSms")
    RemoteResult<String> generateSms(@RequestBody SmsTemplateDTO smsTemplateDTO);

    /**
     * 根据用户id获取用户权限列表
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "smbase/api/getUserPerms")
    RemoteResult<List<String>> getUserPerms(@RequestBody Long userId);

    /**
     * 获取菜单列表
     *
     * @return
     */
    @PostMapping(value = "smbase/api/getMenuList")
    RemoteResult<List<SysMenuRE>> getMenuList();

    /**
     * 通过ID获取菜单
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/getSysMenuById")
    RemoteResult<SysMenuRE> getSysMenuById(@RequestBody Long id);

    /**
     * 新增菜单
     *
     * @param sysMenuDTO
     * @return
     */
    @PostMapping(value = "smbase/api/addMenu")
    RemoteResult<Long> addMenu(@RequestBody SysMenuDTO sysMenuDTO);

    /**
     * 通过名字查找菜单数目
     *
     * @param menuDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryMenuCountByName")
    RemoteResult<Long> queryMenuCountByName(@RequestBody SysMenuDTO menuDTO);

    /**
     * 通过ID删除菜单
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/deleteSysMenuById")
    RemoteResult<Integer> deleteSysMenuById(@RequestBody Long id);

    /**
     * 更新菜单
     *
     * @param menuDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updateMenu")
    RemoteResult<Integer> updateMenu(@RequestBody SysMenuDTO menuDTO);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "smbase/api/batchDeleteSysMenu")
    RemoteResult<Integer> batchDeleteSysMenu(@RequestBody List<Long> ids);

    /**
     * 获取根据用户id用户角色列表
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "smbase/api/getUserRoles")
    RemoteResult<List<String>> getUserRoles(@RequestBody Long userId);

    /**
     * 获取角色列表页面
     *
     * @param roleDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getRolePage")
    RemoteResult<List<SysRoleRE>> getRolePage(@RequestBody SysRoleDTO roleDTO);

    /**
     * 获取角色总数
     *
     * @param roleDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getSysRoleCount")
    RemoteResult<Long> getSysRoleCount(@RequestBody SysRoleDTO roleDTO);

    /**
     * 更新角色信息
     *
     * @param roleDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updateRole")
    RemoteResult<Integer> updateRole(@RequestBody SysRoleDTO roleDTO);

    /**
     * 根据ID删除角色
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/deleteSysRoleById")
    RemoteResult<Integer> deleteSysRoleById(@RequestBody Long id);

    /**
     * 新增角色
     *
     * @param roleDTO
     * @return
     */
    @PostMapping(value = "smbase/api/addRole")
    RemoteResult<Long> addRole(@RequestBody SysRoleDTO roleDTO);

    /**
     * 批量新增角色权限
     *
     * @param sysRoleDTO
     * @return
     */
    @PostMapping(value = "smbase/api/batchInsertAuth")
    RemoteResult<Long> batchInsertAuth(@RequestBody SysRoleDTO sysRoleDTO);

    /**
     * 通过角色ID获取该ID所有的菜单权限
     *
     * @param rid
     * @return
     */
    @PostMapping(value = "smbase/api/getRoleMenuIdByRid")
    RemoteResult<List<Long>> getRoleMenuIdByRid(@RequestBody Long rid);

    /**
     * 获取角色列表
     *
     * @return
     */
    @PostMapping(value = "smbase/api/getRoleList")
    RemoteResult<List<SysRoleRE>> getRoleList();

    /**
     * 通过用户ID获取角色ID
     *
     * @param uid
     * @return
     */
    @PostMapping(value = "smbase/api/getRoleListByUid")
    RemoteResult<List<Long>> getRoleListByUid(@RequestBody Long uid);

    /**
     * 通过用户ID和菜单ID列表添加用户权限
     *
     * @param sysUserRoleDTO
     * @return
     */
    @PostMapping(value = "smbase/api/addUserRole")
    RemoteResult<Long> addUserRole(@RequestBody SysUserRoleDTO sysUserRoleDTO);

    /**
     * 查找用户数量
     *
     * @param name
     * @return
     */
    @PostMapping(value = "smbase/api/querySysRoleCount")
    RemoteResult<Long> querySysRoleCount(@RequestBody String name);

    /**
     * 通过id查找角色
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/getSysRoleById")
    RemoteResult<SysRoleRE> getSysRoleById(@RequestBody Long id);

    /**
     * 用户登录获取用户
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getUserLogin")
    RemoteResult<SysUserRE> getUserLogin(@RequestBody SysUserDTO userDTO);

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/getSysUserById")
    RemoteResult<SysUserRE> getSysUserById(@RequestBody Long id);

    /**
     * 修改密码
     *
     * @param sysUserDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updatePassword")
    RemoteResult<Integer> updatePassword(@RequestBody SysUserDTO sysUserDTO);

    /**
     * 获取用户分页列表
     *
     * @param sysUserDTO
     * @return
     */
    @PostMapping(value = "smbase/api/getUserList")
    RemoteResult<List<SysUserRE>> getUserList(@RequestBody SysUserDTO sysUserDTO);

    /**
     * 获取用户数量
     *
     * @param sysUserDTO
     * @return
     */
    @PostMapping(value = "smbase/api/queryUserCount")
    RemoteResult<Long> queryUserCount(@RequestBody SysUserDTO sysUserDTO);

    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "smbase/api/addUser")
    RemoteResult<Long> addUser(@RequestBody SysUserDTO userDTO);

    /**
     * 通过ID删除用户
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smbase/api/deleteSysUserById")
    RemoteResult<Integer> deleteSysUserById(@RequestBody Long id);

    /**
     * 更新用户信息
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "smbase/api/updateUser")
    RemoteResult<Integer> updateUser(@RequestBody SysUserDTO userDTO);

    /**
     * 通过用户ID获取用户的菜单权限
     *
     * @param uid
     * @return
     */
    @PostMapping(value = "smbase/api/getMenuListByUserId")
    RemoteResult<List<SysMenuRE>> getMenuListByUserId(@RequestBody Long uid);

    /*public static Long addRoleAuth(Long rid, List<Long> list) {
        SysRoleMenuDTO sysRoleMenuDTO = new SysRoleMenuDTO();
        sysRoleMenuDTO.setMenuId(list);
        sysRoleMenuDTO.setRoleId(rid);
        try {
            return (Long) RemoteUtil.exec(ADD_ROLE_AUTH, sysRoleMenuDTO);
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }*/

    /**
     * 通过名字查询用户数量
     *
     * @param name
     * @return
     */
    @PostMapping(value = "smbase/api/queryUserCountByName")
    RemoteResult<Long> queryUserCountByName(@RequestBody String name);
}
