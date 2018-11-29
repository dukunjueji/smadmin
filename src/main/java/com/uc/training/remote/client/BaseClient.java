package com.uc.training.remote.client;

import com.uc.training.base.bd.dto.AddressDTO;
import com.uc.training.base.bd.dto.MemberDTO;
import com.uc.training.base.bd.re.AddressRE;
import com.uc.training.base.bd.re.MemberRE;
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

    /** 新增会员*/
    private static final String INSERT_MEMBER = "smbase.api.insertMember";

    /** 查询会员信息*/
    private static final String GET_MEMBER_INFO_BY_ID = "smbase.api.getGoodsInfoById";

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
    public static Integer deleteAddressById(AddressDTO addressDTO) {
        try {
            return (Integer) RemoteUtil.exec(DELETE_ADDRESS_BY_ID, addressDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }
}
