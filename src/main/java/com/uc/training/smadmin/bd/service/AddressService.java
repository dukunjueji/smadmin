package com.uc.training.smadmin.bd.service;

import com.uc.training.smadmin.bd.model.Address;
import com.uc.training.smadmin.bd.re.AddressRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/16 22:23
 * 说明：
 */
public interface AddressService {
    /**
     * 获取用户所有地址
     * @param memberId
     * @return
     */
    List<AddressRE> getAddressByMemberId(Long memberId);

    /**
     * 新增地址
     * @param address
     * @return
     */
    Long addAddress(Address address);

    /**
     * 通过主键id获取地址
     * @param id
     * @return
     */
    AddressRE getAddressById(Long id);

    /**
     * 修改地址
     * @param address
     * @return
     */
    Integer editAddress(Address address);

    /**
     * 通过主键id删除地址
     * @param id
     * @return
     */
    Integer deleteAddressById(Long id);

    /**
     * 获取会员默认地址
     * @param memberId
     * @return
     */
    AddressRE getDefaultAddress(Long memberId);
}
