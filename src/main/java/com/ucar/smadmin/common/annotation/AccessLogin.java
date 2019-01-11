package com.ucar.smadmin.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
