package com.uc.training.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.bean.AccessToken;
import com.uc.training.common.constant.Constant;
import com.uc.training.smadmin.utils.TokenUtil;
import com.ycc.base.common.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 对所有的api请求进行拦截，验证请求头中是否携带合法且未过期的 token
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/26 8:36
 */
public class TokenInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            //检查是否有AccessLogin注释，有则跳过认证
            AccessLogin accessLogin = ((HandlerMethod) handler).getMethodAnnotation(AccessLogin.class);
            // 没有注释不验证
            if (accessLogin != null && !accessLogin.required()) {
                return true;
            } else {
                // 从 http 请求头中取出 token及uid
                String token = request.getHeader(Constant.REQUEST_HEADER_TOKEN);
                //token不存在
                if (!StringUtils.isEmpty(token)) {
                    AccessToken accessToken = TokenUtil.verify(token);
                    if (accessToken.isVerify()) {
                        boolean isExpire = accessToken.isExpire();
                        if (isExpire) {
                            responseMsg(response, "token invalid");
                            return false;
                        }
                        request.setAttribute(Constant.REQUEST_HEADER_UID, accessToken.getId());
                        return true;
                    } else {
                        responseMsg(response, "token wrong");
                        return false;
                    }
                } else {
                    responseMsg(response, "token does not exist");
                    return false;
                }
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 请求不通过，返回错误信息给客户端
     *
     * @param response 返回response
     * @param msg   返回信息
     * @return void
     */
    private void responseMsg(HttpServletResponse response, String msg) throws IOException {
        Result result = new Result();
        result.setStatus(-3);
        result.setMsg(msg);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(result);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
