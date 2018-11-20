package com.uc.training.smadmin.ord.dao;

import com.uc.training.smadmin.ord.re.CartGoodsRE;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdGoodsVO;

import java.util.List;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:CartGoodsDao数据库操作接口类
 */
public interface CartGoodsDao {

	/**
	 * 通过主键来查找
	 *
	 * @param id （商品列表id）
	 * @return 购物车商品列表
	 */
	List<CartGoodsRE> getCartGoodsById(Long id);

	/**
	 * 根据用户ID和购物车商品表ID获取购物车商品信息
	 *
	 * @param ordGoodsVO
	 * @return
	 */
	List<CartGoodsRE> getCarGoodsByIds(OrdGoodsVO ordGoodsVO);

	/**
	 * 查询列表
	 *
	 * @return List<CartGoodsRE>(购物车商品列表)
	 */
	List<com.uc.training.smadmin.ord.re.CartGoodsRE> queryCartGoodsList();

	/**
	 * 查找数据总记录数
	 *
	 * @return 总记录数
	 */
	Integer queryCartGoodsCount(Long memberId);

	/**
	 * 保存
	 *
	 * @param ordCartGoodsVO (参数对象)
	 * @return 更改条数
	 */
	void insertCartGoods(OrdCartGoodsVO ordCartGoodsVO);

	/**
	 * 更新
	 *
	 * @param record
	 * @return 更改条数
	 */
	int updateCartGoodsById(CartGoodsRE record);

	/**
	 * 删除
	 *
	 * @param ordCartGoodsVO
	 * @return 更改条数
	 */
	int deleteCartGoods(OrdCartGoodsVO ordCartGoodsVO);

	/**
	 * 更新商品数量
	 *
	 * @param ordCartGoodsVO
	 * @return 更改条数
	 */
	int updataCartGoodsNum(OrdCartGoodsVO ordCartGoodsVO);

}