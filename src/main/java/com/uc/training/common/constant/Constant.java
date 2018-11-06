package com.uc.training.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统级静态变量
 *
 * @author wubc
 * @version V1.0
 * @date 2018年9月26日 上午9:57:26
 */
public class Constant {

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "111111";

    /**
     * 枚举常量
     */
    public static Map<String, Map<String, String>> DATA_MAP = new HashMap<String, Map<String, String>>();

    /**
     * session key
     */
    public static final String SESSION_USER_KEY = "SESSION_MEMBER";

    /**
     * session timeout
     */
    public static final Integer SESSION_USER_TIMEOUT = 60 * 60 * 1000;

    /**
     * 请求头用户id名称
     */
    public static final String REQUEST_HEADER_UID = "uid";

    /**
     * 请求头部名称
     */
    public static final String REQUEST_HEADER_TOKEN = "Authorization";

    /**
     * 请求id
     */
    public static final String REQUEST_ID = "id";

    /**
     * 登录失效状态
     */
    public static int LOGIN_INVALID_STATUS = -3;

    /**
     * 手机验证码
     */
    public static String CODE = "telCode";

    /**
     * 最长用户名长度
     */
    public static int LONGEST_USER_NAME = 32;

    /**
     * 最长用户名长度
     */
    public static int LONGEST_ROLE_NAME = 32;

    /**
     * 最长菜单名长度
     */
    public static int LONGEST_MENU_NAME = 32;
}
