package com.uc.training.smadmin.bd.dao.impl;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.dao.BannerDao;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:BannerDao数据库操作接口类
 */
@Repository
public class BannerDaoImpl extends CarIsIbatisDaoImpl implements BannerDao {
	/**
	 * 获取轮播图
	 * @return
	 */
	@Override
	public List<Banner> getBannerList() {
		return this.queryForList("com.uc.training.smadmin.bd.dao.BannerDao.getBannerList");
	}

}