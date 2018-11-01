package com.uc.training.smadmin.bd.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.model.Address;
import com.uc.training.smadmin.bd.re.AddressRE;
import com.uc.training.smadmin.bd.service.AddressService;
import com.uc.training.smadmin.bd.vo.AddressVO;
import com.uc.training.smadmin.utils.TelCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/16 13:14
 */
@Controller
@RequestMapping("/api/address")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    /**
     * 根据主键id获取地址
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAddressById.do_")
    public Result<AddressRE> getAddressById(Long id) {

        Result<AddressRE> address = Result.getSuccessResult(addressService.getAddressById(id));

        return address;
    }

    /**
     * 获取会员默认地址
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDefaultAddress.do_")
    public Result<AddressRE> getDefaultAddress() {

        Result<AddressRE> address = Result.getSuccessResult(addressService.getDefaultAddress(getUid()));

        return address;
    }

    /**
     * 通过会员id查找所有地址
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAddressList.do_")
    public Result<List<AddressRE>> getAddressListBy() {

        Result<List<AddressRE>> addressList = Result.getSuccessResult(addressService.getAddressByMemberId(getUid()));

        return addressList;
    }

    /**
     * 新增地址
     * @param addressVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAddress.do_", method = RequestMethod.POST)
    public Result addAddress(@Validated AddressVO addressVO) {

        Address address = new Address();
        BeanUtils.copyProperties(addressVO, address);

        address.setMemberId(getUid());
        address.setCreateEmp(getUid());
        address.setModifyEmp(getUid());

        return Result.getSuccessResult(addressService.addAddress(address));
    }

    /**
     * 编辑地址
     * @param addressVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editAddress.do_", method = RequestMethod.POST)
    public Result editAddress(@Validated AddressVO addressVO) {

        //判断收件人
        if (addressVO.getReceiver() == null || addressVO.getReceiver().length() <= 0) {
            return Result.getBusinessException("收件人不能为空", null);
        }
        //判断手机号
        if (!TelCodeUtil.validateTel(addressVO.getTelephone())) {
            return Result.getBusinessException("请输入正确手机号", null);
        }

        Address address = new Address();
        BeanUtils.copyProperties(addressVO, address);

        address.setMemberId(getUid());
        address.setModifyEmp(getUid());

        return Result.getSuccessResult(addressService.editAddress(address));
    }

    /**
     * 通过主键id删除地址
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAddress.do_", method = RequestMethod.POST)
    public Result deleteAddress(Long id) {

        // 判断是否为默认地址
        if (addressService.getAddressById(id).getIsDefault() == 1) {
            return Result.getBusinessException("默认地址不能删除！",null);
        }

        return Result.getSuccessResult(addressService.deleteAddressById(id));
    }
}