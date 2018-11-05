package com.uc.training.smadmin.utils;

import com.google.common.base.Joiner;
import com.ycc.base.framework.exception.BusinessRuntimeException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * hibernate validation验证工具类
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/11/5 16:18
 */
public class ValidationUtil {
    /**
     * 使用hibernate的注解来进行验证
     * failFast：true  快速失败返回模式    false 普通模式
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * validation验证
     *
     * @param obj 验证对象
     * @return void
     * @version 1.0 2018/11/5 16:26 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    public static <T> void validate(T obj) {
        List<String> list = new ArrayList<>();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            for (ConstraintViolation constraintViolation : constraintViolations) {
                list.add(constraintViolation.getMessage());
            }
            throw new BusinessRuntimeException(Joiner.on(",").skipNulls().join(list));
        }
    }
}
