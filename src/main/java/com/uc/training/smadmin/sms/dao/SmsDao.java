package com.uc.training.smadmin.sms.dao;

import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.vo.SmsListVO;

import java.util.List;

public interface SmsDao {
    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    public List<Sms> getList(SmsListVO smsListVO);

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    public Long querySmsCount(SmsListVO smsListVO);

    /**
     * 查询单条短信记录
     * @param id
     * @return
     */
    public Sms getSms(Long id);
}
