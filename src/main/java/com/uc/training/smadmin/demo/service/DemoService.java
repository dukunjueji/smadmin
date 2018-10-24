package com.uc.training.smadmin.demo.service;


import com.uc.training.smadmin.demo.re.DemoRE;
import com.uc.training.smadmin.demo.vo.DemoListVO;
import com.uc.training.smadmin.demo.model.Demo;

import java.util.List;

public interface DemoService {

    /**
     * 获取demo列表
     *
     * @param
     * @return java.util.List<Demo>
     * @version 1.0 2018/10/17 12:30 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<Demo> getList();

    /**
     * 获取api dmeof列表
     *
     * @param demoListVO
     * @return java.util.List<com.uc.training.smadmin.demo.dto.DemoRE>
     * @version 1.0 2018/10/17 12:31 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<DemoRE> getDemoList(DemoListVO demoListVO);

    /**
     * 获取api dmeo列表总记录
     *
     * @param demoListVO
     * @return java.lang.Long
     * @version 1.0 2018/10/17 12:31 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    Long getDemoListCount(DemoListVO demoListVO);
}
