package com.ucar.smadmin.base.bd.service.impl;

import com.ucar.smapi.base.bd.dto.AddressDTO;
import com.ucar.smapi.base.bd.re.AddressRE;
import com.ucar.smadmin.base.bd.service.AddressService;
import com.ucar.smadmin.base.bd.vo.AddressVO;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
private BaseClient baseClient;
    /**
     * 获取会员所有地址
     * @param addressVO
     * @return
     */
    @Override
    public List<AddressRE> getAddressList(AddressVO addressVO) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(addressVO, addressDTO);
        return baseClient.getAddressList(addressDTO).getRe();
    }

    /**
     * 新增地址
     * @param address
     * @return
     */
    @Override
    public Long addAddress(AddressVO address) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, addressDTO);
        return baseClient.insertAddress(addressDTO).getRe();
    }

    /**
     * 通过主键id获取地址
     * @param id
     * @param memberId
     * @return
     */
    @Override
    public AddressRE getAddressById(Long id, Long memberId) {
        AddressDTO addressDTO = new AddressDTO();
        if (memberId != null) {
            addressDTO.setMemberId(memberId);
        }
        addressDTO.setId(id);
        return baseClient.getAddressById(addressDTO).getRe();
    }

    /**
     * 修改地址
     * @param addressVO
     * @return
     */
    @Override
    public Integer editAddress(AddressVO addressVO) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(addressVO, addressDTO);
        return baseClient.updateAddressById(addressDTO).getRe();
    }

    /**
     * 通过主键id删除地址
     * @param address
     * @return
     */
    @Override
    public Integer deleteAddressById(AddressVO address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setMemberId(address.getMemberId());
        addressDTO.setId(address.getId());
        return baseClient.deleteAddressById(addressDTO).getRe();
    }

    /**
     * 获取默认地址
     *
     * @param uid
     * @return
     */
    @Override
    public AddressRE getDefaultAddress(Long uid) {
        AddressVO addressVO = new AddressVO();
        addressVO.setMemberId(uid);
        addressVO.setIsDefault(1);
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(addressVO, addressDTO);
        List<AddressRE> list = baseClient.getAddressList(addressDTO).getRe();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

}
