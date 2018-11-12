package com.uc.training.smadmin.sms.service.impl;

import com.uc.training.common.enums.SmsStatusEnum;
import com.uc.training.smadmin.sms.dao.SmsDao;
import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.service.SmsService;
import com.uc.training.smadmin.sms.vo.SmsListVO;
import com.uc.training.smadmin.sms.vo.SmsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 13:49
 * @Description:
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SmsDao smsDao;

    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    @Override
    public List<SmsVO> getList(SmsListVO smsListVO) {
        return smsDao.getList(smsListVO);
    }

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    @Override
    public Long querySmsCount(SmsListVO smsListVO) {
        return smsDao.querySmsCount(smsListVO);
    }

    /**
     * 查询单条短信记录
     * @param id
     * @return
     */
    @Override
    public Sms getSms(Long id) {
        return smsDao.getSms(id);
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
    public Long insertSms(Sms sms) {
        return smsDao.insertSms(sms);
    }
}
