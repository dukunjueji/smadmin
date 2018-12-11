package com.uc.training.common.utils;

/**
 * 模拟jdk1.8的Supplier效果
 * @author HongD
 * @param <T>
 */
public interface Supplier<T> {
    /**
     * 获取结果
     * @return
     */
    T get();
}