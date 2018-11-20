package com.uc.training.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.model.Address;
import com.uc.training.smadmin.bd.re.AddressRE;
import com.uc.training.smadmin.bd.service.AddressService;
import com.uc.training.smadmin.bd.vo.AddressInsertVO;
import com.uc.training.smadmin.bd.vo.AddressUpdateVO;
import com.ycc.base.common.Result;
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

        //判断该地址是否本人拥有
        if (!getUid().equals(addressService.getMemberIdById(id))) {
            return Result.getBusinessException("您没有该地址！请不要查询！", null);
        }

        return Result.getSuccessResult(addressService.getAddressById(id));
    }

    /**
     * 获取会员默认地址
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDefaultAddress.do_")
    public Result<AddressRE> getDefaultAddress() {
        return Result.getSuccessResult(addressService.getDefaultAddress(getUid()));
    }

    /**
     * 通过会员id查找所有地址
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAddressList.do_")
    public Result<List<AddressRE>> getAddressListBy() {
        return Result.getSuccessResult(addressService.getAddressByMemberId(getUid()));
    }

    /**
     * 新增地址
     * @param addressInsertVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAddress.do_", method = RequestMethod.POST)
    public Result addAddress(@Validated AddressInsertVO addressInsertVO) {

        Address address = new Address();
        BeanUtils.copyProperties(addressInsertVO, address);

        address.setMemberId(getUid());
        address.setCreateEmp(getUid());
        address.setModifyEmp(getUid());

        return Result.getSuccessResult(addressService.addAddress(address));
    }

    /**
     * 编辑地址
     * @param addressUpdateVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editAddress.do_", method = RequestMethod.POST)
    public Result editAddress(@Validated AddressUpdateVO addressUpdateVO) {

        //判断该地址是否本人拥有
        if (!getUid().equals(addressService.getMemberIdById(addressUpdateVO.getId()))) {
            return Result.getBusinessException("您没有该地址！请不要查询！", null);
        }

        Address address = new Address();
        BeanUtils.copyProperties(addressUpdateVO, address);

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

        //判断该地址是否本人拥有
        if (!getUid().equals(addressService.getMemberIdById(id))) {
            return Result.getBusinessException("您没有该地址！请不要删除！", null);
        }

        // 判断是否为默认地址
        if (addressService.getAddressById(id).getIsDefault() == 1) {
            return Result.getBusinessException("默认地址不能删除！",null);
        }

        return Result.getSuccessResult(addressService.deleteAddressById(id));
    }
}