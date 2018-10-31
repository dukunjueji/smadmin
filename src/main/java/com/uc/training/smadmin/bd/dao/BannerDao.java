package com.uc.training.smadmin.bd.dao;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.re.BannerAdminRE;
import com.uc.training.smadmin.bd.re.BannerRE;

import java.util.List;
/**
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:BannerDao数据库操作接口类
 */
public interface BannerDao{
	/**
	 * 获取轮播图(前台)
	 * @return
	 */
	List<BannerRE> getBannerList();

	/**
	 * 获取所有轮播图(后台)
	 * @return
     */
	List<BannerAdminRE> getAllBannerList();

	/**
	 * 更新图片
	 * @param banner
	 * @return
     */
	Integer updateBanner(Banner banner);

	/**
	 * 删除图片
	 * @param id
	 * @return
     */
	Integer deleteBanner(Long id);

	/**
	 * 新增轮播图
	 * @param banner
	 * @return
     */
	Long insertBannerById(Banner banner);

	/**
	 * 增加点击量
	 * @param id
     */
	void insertClick(Long id);
}