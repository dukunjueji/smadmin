package com.uc.training.base.sms.service.impl;

import com.uc.training.base.sms.re.SmsRE;
import com.uc.training.base.sms.service.SmsService;
import com.uc.training.base.sms.vo.SmsListVO;
import com.uc.training.base.sms.vo.SmsVO;
import com.uc.training.common.enums.SmsStatusEnum;
import com.uc.training.remote.client.BaseClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 13:49
 * @Description:
 */
@Service
public class SmsServiceImpl implements SmsService {

    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    @Override
    public List<SmsRE> getList(SmsListVO smsListVO) {
        return BaseClient.getSmsList(smsListVO);
    }

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    @Override
    public Long querySmsCount(SmsListVO smsListVO) {
        return BaseClient.querySmsCount(smsListVO);
    }

    /**
     * 发送短信
     *
     * @param telephone
     * @param content
     * @return
     */
    @Override
    public Integer sendSys(String telephone, String content) {

        System.out.println(telephone + ": " +content);

        return SmsStatusEnum.SUCCESS.getKey();
    }

    /**
     * 新增短信
     *
     * @param sms
     * @return
     */
    @Override
    public Long insertSms(SmsVO sms) {
        return BaseClient.insertSms(sms);
    }
}
