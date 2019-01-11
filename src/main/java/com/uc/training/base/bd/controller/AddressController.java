package com.uc.training.base.bd.controller;

import com.uc.training.base.bd.re.AddressRE;
import com.uc.training.base.bd.service.AddressService;
import com.uc.training.base.bd.vo.AddressVO;
import com.smgoods.common.base.controller.BaseController;
import com.smgoods.common.utils.ReplaceStarUtils;
import com.smgoods.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/16 13:14
 */
@RestController
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
        if (id == null) {
            return Result.getBusinessException("请选择有效地址", null);
        }
        return Result.getSuccessResult(addressService.getAddressById(id, getUid()));
    }

    /**
     * 获取会员默认地址
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDefaultAddress.do_")
    public Result<AddressRE> getDefaultAddress() {
        AddressRE addressRE = addressService.getDefaultAddress(getUid());
        if (addressRE != null && addressRE.getTelephone() != null) {
            addressRE.setTelephone(ReplaceStarUtils.replaceAction(addressRE.getTelephone()));
        }
        return Result.getSuccessResult(addressRE);
    }

    /**
     * 通过会员id查找所有地址
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAddressList.do_")
    public Result<List<AddressRE>> getAddressListBy() {
        AddressVO addressVO = new AddressVO();
        addressVO.setMemberId(getUid());
        return Result.getSuccessResult(addressService.getAddressList(addressVO));
    }

    /**
     * 新增地址
     * @param addressInsertVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAddress.do_", method = RequestMethod.POST)
    public Result addAddress(@Validated AddressVO addressInsertVO) {

        addressInsertVO.setMemberId(getUid());
        addressInsertVO.setCreateEmp(getUid());
        addressInsertVO.setModifyEmp(getUid());

        return Result.getSuccessResult(addressService.addAddress(addressInsertVO));
    }

    /**
     * 编辑地址
     * @param addressVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editAddress.do_", method = RequestMethod.POST)
    public Result editAddress(@Validated AddressVO addressVO) {

        addressVO.setMemberId(getUid());
        addressVO.setModifyEmp(getUid());

        return Result.getSuccessResult(addressService.editAddress(addressVO));
    }

    /**
     * 通过主键id删除地址
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAddress.do_", method = RequestMethod.POST)
    public Result deleteAddress(Long id) {
        if (id == null) {
            return Result.getBusinessException("请选择有效地址！", null);
        }

        // 判断是否为默认地址
        AddressRE addressRE = addressService.getAddressById(id, getUid());
        if (addressRE != null && addressRE.getIsDefault() == 1) {
            return Result.getBusinessException("默认地址不能删除！",null);
        }

        AddressVO address = new AddressVO();
        address.setMemberId(getUid());
        address.setId(id);

        return Result.getSuccessResult(addressService.deleteAddressById(address));
    }
}