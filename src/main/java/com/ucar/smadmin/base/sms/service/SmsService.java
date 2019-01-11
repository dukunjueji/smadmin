package com.ucar.smadmin.base.sms.service;

import com.ucar.smapi.base.sms.re.SmsRE;
import com.ucar.smadmin.base.sms.vo.GenerateSmsVO;
import com.ucar.smadmin.base.sms.vo.SmsListVO;
import com.ucar.smadmin.base.sms.vo.SmsVO;

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
