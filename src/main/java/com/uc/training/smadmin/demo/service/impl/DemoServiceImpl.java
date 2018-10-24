package com.uc.training.smadmin.demo.service.impl;

import com.uc.training.smadmin.demo.dao.DemoDao;
import com.uc.training.smadmin.demo.model.Demo;
import com.uc.training.smadmin.demo.re.DemoRE;
import com.uc.training.smadmin.demo.service.DemoService;
import com.uc.training.smadmin.demo.vo.DemoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Copyright All Rights Reserved.
 * @Version 1.0 2018/9/25 14:04 by 吴佰川（baichuan.wu@ucarinc.com）创建
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDao demoDao;

    @Override
    public List<Demo> getList() {
        return demoDao.getList();
    }

    @Override
    public List<DemoRE> getDemoList(DemoListVO demoListVO) {
        return demoDao.getDemoList(demoListVO);
    }

    @Override
    public Long getDemoListCount(DemoListVO demoListVO) {
        return demoDao.getDemoListCount(demoListVO);
    }

}
