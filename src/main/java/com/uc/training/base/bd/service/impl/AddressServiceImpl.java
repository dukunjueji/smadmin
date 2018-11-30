package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.dto.AddressDTO;
import com.uc.training.base.bd.re.AddressRE;
import com.uc.training.base.bd.service.AddressService;
import com.uc.training.base.bd.vo.AddressVO;
import com.uc.training.remote.client.BaseClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/16 22:24
 * 说明：
 */
@Service
public class AddressServiceImpl implements AddressService {

    /**
     * 获取会员所有地址
     * @param addressDTO
     * @return
     */
    @Override
    public List<AddressRE> getAddressList(AddressDTO addressDTO) {
        return BaseClient.getAddressList(addressDTO);
    }

    /**
     * 新增地址
     * @param addressDTO
     * @return
     */
    @Override
    public Long addAddress(AddressDTO addressDTO) {
        return BaseClient.insertAddress(addressDTO);
    }

    /**
     * 通过主键id获取地址
     * @param id
     * @param memberId
     * @return
     */
    @Override
    public AddressRE getAddressById(Long id, Long memberId) {
        return BaseClient.getAddressById(id, memberId);
    }

    /**
     * 修改地址
     * @param addressDTO
     * @return
     */
    @Override
    public Integer editAddress(AddressDTO addressDTO) {
        return BaseClient.updateAddressById(addressDTO);
    }

    /**
     * 通过主键id删除地址
     * @param address
     * @return
     */
    @Override
    public Integer deleteAddressById(AddressVO address) {
        return BaseClient.deleteAddressById(address);
    }

    /**
     * 获取默认地址
     *
     * @param uid
     * @return
     */
    @Override
    public AddressRE getDefaultAddress(Long uid) {

        BaseClient.getAddressList()
        return null;
    }

}
