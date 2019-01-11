package com.ucar.smadmin.enums;

/**
 * description: TODO
 *
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/11/22  14:42
 */
public enum CommentReplyEnum {
    //商家回复
    ADMIN_RELY(1,"商家回复"),
    //他人回复
    OTHER_REPLY(2,"他人回复");

    /**
     * 数据库状态码
     */
    private final Integer key;

    /**
     * 状态描述
     */
    private final String value;

    CommentReplyEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
