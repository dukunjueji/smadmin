package com.uc.training.base.sms.service.impl;

import com.uc.training.base.sms.dto.SmsDTO;
import com.uc.training.base.sms.re.SmsRE;
import com.uc.training.base.sms.service.SmsService;
import com.uc.training.base.sms.vo.GenerateSmsVO;
import com.uc.training.base.sms.vo.SmsListVO;
import com.uc.training.base.sms.vo.SmsVO;
import com.uc.training.common.enums.SmsStatusEnum;
import com.uc.training.remote.remoteclient.BaseClient;
import org.springframework.beans.BeanUtils;
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
private BaseClient baseClient;
    //使用MailServiceImpl()实现类，不要直接调用发送方法
    //private static MailService mailService = new MailServiceImpl();

    /**
     * 查询短信列表
     * @param smsListVO
     * @return
     */
    @Override
    public List<SmsRE> getList(SmsListVO smsListVO) {
        SmsDTO smsDTO = new SmsDTO();
        BeanUtils.copyProperties(smsListVO, smsDTO);
        return baseClient.getSmsList(smsDTO).getRe();
    }

    /**
     * 查询记录总数
     * @param smsListVO
     * @return
     */
    @Override
    public Long querySmsCount(SmsListVO smsListVO) {
        SmsDTO smsDTO = new SmsDTO();
        BeanUtils.copyProperties(smsListVO, smsDTO);
        return baseClient.querySmsCount(smsDTO).getRe();
    }

    /**
     * 发送短信
     *
     * @param generateSmsVO
     * @param content
     * @return
     */
    @Override
    public Integer sendSys(GenerateSmsVO generateSmsVO, String content) {
        //发送邮件
        /*if (generateSmsVO.getEmil() != null) {
            MailMessage mailMessage = new MailMessage();
            mailMessage.setMailAddress(generateSmsVO.getEmil());//邮件地址
            mailMessage.setContent(content); //邮件内容
            mailMessage.setTitle(generateSmsVO.getEmailTitle()); //邮件标题
            mailMessage.setMetaType("text"); //邮件类型(html或者text)
            mailMessage.setSaveToDB(false); //是否保存到数据库
            mailMessage.setChannel(2); //通道(专车：1通道；租车：1通道；买买车：2通道)
            mailService.sendMessage(mailMessage);
        }*/

        System.out.println(generateSmsVO.getTelephone() + ": " +content);

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
        SmsDTO smsDTO = new SmsDTO();
        BeanUtils.copyProperties(sms, smsDTO);
        return baseClient.insertSms(smsDTO).getRe();
    }
}
