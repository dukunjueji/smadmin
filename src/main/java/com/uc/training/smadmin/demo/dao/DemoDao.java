package com.uc.training.smadmin.demo.dao;

import com.uc.training.smadmin.demo.re.DemoRE;
import com.uc.training.smadmin.demo.model.Demo;
import com.uc.training.smadmin.demo.vo.DemoListVO;

import java.util.List;

/**
 * @Description
 * @Copyright All Rights Reserved.
 * @Version 1.0 2018/9/25 14:01 by 吴佰川（baichuan.wu@ucarinc.com）创建
 */
public interface DemoDao {
    /**
     * 获取所有列表
     *
     * @param
     * @return java.util.List<com.uc.training.smadmin.demo.model.Demo>
     * @version 1.0 2018/10/17 12:21 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<Demo> getList();

    /**
     * 查询列表
     *
     * @param demoListVO 查询参数vo
     * @return java.util.List<com.uc.training.smadmin.demo.re.DemoRE>
     * @version 1.0 2018/10/17 12:21 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    List<DemoRE> getDemoList(DemoListVO demoListVO);

    /**
     * 查询列表总记录数
     *
     * @param demoListVO 查询参数vo
     * @return java.lang.Long
     * @version 1.0 2018/10/17 12:22 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    Long getDemoListCount(DemoListVO demoListVO);
}
