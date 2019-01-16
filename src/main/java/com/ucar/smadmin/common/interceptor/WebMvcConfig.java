package com.ucar.smadmin.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 * @author DK
 */
@Configuration
public class WebMvcConfig  {
    //spring boot 拦截器需要实现的类暂时移除
//implements WebMvcConfigurer
    /**
     * 注册拦截器
     */
   /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        *//**
         * addPathPattern后跟拦截地址，excludePathPatterns后跟排除拦截地址
         *//*
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/login/index")
            .excludePathPatterns("/api/member/memberLogin.do_")
            .excludePathPatterns("/api/order/getCommentGoodsCount.do_");
    }*/
}