package com.uc.training.base.bd.service;

import com.uc.training.base.bd.dto.AddressDTO;
import com.uc.training.base.bd.re.AddressRE;
import com.uc.training.base.bd.vo.AddressVO;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/16 22:23
 * 说明：
 */
public interface AddressService {
    /**
     * 获取用户所有地址
     *
     * @param addressDTO
     * @return
     */
    List<AddressRE> getAddressList(AddressDTO addressDTO);

    /**
     * 新增地址
     *
     * @param address
     * @return
     */
    Long addAddress(AddressDTO address);

    /**
     * 通过主键id获取地址
     * @param id
     * @param memberId
     * @return
     */
    AddressRE getAddressById(Long id, Long memberId);

    /**
     * 修改地址
     *
     * @param addressDTO
     * @return
     */
    Integer editAddress(AddressDTO addressDTO);

    /**
     * 通过主键id删除地址
     *
     * @param address
     * @return
     */
    Integer deleteAddressById(AddressVO address);

    /**
     * 获取默认地址
     * @param uid
     * @return
     */
    AddressRE getDefaultAddress(Long uid);
}
