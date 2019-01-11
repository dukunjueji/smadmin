package com.ucar.smadmin.remote.remoteclient.impl;

import com.ucar.smadmin.base.bd.dto.AddressDTO;
import com.ucar.smadmin.base.bd.dto.BannerDTO;
import com.ucar.smadmin.base.bd.dto.GrowthDetailDTO;
import com.ucar.smadmin.base.bd.dto.IntegralDetaillDTO;
import com.ucar.smadmin.base.bd.dto.LoginLogDTO;
import com.ucar.smadmin.base.bd.dto.MemberDTO;
import com.ucar.smadmin.base.bd.dto.MemberGradeDTO;
import com.ucar.smadmin.base.bd.dto.MemberRechargeHistoryDTO;
import com.ucar.smadmin.base.bd.dto.MemberRechargeHistoryModelDTO;
import com.ucar.smadmin.base.bd.dto.MessageDTO;
import com.ucar.smadmin.base.bd.re.AddressRE;
import com.ucar.smadmin.base.bd.re.BannerRE;
import com.ucar.smadmin.base.bd.re.MemberDetailRE;
import com.ucar.smadmin.base.bd.re.MemberGradeRE;
import com.ucar.smadmin.base.bd.re.MemberRE;
import com.ucar.smadmin.base.bd.re.MemberRechargeHistoryListRE;
import com.ucar.smadmin.base.bd.re.MessageRE;
import com.ucar.smadmin.base.sms.dto.SmsDTO;
import com.ucar.smadmin.base.sms.dto.SmsTemplateDTO;
import com.ucar.smadmin.base.sms.re.SmsRE;
import com.ucar.smadmin.base.sms.re.SmsTemplateRE;
import com.ucar.smadmin.base.sys.dto.SysMenuDTO;
import com.ucar.smadmin.base.sys.dto.SysRoleDTO;
import com.ucar.smadmin.base.sys.dto.SysUserDTO;
import com.ucar.smadmin.base.sys.dto.SysUserRoleDTO;
import com.ucar.smadmin.base.sys.re.SysMenuRE;
import com.ucar.smadmin.base.sys.re.SysRoleRE;
import com.ucar.smadmin.base.sys.re.SysUserRE;
import com.ucar.smadmin.common.vo.RemoteResult;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * base熔断接口
 * 
 * @author dk
 * @date 2019年1月5日 下午1:58:32
 * @version V1.0
 */
@Component
public class BaseClientFallback implements BaseClient {

