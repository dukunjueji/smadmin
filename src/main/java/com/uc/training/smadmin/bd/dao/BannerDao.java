package com.uc.training.smadmin.bd.dao;
import com.uc.training.smadmin.bd.model.Banner;import java.util.List;
/**
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:BannerDao数据库操作接口类
 */
public interface BannerDao{
	/**
	 * 获取轮播图
	 * @return
	 */
	List<Banner> getBannerList();
}