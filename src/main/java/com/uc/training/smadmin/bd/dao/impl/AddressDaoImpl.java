package com.uc.training.smadmin.bd.dao.impl;
import com.uc.training.smadmin.bd.model.Address;
import com.uc.training.smadmin.bd.dao.AddressDao;
import com.uc.training.smadmin.bd.re.AddressRE;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:AddressDao数据库操作接口类
 */
@Repository
public class AddressDaoImpl extends CarIsIbatisDaoImpl implements AddressDao {


    /**
     * 通过主键id获取地址
     * @param id
     * @return
     */
    @Override
    public AddressRE getAddressById(Long id) {
        return (AddressRE) this.queryForObject("com.uc.training.smadmin.bd.dao.AddressDao.getAddressById", id);
    }

    /**
     * 新增地址
     * @param address
     * @return
     */
    @Override
    public Long insertAddress(Address address) {

        return (Long) this.insert("com.uc.training.smadmin.bd.dao.AddressDao.insertAddress", address);
    }

    /**
     * 更新地址
     * @param address
     * @return
     */
    @Override
    public Integer updateAddressById(Address address) {
        return this.update("com.uc.training.smadmin.bd.dao.AddressDao.updateAddressById", address);
    }

    /**
     * 通过会员id查找所有地址
     * @param memberId
     * @return
     */
    @Override
    public List<AddressRE> getAddressByMemberId(Long memberId) {
        return this.queryForList("com.uc.training.smadmin.bd.dao.AddressDao.getAddressByMemberId", memberId);
    }

    /**
     * 通过主键id删除地址
     * @param id
     * @return
     */
    @Override
    public Integer deleteAddress(Long id) {
        return this.deleteObject("com.uc.training.smadmin.bd.dao.AddressDao.deleteAddress", id);
    }

    /**
     * 修改会员默认地址
     * @param address
     */
    @Override
    public void updateIsDefault(Address address) {
        this.update("com.uc.training.smadmin.bd.dao.AddressDao.cancelIsDefault",address);
    }

    /**
     * 获取会员默认地址
     * @param memberId
     * @return
     */
    @Override
    public AddressRE getDefaultAddress(Long memberId) {
        return (AddressRE) this.queryForObject("com.uc.training.smadmin.bd.dao.AddressDao.getDefaultAddress", memberId);
    }

    /**
     * 根据主键id获取会员id
     *
     * @param id
     * @return
     */
    @Override
    public Long getMemberIdById(Long id) {
        return (Long) this.queryForObject("com.uc.training.smadmin.bd.dao.AddressDao.getMemberIdById", id);
    }
}