	/**
	 * 根据会员信息进行查找
	 *
	 * @param memberDTO
	 * @return
	 */
	@Override
	public RemoteResult<MemberRE> queryOneMember(MemberDTO memberDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取会员数量
	 *
	 * @param memberDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> queryMemberCount(MemberDTO memberDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取会员数量
	 *
	 * @param memberDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<MemberRE>> queryMemberList(MemberDTO memberDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据会员信息进行查找
	 *
	 * @param memberDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updateMember(MemberDTO memberDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增会员
	 *
	 * @param member
	 * @return
	 */
	@Override
	public RemoteResult<Long> insertMember(MemberDTO member) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取会员详细信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<MemberDetailRE> getMemberDetailById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据会员信息进行查找
	 *
	 * @param memberGradeDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<MemberGradeRE>> queryMemberGradeList(MemberGradeDTO memberGradeDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 修改会员等级信息
	 *
	 * @param memberGradeDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> modifyMemberGrade(MemberGradeDTO memberGradeDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过用户ID获取折扣
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<Double> getDiscountByUId(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 查找消息数量
	 *
	 * @param messageDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> queryMessageCount(MessageDTO messageDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 查询指定会员的所有消息
	 *
	 * @param messageDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<MessageRE>> queryMessageList(MessageDTO messageDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 更新消息
	 *
	 * @param messageDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updateMessage(MessageDTO messageDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增消息
	 *
	 * @param messageDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> insertMessage(MessageDTO messageDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过消息id获取消息的详情
	 *
	 * @param messageDTO
	 * @return
	 */
	@Override
	public RemoteResult<MessageRE> queryOneMessageById(MessageDTO messageDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据会员信息进行查找
	 *
	 * @param loginLogDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> insertLoginLog(LoginLogDTO loginLogDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 查找登陆日志数据
	 *
	 * @param loginLogDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> queryLoginCount(LoginLogDTO loginLogDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 添加积分详情
	 *
	 * @param integralDetaillDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> addIntegralDetail(IntegralDetaillDTO integralDetaillDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取轮播图信息(前台)
	 *
	 * @param bannerDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<BannerRE>> getBannerList(BannerDTO bannerDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取所有轮播图(后台)
	 *
	 * @param bannerDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<BannerRE>> getAllBannerList(BannerDTO bannerDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 更新图片
	 *
	 * @param bannerDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updateBanner(BannerDTO bannerDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 删除图片
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<Integer> deleteBannerById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增轮播图
	 *
	 * @param bannerDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> insertBanner(BannerDTO bannerDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取图片数量
	 *
	 * @param bannerDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> getBannerCount(BannerDTO bannerDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 添加积分详情
	 *
	 * @param growthDetailDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> saveGrowthDetail(GrowthDetailDTO growthDetailDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据id查询地址信息
	 *
	 * @param addressDTO
	 * @return
	 */
	@Override
	public RemoteResult<AddressRE> getAddressById(AddressDTO addressDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取用户地址
	 *
	 * @param addressDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<AddressRE>> getAddressList(AddressDTO addressDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增地址
	 *
	 * @param addressDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> insertAddress(AddressDTO addressDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 修改地址
	 *
	 * @param addressDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updateAddressById(AddressDTO addressDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 删除地址
	 *
	 * @param addressDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> deleteAddressById(AddressDTO addressDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * hhj
	 * 根据会员id获取充值记录
	 *
	 * @param memberRechargeHistoryDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<MemberRechargeHistoryListRE>> getRechargeHistoryListByMemberId(MemberRechargeHistoryDTO memberRechargeHistoryDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * hhj
	 * 根据会员id获取总记录数
	 *
	 * @param memberId
	 * @return
	 */
	@Override
	public RemoteResult<Long> getCountByMemberId(Long memberId) {
		return RemoteResult.getServiceError();
	}

	/**
	 * hhj
	 * 新增充值记录
	 *
	 * @param memberRechargeHistoryModelDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> insertMemberRechargeHistory(MemberRechargeHistoryModelDTO memberRechargeHistoryModelDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 查询短信列表
	 *
	 * @param smsDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<SmsRE>> getSmsList(SmsDTO smsDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 查询短信记录总数
	 *
	 * @param smsDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> querySmsCount(SmsDTO smsDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增短信
	 *
	 * @param smsDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> insertSms(SmsDTO smsDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增短信模板
	 *
	 * @param smsTemplateDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> addTemplate(SmsTemplateDTO smsTemplateDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过ID删除短信模板
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<Integer> deleteTemplateById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 修改短信模板
	 *
	 * @param smsTemplateDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> modifyTemplate(SmsTemplateDTO smsTemplateDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过ID获取短信模板
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<SmsTemplateRE> getTemplateById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取短信模板列表
	 *
	 * @param smsTemplateDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<SmsTemplateRE>> getTemplateList(SmsTemplateDTO smsTemplateDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 查询列表总记录数
	 *
	 * @param smsTemplateDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> getTemplateListCount(SmsTemplateDTO smsTemplateDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据ID列表批量删除短信模板
	 *
	 * @param list
	 * @return
	 */
	@Override
	public RemoteResult<Integer> batchDeleteSmsTempleById(List<Long> list) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 生成短信
	 *
	 * @param smsTemplateDTO
	 * @return
	 */
	@Override
	public RemoteResult<String> generateSms(SmsTemplateDTO smsTemplateDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据用户id获取用户权限列表
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public RemoteResult<List<String>> getUserPerms(Long userId) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取菜单列表
	 *
	 * @return
	 */
	@Override
	public RemoteResult<List<SysMenuRE>> getMenuList() {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过ID获取菜单
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<SysMenuRE> getSysMenuById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增菜单
	 *
	 * @param sysMenuDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> addMenu(SysMenuDTO sysMenuDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过名字查找菜单数目
	 *
	 * @param menuDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> queryMenuCountByName(SysMenuDTO menuDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过ID删除菜单
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<Integer> deleteSysMenuById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 更新菜单
	 *
	 * @param menuDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updateMenu(SysMenuDTO menuDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public RemoteResult<Integer> batchDeleteSysMenu(List<Long> ids) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取根据用户id用户角色列表
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public RemoteResult<List<String>> getUserRoles(Long userId) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取角色列表页面
	 *
	 * @param roleDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<SysRoleRE>> getRolePage(SysRoleDTO roleDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取角色总数
	 *
	 * @param roleDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> getSysRoleCount(SysRoleDTO roleDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 更新角色信息
	 *
	 * @param roleDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updateRole(SysRoleDTO roleDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据ID删除角色
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<Integer> deleteSysRoleById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增角色
	 *
	 * @param roleDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> addRole(SysRoleDTO roleDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 批量新增角色权限
	 *
	 * @param sysRoleDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> batchInsertAuth(SysRoleDTO sysRoleDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过角色ID获取该ID所有的菜单权限
	 *
	 * @param rid
	 * @return
	 */
	@Override
	public RemoteResult<List<Long>> getRoleMenuIdByRid(Long rid) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取角色列表
	 *
	 * @return
	 */
	@Override
	public RemoteResult<List<SysRoleRE>> getRoleList() {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过用户ID获取角色ID
	 *
	 * @param uid
	 * @return
	 */
	@Override
	public RemoteResult<List<Long>> getRoleListByUid(Long uid) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过用户ID和菜单ID列表添加用户权限
	 *
	 * @param sysUserRoleDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> addUserRole(SysUserRoleDTO sysUserRoleDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 查找用户数量
	 *
	 * @param name
	 * @return
	 */
	@Override
	public RemoteResult<Long> querySysRoleCount(String name) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过id查找角色
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<SysRoleRE> getSysRoleById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 用户登录获取用户
	 *
	 * @param userDTO
	 * @return
	 */
	@Override
	public RemoteResult<SysUserRE> getUserLogin(SysUserDTO userDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 根据用户id查询用户
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<SysUserRE> getSysUserById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 修改密码
	 *
	 * @param sysUserDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updatePassword(SysUserDTO sysUserDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取用户分页列表
	 *
	 * @param sysUserDTO
	 * @return
	 */
	@Override
	public RemoteResult<List<SysUserRE>> getUserList(SysUserDTO sysUserDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 获取用户数量
	 *
	 * @param sysUserDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> queryUserCount(SysUserDTO sysUserDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 新增用户
	 *
	 * @param userDTO
	 * @return
	 */
	@Override
	public RemoteResult<Long> addUser(SysUserDTO userDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过ID删除用户
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RemoteResult<Integer> deleteSysUserById(Long id) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDTO
	 * @return
	 */
	@Override
	public RemoteResult<Integer> updateUser(SysUserDTO userDTO) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过用户ID获取用户的菜单权限
	 *
	 * @param uid
	 * @return
	 */
	@Override
	public RemoteResult<List<SysMenuRE>> getMenuListByUserId(Long uid) {
		return RemoteResult.getServiceError();
	}

	/**
	 * 通过名字查询用户数量
	 *
	 * @param name
	 * @return
	 */
	@Override
	public RemoteResult<Long> queryUserCountByName(String name) {
		return RemoteResult.getServiceError();
	}
}
