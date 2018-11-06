package com.uc.training.smadmin.bd.dao.impl;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.dao.BannerDao;
import com.uc.training.smadmin.bd.re.AdminBannerListRE;
import com.uc.training.smadmin.bd.re.BannerRE;
import com.uc.training.smadmin.bd.vo.AdminBannerListVO;
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
	public List<BannerRE> getBannerList() {
		return this.queryForList("com.uc.training.smadmin.bd.dao.BannerDao.getBannerList");
	}

	/**
	 * 获取所有轮播图(后台)
	 *
	 * @return
	 */
	@Override
	public List<AdminBannerListRE> getAllBannerList(AdminBannerListVO adminBannerListVO) {
		return this.queryForList("com.uc.training.smadmin.bd.dao.BannerDao.getAllBannerList", adminBannerListVO);
	}

	/**
	 * 更新图片
	 *
	 * @param banner
	 * @return
	 */
	@Override
	public Integer updateBanner(Banner banner) {
		return this.update("com.uc.training.smadmin.bd.dao.BannerDao.updateBanner", banner);
	}

	/**
	 * 删除图片
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteBannerById(Long id) {
		return this.deleteObject("com.uc.training.smadmin.bd.dao.BannerDao.deleteBannerById", id);
	}

	/**
	 * 新增轮播图
	 *
	 * @param banner
	 * @return
	 */
	@Override
	public Long insertBanner(Banner banner) {
		return (Long) this.insert("com.uc.training.smadmin.bd.dao.BannerDao.insertBanner", banner);
	}

	/**
	 * 增加点击量
	 *
	 * @param id
	 */
	@Override
	public Integer insertClick(Long id) {
		return this.update("com.uc.training.smadmin.bd.dao.BannerDao.insertClick", id);
	}

	/**
	 * 后台分页获取banner数量
	 *
	 * @param adminBannerListVO
	 * @return
	 */
	@Override
	public Long getAdminBannerCount(AdminBannerListVO adminBannerListVO) {
		return (Long) this.queryForObject("com.uc.training.smadmin.bd.dao.BannerDao.getAdminBannerCount", adminBannerListVO);
	}

}