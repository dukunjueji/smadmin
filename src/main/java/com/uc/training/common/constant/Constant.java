package com.uc.training.common.constant;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * 系统级静态变量
 *
 * @author wubc
 * @version V1.0
 * @date 2018年9月26日 上午9:57:26
 */
public final class Constant {
    private Constant() {
    }

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "111111";

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

    public static final Integer LONGEST_ROLE_NAME = 22;
}
