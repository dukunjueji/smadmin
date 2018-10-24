package com.uc.training.common.handler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.ycc.base.common.Result;
import com.ycc.base.framework.exception.BusinessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.Map.Entry;

/**
 * Description: Controller异常拦截器.
 * All Rights Reserved.
 * @version 1.0 2017-12-20 14:06:29 by 刘庆魁（qk.liu@zuche.com）
 */
public class YccHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger staticLogger = LoggerFactory.getLogger(com.ycc.base.framework.web.spring.YccHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        Logger logger = staticLogger;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger = LoggerFactory.getLogger(handlerMethod.getBean().getClass());
            if (logger.isDebugEnabled()) {
                logger.debug("Controller返回结果包装：handler={}", handler);
            }
        }

        Result<Object> result = null;
        if (ex instanceof BusinessRuntimeException) {
            result = Result.getBusinessException(ex.getMessage(), null);
            result.setCode(((BusinessRuntimeException) ex).getCode());
            if (logger.isDebugEnabled()) {
                logger.debug("Controller返回结果包装：result={}", JSON.toJSONString(result));
            }
        } else  if (ex instanceof BindException) {
            BindingResult bindResult = ((BindException) ex).getBindingResult();
            List<String> list = new ArrayList<String>();
            if (bindResult != null) {
                for (ObjectError objectError : bindResult.getAllErrors()) {
                    list.add(objectError.getDefaultMessage());
                }
            }
            result = Result.getBusinessException(Joiner.on(",").skipNulls().join(list), null);
        } else {
            result = Result.getServiceError(ex.getMessage(), null);

            //非业务异常,log
            String uri = request.getRequestURI();
            @SuppressWarnings("unchecked")
            String params = paramMapToString(request.getParameterMap());
            logger.error("Controller异常,uri:[{}],params:[{}]", uri, params, ex);
        }

        if (isAjax(request)) {
            request.setAttribute("contentType", "application/json;charset=UTF-8");
        }

        //兼容架构, 在sys/errorpage中输出结果
        request.setAttribute("re", JSON.toJSONString(result));
        return null;
    }

    /**
     * 判断ajax.
     */
    private boolean isAjax(HttpServletRequest request) {
        return request.getHeader("accept").contains("application/json")
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

    /**
     * 参数map转化为字符串, 输出log.
     */
    private String paramMapToString(Map<String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Set<Entry<String, String[]>> es = map.entrySet();
        int i = 0;
        for (Entry<String, String[]> e : es) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(e.getKey()).append(':');
            String[] values = e.getValue();
            if (values.length == 1) {
                sb.append(values[0]);
            } else {
                sb.append(Arrays.toString(values));
            }

            i++;
        }
        sb.append('}');
        return sb.toString();
    }
}
