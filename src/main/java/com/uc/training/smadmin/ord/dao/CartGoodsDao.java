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
	 */
	 public List<CartGoods>  getCartGoodsById(Long id);

	/**
	 * 查询列表
	 */
	 public List<CartGoods>  queryCartGoodsList();

	/**
	 * 查找数据总记录数
	 */
	public Integer queryCartGoodsCount();

	/**
	 * 保存
	 */
	public Long insertCartGoods(OrdCartGoodsVo ordCartGoodsVo);

	/**
	 * 更新
	 */
	public int updateCartGoodsById(CartGoods record);

	/**
	 * 删除
	 * @param ordCartGoodsVo
	 * @return
	 */
  public int deleteCartGoods(OrdCartGoodsVo ordCartGoodsVo);

	/**
	 * 更新商品数量
	 * @param ordCartGoodsVo
	 * @return
	 */
	public int updataCartGoodsNum(OrdCartGoodsVo ordCartGoodsVo);

}