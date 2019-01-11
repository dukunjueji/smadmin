package com.uc.training.base.bd.vo;

import com.smgoods.common.base.model.BaseDomain;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/25
 * 说明：
 */
public class MessageVO extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 4049801712145458173L;

    /**
     * 消息id
     */
    @NotNull(message = "消息id不能为空")
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 消息状态
     */
    @NotNull(message = "消息状态不能为空")
    private Integer isRead;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 消息内容
     */
    private String content;

    @Override
    public String toString() {
        return "MessageVO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", isRead=" + isRead +
                ", createTime='" + createTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

}
