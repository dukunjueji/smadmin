package com.uc.training.smadmin.demo.dao.impl;

import com.uc.training.smadmin.demo.re.DemoRE;
import com.uc.training.smadmin.demo.vo.DemoListVO;
import com.uc.training.smadmin.demo.dao.DemoDao;
import com.uc.training.smadmin.demo.model.Demo;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/9/25 14:02 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @description
 * @copyright All Rights Reserved.
 */
@Repository
public class DemoDaoImpl extends CarIsIbatisDaoImpl implements DemoDao {
    @Override
    public List<Demo> getList() {
        return this.queryForList("DemoDao.getList");
    }

    @Override
    public List<DemoRE> getDemoList(DemoListVO demoListVO) {
        return this.queryForList("DemoDao.getDemoList", demoListVO);
    }

    @Override
    public Long getDemoListCount(DemoListVO demoListVO) {
        return (Long) this.queryForObject("DemoDao.getDemoListCount", demoListVO);
    }
}
