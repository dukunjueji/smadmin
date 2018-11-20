package com.uc.training.smadmin.ord.dao.impl;

import com.uc.training.smadmin.ord.dao.CartGoodsDao;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdGoodsVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:CartGoodsDao数据库操作接口类
 */
@Repository
public class CartGoodsDaoImpl extends CarIsIbatisDaoImpl implements CartGoodsDao {

	@Override
	public List<CartGoods> getCartGoodsById(Long id) {
		return (List<CartGoods>) this.queryForList("com.uc.training.smadmin.ord.dao.CartGoodsDao.getCartGoodsById", id);
	}

	@Override
	public List<CartGoods> queryCartGoodsList() {
		return this.queryForList("com.uc.training.smadmin.ord.dao.CartGoodsDao.queryCartGoodsList");
	}

	@Override
	public Integer queryCartGoodsCount(Long memberId) {
		return (Integer) this.queryForObject("com.uc.training.smadmin.ord.dao.CartGoodsDao.queryCartGoodsCount",memberId);
	}

	@Override
	public void insertCartGoods(OrdCartGoodsVO ordCartGoodsVO) {
		this.insert("com.uc.training.smadmin.ord.dao.CartGoodsDao.insertCartGoods", ordCartGoodsVO);
	}

	@Override
	public int updateCartGoodsById(CartGoods record) {
		return this.update("com.uc.training.smadmin.ord.dao.CartGoodsDao.updateCartGoodsById", record);
	}

	@Override
	public int deleteCartGoods(OrdCartGoodsVO ordCartGoodsVO) {
		return this.deleteObject("com.uc.training.smadmin.ord.dao.CartGoodsDao.delCartGoods", ordCartGoodsVO);
	}

	@Override
	public int updataCartGoodsNum(OrdCartGoodsVO ordCartGoodsVO) {
		return this.update("com.uc.training.smadmin.ord.dao.CartGoodsDao.upCartGoodsNum", ordCartGoodsVO);
	}

	@Override
	public List<CartGoods> getCarGoodsByIds(OrdGoodsVO ordGoodsVO) {
		return this.queryForList("com.uc.training.smadmin.ord.dao.CartGoodsDao.checkCartGoodsList", ordGoodsVO);
	}
}