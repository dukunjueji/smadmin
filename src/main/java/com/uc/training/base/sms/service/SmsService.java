package com.uc.training.base.sms.service;

import com.uc.training.base.sms.re.SmsRE;
import com.uc.training.base.sms.vo.GenerateSmsVO;
import com.uc.training.base.sms.vo.SmsListVO;
import com.uc.training.base.sms.vo.SmsVO;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 13:48
 * @Description:
 */
public interface SmsService {

    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    List<SmsRE> getList(SmsListVO smsListVO);

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    Long querySmsCount(SmsListVO smsListVO);

    /**
     * 发送短信
     * @param generateSmsVO
     * @param content
     * @return
     */
    Integer sendSys(GenerateSmsVO generateSmsVO, String content);

    /**
     * 新增短信
     * @param sms
     * @return
     */
    Long insertSms(SmsVO sms);
}
