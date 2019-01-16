package com.ucar.smadmin.common.base.controller;

import com.ucar.smadmin.common.constant.Constant;
import com.ucar.smapi.common.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * controller返回结果基类.
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0 2018/10/25 17:47
 * @date 2018/10/25 17:47
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String DEFAULT_SPLIT = ",";
    private static final String DEFAULT_UNKNOWN = "unknown";
    private static final String DEFAULT_LOCALHOST = "127.0.0.1";
    /**
     * 根据token获取用户id
     *
     * @return java.lang.Long
     */
    public Long getUid() {
        Object obj = getRequest().getHeader(Constant.REQUEST_HEADER_UID);
        if (obj != null) {
            String uid = String.valueOf(obj);
            getRequest().setAttribute(Constant.REQUEST_HEADER_UID,uid);
            return Long.valueOf(uid);
        } else {
            Object obj1 = getRequest().getAttribute(Constant.REQUEST_HEADER_UID);
            if(obj1 != null){
                String uid = String.valueOf(obj);
                return Long.valueOf(uid);
            }
            return null;
        }
    }

    /**
     * 根据token获取用户id
     *
     * @return java.lang.Long
     */
    public Long getUid(HttpServletRequest request) {
        Object obj = request.getAttribute(Constant.REQUEST_HEADER_UID);
        if (obj != null) {
            String uid = String.valueOf(obj);
            return Long.valueOf(uid);
        } else {
            return null;
        }
    }
    public void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public Object getSessionAttribute(String key) {
        return getSession().getAttribute(key);
    }

    public String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession() {
        return this.getRequest().getSession();
    }

    /**
     * 获取当前完整请求地址
     *
     * @return 请求地址
     *
     */
    protected String getRequestUrl() {
        HttpServletRequest request = getRequest();
        StringBuffer url = new StringBuffer(request.getScheme());
        // 请求协议 http,https
        url.append("://");
        // 请求服务器
        url.append(request.getHeader("host"));
        // 工程名
        url.append(request.getRequestURI());
        if (request.getQueryString() != null) {
            // 请求参数
            url.append("?").append(request.getQueryString());
        }
        return url.toString();
    }

    /**
     * 设置变量到session中
     *
     * @param name
     * @param value
     */
    protected void setAttribute(String name, Object value) {
        getSession().setAttribute(name, value);
    }

    /**
     * 获取request所有参数
     *
     * @return
     */
    protected Map<String, Object> getRequestParams() {
        HttpServletRequest request = getRequest();
        Map<String, Object> params = new TreeMap<String, Object>();
        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String p = e.nextElement();
            params.put(p, request.getParameter(p));
        }
        return params;
    }

    /**
     * 获取请求属性封装为Map类型
     *
     * @return
     */
    protected Map<String, Object> getRequestMapSingle() {
        Map<String, Object> params = new TreeMap<String, Object>();
        Map<String, String[]> map = getRequest().getParameterMap();
        for (Object o : map.keySet()) {
            String key = (String) o;
            params.put(key, ((String[]) map.get(key))[0]);
        }
        return params;
    }

    /**
     * 获取参数map
     *
     * @param names
     * @return
     */
    protected Map<String, String> loadArguments(String[] names) {
        Map<String, String> map = new TreeMap<String, String>();
        int len = names.length;
        for (int i = 0; i < len; i++) {
            if (getRequestParam(names[i]) != null) {
                map.put(names[i], getRequestParam(names[i]).trim());
            } else {
                map.put(names[i], getRequestParam(names[i]));
            }
        }
        return map;
    }

    /**
     * 获取参数值
     *
     * @param paramName
     * @return
     */
    protected String getRequestParam(String paramName) {
        return getRequest().getParameter(paramName);
    }


    /**
     * 获取基于应用程序的url绝对路径
     *
     * @param url
     * @return
     */
    public final String getAppbaseUrl(String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return getRequest().getContextPath() + url;
    }

    /**
     * 获取本机IP
     *
     * @return
     */
    protected String getLocalhostIp() {
        String ip = null;
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> interfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : interfaceAddress) {
                    InetAddress inetAddress = add.getAddress();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            logger.error(e.getMessage());
        }
        return ip;
    }

    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request){
        logger.error("请求："+getRequestUrl()+"出错了！");
        logger.error(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Result result = Result.getServiceError("服务器异常,请稍后再试！", null);
        return new ResponseEntity<Object>(result,headers, HttpStatus.OK);
    }


}
