//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uc.training.base.bd.controller;

import com.ycc.base.framework.exception.BusinessRuntimeException;
import com.zuche.framework.extend.logger.CustomLogger;
import com.zuche.framework.extend.logger.CustomLoggerFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class Result<T> implements Serializable {
    private static final CustomLogger logger = CustomLoggerFactory.getCustomLogger(Result.class);
    private static final long serialVersionUID = -6029022489278479070L;
    public static final int REMOTE_SUCCES_STATUS = 0;
    public static final int REMOTE_BUSINESS_ERROR_STATUS = -1;
    public static final int REMOTE_SERVICE_ERROR_STATUS = -2;
    private int status = 0;
    private String msg;
    private String code;
    private T re;
    private String trace;

    public Result() {
    }

    public static <T> Result<T> getServiceError() {
        return getServiceError("发生未知错误", (String)null);
    }

    public static <T> Result<T> getServiceError(String message, String code) {
        Result<T> result = new Result();
        result.setStatus(-2);
        result.setMsg(message);
        result.setCode(code);
        return result;
    }

    public static <T> Result<T> getServiceError(String message, String code, String trace) {
        Result<T> result = new Result();
        result.setStatus(-2);
        result.setMsg(message);
        result.setCode(code);
        result.setTrace(trace);
        return result;
    }

    public static <T> Result<T> getSuccessResult(T re) {
        Result<T> result = new Result();
        result.setStatus(0);
        result.setRe(re);
        return result;
    }

    public static <T> void setSuccessResult(Result<T> result, T re) {
        result.setStatus(0);
        result.setRe(re);
    }

    public static <T> Result<T> getBusinessException(String msg, String code, String trace) {
        Result<T> result = new Result();
        result.setCode(code);
        result.setStatus(-1);
        result.setMsg(msg);
        result.setTrace(trace);
        return result;
    }

    public static <T> Result<T> getBusinessException(String msg, String code) {
        Result<T> result = new Result();
        result.setCode(code);
        result.setStatus(-1);
        result.setMsg(msg);
        return result;
    }

    public static List copyPropertiesForCollection(Collection source, Class targetClass) {
        List result = new ArrayList();
        if (source != null) {
            Iterator i$ = source.iterator();

            while(i$.hasNext()) {
                Object item = i$.next();

                try {
                    Object targetObject = targetClass.newInstance();
                    BeanUtils.copyProperties(item, targetObject);
                    result.add(targetObject);
                } catch (Exception var6) {
                    throw new RuntimeException(var6);
                }
            }
        }

        return result;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRe() {
        return this.re;
    }

    public void setRe(T re) {
        this.re = re;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTrace() {
        return this.trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public boolean isSuccess() {
        return this.getStatus() == 0;
    }

    public T unboxing() {
        if (this.isSuccess()) {
            return this.getRe();
        } else {
            logger.log(Result.class.getName(), 40, this.getTrace(), (Throwable)null);
            throw new BusinessRuntimeException(this.getMsg(), this.getCode());
        }
    }
}
