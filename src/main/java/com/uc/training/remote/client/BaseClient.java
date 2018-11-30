package com.uc.training.remote.client;

import com.uc.training.base.bd.dto.AddressDTO;
import com.uc.training.base.bd.dto.BannerDTO;
import com.uc.training.base.bd.dto.IntegralDetaillDTO;
import com.uc.training.base.bd.dto.LoginLogDTO;
import com.uc.training.base.bd.dto.MemberDTO;
import com.uc.training.base.bd.dto.MemberGradeDTO;
import com.uc.training.base.bd.dto.MessageDTO;
import com.uc.training.base.bd.re.AddressRE;
import com.uc.training.base.bd.re.BannerRE;
import com.uc.training.base.bd.re.MemberDetailRE;
import com.uc.training.base.bd.re.MemberGradeRE;
import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.re.MessageRE;
import com.uc.training.base.bd.vo.AddressVO;
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
import com.uc.training.remote.utils.RemoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
@Service
public class BaseClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class.getName());

    /** 根据会员信息进行查找*/
    private static final String QUERY_ONE_MEMBER = "smbase.api.queryOneMember";

    /** 根据会员信息进行查找*/
    private static final String UPDATE_MEMBER = "smbase.api.updateMember";

    /** 新增会员*/
    private static final String INSERT_MEMBER = "smbase.api.insertMember";

    /** 获取会员详细信息*/
    private static final String GET_MEMBER_DETAIL_BY_ID = "smbase.api.getMemberDetailById";

    /** 根据会员信息进行查找*/
    private static final String QUERY_MEMBER_GRADE_LIST = "smbase.api.queryMemberGradeList";

    /** 修改会员等级信息*/
    private static final String MODIFY_MEMBER_GRADE = "smbase.api.modifyMemberGrade";

    /** 查找数据总记录数*/
    private static final String QUERY_MEMBER_GRADE_COUNT = "smbase.api.queryMemberGradeCount";

    /** 通过用户ID获取会员信息*/
    private static final String GET_MEMBER_GRADE_BY_ID = "smbase.api.getMemberGradeByUId";

    /** 查找消息数量*/
    private static final String QUERY_MESSAGE_COUNT = "smbase.api.queryMessageCount";

    /** 查询指定会员的所有消息*/
    private static final String QUERY_MESSAGE_LIST = "smbase.api.queryMessageList";

    /** 更新消息*/
    private static final String UPDATE_MESSAGE = "smbase.api.updateMessage";

    /** 更新消息*/
    private static final String CHECK_MEMBER_PASSWORD = "smbase.api.checkMemberPassword";

    /** 新增消息*/
    private static final String INSERT_MESSAGE = "smbase.api.updateMessage";

    /** 通过消息id获取消息的详情*/
    private static final String QUERY_ONE_MESSAGE_BY_ID = "smbase.api.queryOneMessageById";

    /** 插入登陆日志*/
    private static final String INSERT_LOGIN_LOG = "smbase.api.insertLoginLog";

    /** 统计登录表数据*/
    private static final String QUERY_LOGIN_COUNT = "smbase.api.queryLoginCount";

    /** 添加积分详情*/
    private static final String ADD_INTEGRAL_DETAIL = "smbase.api.addIntegralDetail";

    /** 获取轮播图信息(前台)*/
    private static final String GET_BANNER_LIST = "smbase.api.getBannerList";

    /** 获取所有轮播图(后台)*/
    private static final String GET_ALL_BANNER_LIST = "smbase.api.getAllBannerList";

    /** 更新图片*/
    private static final String UPDATE_BANNER = "smbase.api.updateBanner";

    /** 删除图片*/
    private static final String DELETE_BANNER_BY_ID = "smbase.api.deleteBannerById";

    /** 新增轮播图*/
    private static final String INSERT_BANNER = "smbase.api.insertBanner";

    /** 获取图片总数量*/
    private static final String GET_BANNER_COUNT = "smbase.api.getBannerCount";

    /** 根据id查询地址信息*/
    private static final String GET_ADDRESS_BY_ID = "smbase.api.getAddressById";

    /** 获取地址列表*/
    private static final String GET_ADDRESS_LIST = "smbase.api.getAddressList";

    /** 新增地址*/
    private static final String INSERT_ADDRESS = "smbase.api.insertAddress";

    /** 更新地址*/
    private static final String UPDATE_ADDRESS_BY_ID = "smbase.api.updateAddressById";

    /** 删除地址*/
    private static final String DELETE_ADDRESS_BY_ID = "smbase.api.deleteAddressById";

    /** 查询短信列表*/
    private static final String GET_SMS_LIST = "smbase.api.getSmsList";

    /** 查询短信记录总数*/
    private static final String QUERY_SMS_COUNT = "smbase.api.querySmsCount";

    /** 查询单条短信记录*/
    private static final String GET_SMS = "smbase.api.getSms";

    /** 新增短信*/
    private static final String INSERT_SMS = "smbase.api.insertSms";

    /** 新增短信模板*/
    private static final String ADD_TEMPLATE = "smbase.api.addTemplate";

    /** 通过ID删除短信模板*/
    private static final String DELETE_TEMPLATE_BY_ID = "smbase.api.deleteTemplateById";

    /** 修改短信模板*/
    private static final String MODIFY_TEMPLATE = "smbase.api.modifyTemplate";

    /** 通过ID获取短信模板*/
    private static final String GET_TEMPLATE_BY_ID = "smbase.api.getTemplateById";

    /** 获取短信模板列表*/
    private static final String GET_TEMPLATE_LIST = "smbase.api.getTemplateList";

    /** 查询列表总记录数*/
    private static final String GET_TEMPLATE_LIST_COUNT = "smbase.api.getTemplateListCount";

    /** 根据ID列表批量删除短信模板*/
    private static final String BATCH_DELETE_SMS_TEMPLATE_BY_ID = "smbase.api.batchDeleteSmsTempleById";

    /** 生成短信*/
    private static final String GENERATE_SMS = "smbase.api.generateSms";

    /** 根据用户id获取用户权限列表*/
    private static final String GET_USER_PERMS = "smbase.api.getUserPerms";

    /** 获取菜单列表*/
    private static final String GET_MENU_LIST = "smbase.api.getMenuList";

    /** 通过ID获取菜单*/
    private static final String GET_SYS_MENU_BY_ID = "smbase.api.getSysMenuById";

    /** 新增菜单*/
    private static final String ADD_MENU = "smbase.api.addMenu";

    /** 通过ID删除菜单*/
    private static final String DELETE_SYS_MENU_BY_ID = "smbase.api.deleteSysMenuById";

    /** 更新菜单*/
    private static final String UPDATE_MENU = "smbase.api.updateMenu";

    /** 批量删除*/
    private static final String BATCH_DELETE_SYS_MENU = "smbase.api.batchDeleteSysMenu";

    /** 查找用户数量*/
    private static final String QUERY_SYS_MENU_COUNT = "smbase.api.querySysMenuCount";

    /** 获取根据用户id用户角色列表*/
    private static final String GET_USER_ROLES = "smbase.api.getUserRoles";

    /** 获取角色列表页面*/
    private static final String GET_ROLE_PAGE = "smbase.api.getRolePage";

    /** 获取角色总数*/
    private static final String GET_SYS_ROLE_COUNT = "smbase.api.getSysRoleCount";

    /** 更新角色信息*/
    private static final String UPDATE_ROLE = "smbase.api.updateRole";

    /** 根据ID删除角色*/
    private static final String DELETE_SYS_ROLE_BY_ID = "smbase.api.deleteSysRoleById";

    /** 新增角色*/
    private static final String ADD_ROLE = "smbase.api.addRole";

    /** 批量新增角色权限*/
    private static final String BATCH_INSERT_AUTH = "smbase.api.batchInsertAuth";

    /** 通过角色ID获取该ID所有的菜单权限*/
    private static final String GET_ROLE_MENU_ID_BY_RID = "smbase.api.getRoleMenuIdByRid";

    /** 获取角色列表*/
    private static final String GET_ROLE_LIST = "smbase.api.getRoleList";

    /** 通过用户ID获取角色ID*/
    private static final String GET_ROLE_LIST_BY_UID = "smbase.api.getRoleListByUid";

    /** 通过用户ID和菜单ID列表添加用户权限*/
    private static final String ADD_USER_ROLE = "smbase.api.addUserRole";

    /** 查找用户数量*/
    private static final String QUERY_SYS_ROLE_COUNT = "smbase.api.querySysRoleCount";

    /** 通过id查找*/
    private static final String GET_SYS_ROLE_BY_ID = "smbase.api.getSysRoleById";

    /** 用户登录获取用户*/
    private static final String GET_USER_LOGIN = "smbase.api.getUserLogin";

    /** 根据用户id查询用户*/
    private static final String GET_SYS_USER_BY_ID = "smbase.api.getSysUserById";

    /** 修改密码*/
    private static final String UPDATE_PASSWORD = "smbase.api.updatePassword";

    /** 获取用户分页列表*/
    private static final String GET_USER_LIST = "smbase.api.getUserList";

    /** 获取用户数量*/
    private static final String GET_USER_COUNT = "smbase.api.queryUserCount";

    /** 新增用户*/
    private static final String ADD_USER = "smbase.api.addUser";

    /** 通过ID删除用户*/
    private static final String DELETE_SYS_USER_BY_ID = "smbase.api.deleteSysUserById";

    /** 更新用户信息*/
    private static final String UPDATE_USER = "smbase.api.updateUser";

    /** 通过用户ID获取用户的菜单权限*/
    private static final String GET_MENU_LIST_BY_USER_ID = "smbase.api.getMenuListByUserId";

    /**
     * 根据会员信息进行查找
     */
    public static MemberRE queryOneMember(MemberDTO memberDTO) {
        try {
            return (MemberRE) RemoteUtil.exec(QUERY_ONE_MEMBER, memberDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据会员信息进行查找
     */
    public static Integer updateMember(MemberDTO memberDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_MEMBER, memberDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新消息
     */
    public static Boolean checkMemberPassword(MessageDTO messageDTO) {
        try {
            return (Boolean) RemoteUtil.exec(CHECK_MEMBER_PASSWORD, messageDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据会员信息进行查找
     */
    public static Long insertMember(MemberDTO memberDTO) {
        try {
            return (Long) RemoteUtil.exec(INSERT_MEMBER, memberDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取会员详细信息
     */
    public static MemberDetailRE getMemberDetailById(Long id) {
        try {
            return (MemberDetailRE) RemoteUtil.exec(GET_MEMBER_DETAIL_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据会员信息进行查找
     */
    public static List<MemberGradeRE> queryMemberGradeList(MemberGradeDTO memberGradeDTO) {
        try {
            return (List<MemberGradeRE>) RemoteUtil.exec(QUERY_MEMBER_GRADE_LIST, memberGradeDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 修改会员等级信息
     */
    public static Integer modifyMemberGrade(MemberGradeDTO memberGradeDTO) {
        try {
            return (Integer) RemoteUtil.exec(MODIFY_MEMBER_GRADE, memberGradeDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查找数据总记录数
     */
    public static Integer queryMemberGradeCount() {
        try {
            return (Integer) RemoteUtil.exec(QUERY_MEMBER_GRADE_COUNT, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过用户ID获取会员信息
     */
    public static Integer getMemberGradeByUId(Long id) {
        try {
            return (Integer) RemoteUtil.exec(GET_MEMBER_GRADE_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查找消息数量
     */
    public static Integer queryMessageCount(MessageDTO messageDTO) {
        try {
            return (Integer) RemoteUtil.exec(QUERY_MESSAGE_COUNT, messageDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查询指定会员的所有消息
     */
    public static List<MessageRE> queryMessageList(MessageDTO messageDTO) {
        try {
            return (List<MessageRE>) RemoteUtil.exec(QUERY_MESSAGE_LIST, messageDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新消息
     */
    public static Integer updateMessage(MessageDTO messageDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_MESSAGE, messageDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增消息
     */
    public static Long insertMessage(MessageDTO messageDTO) {
        try {
            return (Long) RemoteUtil.exec(INSERT_MESSAGE, messageDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过消息id获取消息的详情
     */
    public static MessageRE queryOneMessageById(MessageDTO messageDTO) {
        try {
            return (MessageRE) RemoteUtil.exec(QUERY_ONE_MESSAGE_BY_ID, messageDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据会员信息进行查找
     */
    public static Long insertLoginLog(LoginLogDTO loginLogDTO) {
        try {
            return (Long) RemoteUtil.exec(INSERT_LOGIN_LOG, loginLogDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查找登陆日志数据
     */
    public static Integer queryLoginCount(LoginLogDTO loginLogDTO) {
        try {
            return (Integer) RemoteUtil.exec(QUERY_LOGIN_COUNT, loginLogDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 添加积分详情
     */
    public static Long addIntegralDetail(IntegralDetaillDTO integralDetaillDTO) {
        try {
            return (Long) RemoteUtil.exec(ADD_INTEGRAL_DETAIL, integralDetaillDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取轮播图信息(前台)
     */
    public static List< BannerRE > getBannerList(BannerDTO bannerDTO) {
        try {
            return (List< BannerRE >) RemoteUtil.exec(GET_BANNER_LIST, bannerDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取所有轮播图(后台)
     */
    public static List<BannerRE> getAllBannerList(BannerDTO bannerDTO) {
        try {
            return (List<BannerRE>) RemoteUtil.exec(GET_ALL_BANNER_LIST, bannerDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新图片
     */
    public static Integer updateBanner(BannerDTO bannerDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_BANNER, bannerDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 删除图片
     */
    public static Integer deleteBannerById(Long id) {
        try {
            return (Integer) RemoteUtil.exec(DELETE_BANNER_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增轮播图
     */
    public static Long insertBanner(Long id) {
        try {
            return (Long) RemoteUtil.exec(INSERT_BANNER, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取图片总数量
     */
    public static Integer getBannerCount(BannerDTO bannerDTO) {
        try {
            return (Integer) RemoteUtil.exec(GET_BANNER_COUNT, bannerDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据id查询地址信息
     */
    public static AddressRE getAddressById(Long id, Long memberId) {
            AddressDTO addressDTO = new AddressDTO();
            if (memberId != null) {
                addressDTO.setMemberId(memberId);
            }
            addressDTO.setId(id);
        try {
            return (AddressRE) RemoteUtil.exec(GET_ADDRESS_BY_ID, addressDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取用户地址
     */
    public static List<AddressRE> getAddressList(AddressDTO addressDTO) {
        try {
            return (List<AddressRE>) RemoteUtil.exec(GET_ADDRESS_LIST, addressDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增地址
     */
    public static Long insertAddress(AddressDTO addressDTO) {
        try {
            return (Long) RemoteUtil.exec(INSERT_ADDRESS, addressDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 修改地址
     */
    public static Integer updateAddressById(AddressDTO addressDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_ADDRESS_BY_ID, addressDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 删除地址
     */
    public static Integer deleteAddressById(AddressVO address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setMemberId(address.getMemberId());
        addressDTO.setId(address.getId());
        try {
            return (Integer) RemoteUtil.exec(DELETE_ADDRESS_BY_ID, addressDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查询短信列表
     */
    public static List<SmsRE> getSmsList(SmsDTO smsDTO) {
        try {
            return (List<SmsRE>) RemoteUtil.exec(GET_SMS_LIST, smsDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查询短信记录总数
     */
    public static Integer querySmsCount(SmsDTO smsDTO) {
        try {
            return (Integer) RemoteUtil.exec(QUERY_SMS_COUNT, smsDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查询单条短信记录
     */
    public static SmsRE getSms(SmsDTO smsDTO) {
        try {
            return (SmsRE) RemoteUtil.exec(GET_SMS, smsDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增短信
     */
    public static SmsRE insertSms(SmsDTO smsDTO) {
        try {
            return (SmsRE) RemoteUtil.exec(INSERT_SMS, smsDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增短信模板
     */
    public static Long insertSms(SmsTemplateDTO smsTemplateDTO) {
        try {
            return (Long) RemoteUtil.exec(ADD_TEMPLATE, smsTemplateDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过ID删除短信模板
     */
    public static Integer deleteTemplateById(Long id) {
        try {
            return (Integer) RemoteUtil.exec(DELETE_TEMPLATE_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 修改短信模板
     */
    public static Integer modifyTemplate(SmsTemplateDTO smsTemplateDTO) {
        try {
            return (Integer) RemoteUtil.exec(MODIFY_TEMPLATE, smsTemplateDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过ID获取短信模板
     */
    public static SmsTemplateRE getTemplateById(Long id) {
        try {
            return (SmsTemplateRE) RemoteUtil.exec(GET_TEMPLATE_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取短信模板列表
     */
    public static List<SmsTemplateRE> getTemplateList(Long id) {
        try {
            return (List<SmsTemplateRE>) RemoteUtil.exec(GET_TEMPLATE_LIST, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查询列表总记录数
     */
    public static Integer getTemplateListCount(SmsTemplateDTO smsTemplateDTO) {
        try {
            return (Integer) RemoteUtil.exec(GET_TEMPLATE_LIST_COUNT, smsTemplateDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据ID列表批量删除短信模板
     */
    public static Integer batchDeleteSmsTempleById(SmsTemplateDTO smsTemplateDTO) {
        try {
            return (Integer) RemoteUtil.exec(BATCH_DELETE_SMS_TEMPLATE_BY_ID, smsTemplateDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 生成短信
     */
    public static String generateSms(SmsTemplateDTO smsTemplateDTO) {
        try {
            return (String) RemoteUtil.exec(GENERATE_SMS, smsTemplateDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据用户id获取用户权限列表
     */
    public static List<String> getUserPerms(Long userId) {
        try {
            return (List<String>) RemoteUtil.exec(GET_USER_PERMS, userId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取菜单列表
     */
    public static List<SysMenuRE> getMenuList(SysMenuDTO sysMenuDTO) {
        try {
            return (List<SysMenuRE>) RemoteUtil.exec(GET_MENU_LIST, sysMenuDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过ID获取菜单
     */
    public static SysMenuRE getSysMenuById(Long id) {
        try {
            return (SysMenuRE) RemoteUtil.exec(GET_SYS_MENU_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增菜单
     */
    public static Long addMenu(SysMenuDTO sysMenuDTO) {
        try {
            return (Long) RemoteUtil.exec(ADD_MENU, sysMenuDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过ID删除菜单
     */
    public static Integer deleteSysMenuById(Long id) {
        try {
            return (Integer) RemoteUtil.exec(DELETE_SYS_MENU_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新菜单
     */
    public static Integer updateMenu(SysMenuDTO sysMenuDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_MENU, sysMenuDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 批量删除
     */
    public static Integer batchDeleteSysMenu(List<Long> ids) {
        try {
            return (Integer) RemoteUtil.exec(BATCH_DELETE_SYS_MENU, ids);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查找用户数量
     */
    public static Integer querySysMenuCount(SysMenuDTO sysMenuDTO) {
        try {
            return (Integer) RemoteUtil.exec(QUERY_SYS_MENU_COUNT, sysMenuDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取根据用户id用户角色列表
     */
    public static List<String> getUserRoles(Long userId) {
        try {
            return (List<String>) RemoteUtil.exec(GET_USER_ROLES, userId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取角色列表页面
     */
    public static List<SysRoleRE> getRolePage(SysRoleDTO sysRoleDTO) {
        try {
            return (List<SysRoleRE>) RemoteUtil.exec(GET_ROLE_PAGE, sysRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取角色总数
     */
    public static Integer getSysRoleCount(SysRoleDTO sysRoleDTO) {
        try {
            return (Integer) RemoteUtil.exec(GET_SYS_ROLE_COUNT, sysRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新角色信息
     */
    public static Integer updateRole(SysRoleDTO sysRoleDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_ROLE, sysRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据ID删除角色
     */
    public static Integer deleteSysRoleById(Long id) {
        try {
            return (Integer) RemoteUtil.exec(DELETE_SYS_ROLE_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增角色
     */
    public static Long addRole(SysRoleDTO sysRoleDTO) {
        try {
            return (Long) RemoteUtil.exec(ADD_ROLE, sysRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 批量新增角色权限
     */
    public static Long batchInsertAuth(SysRoleDTO sysRoleDTO) {
        try {
            return (Long) RemoteUtil.exec(BATCH_INSERT_AUTH, sysRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过角色ID获取该ID所有的菜单权限
     */
    public static List<Long> getRoleMenuIdByRid(SysRoleDTO sysRoleDTO) {
        try {
            return (List<Long>) RemoteUtil.exec(GET_ROLE_MENU_ID_BY_RID, sysRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取角色列表
     */
    public static List<SysRoleRE> getRoleList() {
        try {
            return (List<SysRoleRE>) RemoteUtil.exec(GET_ROLE_LIST, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过用户ID获取角色ID
     */
    public static List<Long> getRoleListByUid() {
        try {
            return (List<Long>) RemoteUtil.exec(GET_ROLE_LIST_BY_UID, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过用户ID和菜单ID列表添加用户权限
     */
    public static Long addUserRole(SysUserRoleDTO sysUserRoleDTO) {
        try {
            return (Long) RemoteUtil.exec(ADD_USER_ROLE, sysUserRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 查找用户数量
     */
    public static Integer querySysRoleCount(SysRoleDTO sysRoleDTO) {
        try {
            return (Integer) RemoteUtil.exec(QUERY_SYS_ROLE_COUNT, sysRoleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过id查找角色
     */
    public static SysRoleRE getSysRoleById(Long id) {
        try {
            return (SysRoleRE) RemoteUtil.exec(GET_SYS_ROLE_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 用户登录获取用户
     */
    public static SysRoleRE getUserLogin(SysUserDTO sysUserDTO) {
        try {
            return (SysRoleRE) RemoteUtil.exec(GET_USER_LOGIN, sysUserDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据用户id查询用户
     */
    public static SysUserRE getSysUserById(Long id) {
        try {
            return (SysUserRE) RemoteUtil.exec(GET_SYS_USER_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 修改密码
     */
    public static Integer updatePassword(SysUserDTO sysUserDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_PASSWORD, sysUserDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取用户分页列表
     */
    public static List<SysUserRE> getUserList(SysUserDTO sysUserDTO) {
        try {
            return (List<SysUserRE>) RemoteUtil.exec(GET_USER_LIST, sysUserDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 获取用户数量
     */
    public static Integer queryUserCount(SysUserDTO sysUserDTO) {
        try {
            return (Integer) RemoteUtil.exec(GET_USER_COUNT, sysUserDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 新增用户
     */
    public static Long addUser(SysUserDTO sysUserDTO) {
        try {
            return (Long) RemoteUtil.exec(ADD_USER, sysUserDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过ID删除用户
     */
    public static Integer deleteSysUserById(Long id) {
        try {
            return (Integer) RemoteUtil.exec(DELETE_SYS_USER_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新用户信息
     */
    public static Integer updateUser(SysUserDTO sysUserDTO) {
        try {
            return (Integer) RemoteUtil.exec(UPDATE_USER, sysUserDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过用户ID获取用户的菜单权限
     */
    public static List<SysMenuRE> getMenuListByUserId(Long uid) {
        try {
            return (List<SysMenuRE>) RemoteUtil.exec(GET_MENU_LIST_BY_USER_ID, uid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }
}
