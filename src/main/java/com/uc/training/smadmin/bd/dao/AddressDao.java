package com.uc.training.smadmin.bd.dao;
import com.uc.training.smadmin.bd.model.Address;
import com.uc.training.smadmin.bd.re.AddressRE;

import java.util.List;
import java.util.Objects;

/**
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:AddressDao数据库操作接口类
 */
public interface AddressDao{

    /**
     * 通过主键id获取地址
     * @param id
     * @return
     */
    AddressRE  getAddressById(Long id);

    /**
     * 新增地址
     * @param address
     * @return
     */
	Long insertAddress(Address address);

    /**
     * 更新地址
     * @param address
     * @return
     */
	 Integer updateAddressById(Address address);

	/**
	 * 通过会员id查找所有地址
	 * @param memberId
	 * @return
	 */
	List<AddressRE> getAddressByMemberId(Long memberId);

    /**
     * 通过主键id删除地址
     * @param id
     * @return
     */
	Integer deleteAddress(Long id);

	/**
	 * 修改会员默认地址
	 * @param address
	 */
	void updateIsDefault(Address address);

	/**
	 * 获取会员默认地址
	 * @param memberId
	 * @return
     */
    AddressRE getDefaultAddress(Long memberId);

	/**
	 * 根据主键id获取会员id
	 * @param id
	 * @return
	 */
	Long getMemberIdById(Long id);
}