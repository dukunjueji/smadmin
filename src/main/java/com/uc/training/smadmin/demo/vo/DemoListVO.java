package com.uc.training.smadmin.demo.vo;

import com.uc.training.common.bean.PageQuery;
/**
 * 请求列表vo
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/25 17:50
 */
public class DemoListVO extends PageQuery {

    private static final long serialVersionUID = -384549370196291123L;
    private String name;
    private Integer sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
