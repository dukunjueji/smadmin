package com.uc.training.common.annotation;

import java.lang.annotation.*;

/**
 * 登录自定义注解
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/9/27 10:03 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLogin {
    boolean required() default true;
}
