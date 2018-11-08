package com.uc.training.smadmin.sms.service;

import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.vo.SmsListVO;
import com.uc.training.smadmin.sms.vo.SmsVO;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 13:48
 * @Description:
 */
interface SmsService {

    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    List<SmsVO> getList(SmsListVO smsListVO);

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    Long querySmsCount(SmsListVO smsListVO);

    /**
     * 查询单条短信记录
     * @param id
     * @return
     */
    Sms getSms(Long id);

    /**
     * 发送短信
     * @param content
     * @return
     */
    Integer sendSys(String content);

    /**
     * 新增短信
     * @param sms
     * @return
     */
    Long insertSms(Sms sms);
}
