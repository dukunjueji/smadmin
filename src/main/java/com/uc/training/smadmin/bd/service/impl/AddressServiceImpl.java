package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.common.enums.AddressTypeEnum;
import com.uc.training.smadmin.bd.dao.AddressDao;
import com.uc.training.smadmin.bd.model.Address;
import com.uc.training.smadmin.bd.re.AddressRE;
import com.uc.training.smadmin.bd.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AddressDao addressDao;

    /**
     * 获取会员所有地址
     * @param memberId
     * @return
     */
    @Override
    public List<AddressRE> getAddressByMemberId(Long memberId) {
        return addressDao.getAddressByMemberId(memberId);
    }

    /**
     * 新增地址
     * @param address
     * @return
     */
    @Override
    public Long addAddress(Address address) {

        //取消会员地址的默认
        if (address.getIsDefault() == 1) {
            addressDao.updateIsDefault(address);
        }

        return addressDao.insertAddress(address);
    }

    /**
     * 通过主键id获取地址
     * @param id
     * @return
     */
    @Override
    public AddressRE getAddressById(Long id) {
        return addressDao.getAddressById(id);
    }

    /**
     * 修改地址
     * @param address
     * @return
     */
    @Override
    public Integer editAddress(Address address) {

        if (address.getIsDefault() == AddressTypeEnum.ISDEFAULT.getType()) {
            addressDao.updateIsDefault(address);
        }

        return addressDao.updateAddressById(address);
    }

    /**
     * 通过主键id删除地址
     * @param id
     * @return
     */
    @Override
    public Integer deleteAddressById(Long id) {
        return addressDao.deleteAddress(id);
    }

    /**
     * 获取会员默认地址
     * @param memberId
     * @return
     */
    @Override
    public AddressRE getDefaultAddress(Long memberId) {
        return addressDao.getDefaultAddress(memberId);
    }
}
