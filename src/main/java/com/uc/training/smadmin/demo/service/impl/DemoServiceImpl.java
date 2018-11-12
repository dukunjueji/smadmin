package com.uc.training.smadmin.demo.service.impl;

import com.uc.training.smadmin.demo.dao.DemoDao;
import com.uc.training.smadmin.demo.re.DemoRE;
import com.uc.training.smadmin.demo.model.Demo;
import com.uc.training.smadmin.demo.service.DemoService;
import com.uc.training.smadmin.demo.vo.DemoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 例子service实现类
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/25 17:51
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
