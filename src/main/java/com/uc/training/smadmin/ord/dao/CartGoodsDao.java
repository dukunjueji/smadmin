package com.uc.training.smadmin.ord.dao;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;

import java.util.List;
/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:CartGoodsDao数据库操作接口类
 */
public interface CartGoodsDao{

	/**
	 * 通过主键来查找
	 * @param id （商品列表id）
	 * @return 购物车商品列表
	 */
	  List<CartGoods>  getCartGoodsById(Long id);

	/**
	 * 查询列表
	 * @return List<CartGoods>(购物车商品列表)
	 */
	  List<CartGoods>  queryCartGoodsList();

	/**
	 * 查找数据总记录数
	 * @return 总记录数
	 */
	 Integer queryCartGoodsCount();

	/**
	 * 保存
	 * @param ordCartGoodsVo (参数对象)
	 * @return 更改条数
	 */
	 int insertCartGoods(OrdCartGoodsVo ordCartGoodsVo);

	/**
	 * 更新
	 * @param record
	 * @return 更改条数
	 */
	 int updateCartGoodsById(CartGoods record);

	/**
	 * 删除
	 * @param ordCartGoodsVo
	 * @return 更改条数
	 */
   int deleteCartGoods(OrdCartGoodsVo ordCartGoodsVo);

	/**
	 * 更新商品数量
	 * @param ordCartGoodsVo
	 * @return 更改条数
	 */
	 int updataCartGoodsNum(OrdCartGoodsVo ordCartGoodsVo);

}