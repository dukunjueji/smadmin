package com.uc.training.common.bean;
import java.io.Serializable;
/**
 * JsonResult对象包含协议属性：
 * t:业务正常返回的消息体,每个服务使用专用Re对象，不可复用。属性命名规则同数据库命名。支持数据结构包装，例如：List<Re>，Set<Re>。
 * code:业务语义状态码，用于做业务语义路由，和msg取其一设值即可。
 * msg:描述信息
 *
 * @copyright www.ucarinc.com All Rights Reserved.
 * @version 1.0 2018/10/17 13:23 by 吴佰川（baichuan.wu@ucarinc.com）创建
 */
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = -1520951807292593403L;

    /**
     * 成功
     */
    private final static int SUCCES_CODE = 0;

    /**
     * 失败
     */
    private final static int FAILURE_CODE = -1;

    /**
     * 异常
     */
    private final static int ERROR_CODE = -2;

    /**
     * 登录失效
     */
    private final static int INVALID_CODE = -3;
    /**
     * 默认成功消息
     */
    private static final String MESSAGE_SUCCESS = "成功。";
    /**
     * 默认失败消息
     */
    private static final String MESSAGE_FAILURE = "失败。";
    /**
     * 默认错误消息
     */
    private static final String MESSAGE_ERROR = "错误。";

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 业务语义状态码，用于做业务语义路由，和msg取其一设值即可
     */
    private Integer code;

    /**
     * 业务正常返回的消息体,每个服务使用专用Re对象，不可复用
     */
    private T data;

    public <T> JsonResult() {

    }

    /**
     * 请求成功
     *
     * @return JsonResult
     */
    public static <T> JsonResult<T> success() {
        JsonResult<T> result = new JsonResult<T>();
        result.setMsg(MESSAGE_SUCCESS);
        result.setCode(SUCCES_CODE);
        return result;
    }

    /**
     * 请求成功返回数据
     *
     * @return JsonResult
     */
    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> result = new JsonResult<T>();
        result.setMsg(MESSAGE_SUCCESS);
        result.setCode(SUCCES_CODE);
        result.setData(data);
        return result;
    }

    /**
     * 请求失败
     *
     * @return JsonResult
     */
    public static <T> JsonResult<T> failure() {
        JsonResult<T> result = new JsonResult<T>();
        result.setMsg(MESSAGE_FAILURE);
        result.setCode(FAILURE_CODE);
        return result;
    }

    /**
     * 请求失败
     *
     * @param message
     * @return JsonResult
     */
    public static <T> JsonResult<T> failure(String message) {
        JsonResult<T> result = new JsonResult<T>();
        result.setMsg(message);
        result.setCode(FAILURE_CODE);
        return result;
    }

    /**
     * 请求异常
     *
     * @return JsonResult
     */
    public static <T> JsonResult<T> error() {
        JsonResult<T> result = new JsonResult<T>();
        result.setMsg(MESSAGE_ERROR);
        result.setCode(ERROR_CODE);
        return result;
    }

    /**
     * 请求异常
     *
     * @param message
     * @return JsonResult
     */
    public static <T> JsonResult<T> error(String message) {
        JsonResult<T> result = new JsonResult<T>();
        result.setMsg(message);
        result.setCode(ERROR_CODE);
        return result;
    }

    /**
     * 登录失效
     *
     * @param message
     * @return JsonResult
     */
    public static <T> JsonResult<T> invalid(String message) {
        JsonResult<T> result = new JsonResult<T>();
        result.setMsg(message);
        result.setCode(INVALID_CODE);
        return result;
    }


    /**
     * 请求是否成功，判断status值是否为{@link #SUCCES_CODE}
     *
     * @return code
     */
    public boolean isSuccess() {
        return getCode() == JsonResult.SUCCES_CODE;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JsonResult{")
                .append("msg='").append(msg).append('\'')
                .append(", code='").append(code).append('\'')
                .append(", data=").append(data)
                .append('}');
        return sb.toString();
    }
}
