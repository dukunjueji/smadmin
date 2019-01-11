package com.ucar.smadmin.base.sms.service.impl;

import com.ucar.smapi.base.sms.dto.SmsDTO;
import com.ucar.smapi.base.sms.dto.SmsTemplateDTO;
import com.ucar.smapi.base.sms.re.SmsTemplateRE;
import com.ucar.smadmin.base.sms.service.SmsTemplateService;
import com.ucar.smadmin.base.sms.vo.GenerateSmsVO;
import com.ucar.smadmin.base.sms.vo.SmsTemplateListVO;
import com.ucar.smadmin.base.sms.vo.SmsTemplateVO;
import com.ucar.smadmin.base.sms.vo.SmsVO;
import com.ucar.smadmin.common.mail.dto.MailMessage;
import com.ucar.smadmin.common.utils.TelCodeUtil;
import com.ucar.smadmin.enums.SmsStatusEnum;
import com.ucar.smadmin.enums.SmsTypeEnum;
import com.ucar.smadmin.common.mail.MailService;
import com.ucar.smadmin.common.redis.RedisComponent;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 8:57
 * @Description:
 */
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private MailService mailService;
    @Autowired
    private BaseClient baseClient;

    //使用MailServiceImpl()实现类，不要直接调用发送方法
    //private static MailService mailService = new MailServiceImpl();

    /**
     * 新增短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Long addTemplate(SmsTemplateVO smsTemplate){
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        BeanUtils.copyProperties(smsTemplate, smsTemplateDTO);
        return baseClient.addTemplate(smsTemplateDTO).getRe();
    }

    /**
     * 通过ID删除短信模板
     * @param id
     * @return
     */
    @Override
    public Integer deleteTemplateById(Long id){
        return baseClient.deleteTemplateById(id).getRe();
    }

    /**
     * 修改短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Integer modifyTemplate(SmsTemplateVO smsTemplate){
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        BeanUtils.copyProperties(smsTemplate, smsTemplateDTO);
        return baseClient.modifyTemplate(smsTemplateDTO).getRe();
    }

    /**
     * 通过ID获取指定的短信模板
     * @param id
     * @return
     */
    @Override
    public SmsTemplateRE getTemplateById(Long id){
        return baseClient.getTemplateById(id).getRe();
    }

    /**
     * 获取短信模板列表
     * @return
     */
    @Override
    public List<SmsTemplateRE> getTemplateList(SmsTemplateListVO smsTemplateListVO){
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        smsTemplateDTO.setCode(smsTemplateListVO.getCode());
        smsTemplateDTO.setType(smsTemplateListVO.getType());
        smsTemplateDTO.setOffset(smsTemplateListVO.getOffset());
        smsTemplateDTO.setPageSize(smsTemplateListVO.getPageSize());
        return baseClient.getTemplateList(smsTemplateDTO).getRe();
    }

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    @Override
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO) {
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        BeanUtils.copyProperties(smsTemplateListVO, smsTemplateDTO);
        return baseClient.getTemplateListCount(smsTemplateDTO).getRe();
    }

    /**
     * 通过编码获取短信模板
     * @param code
     * @return
     */
    @Override
    public SmsTemplateRE getByCode(String code) {
        SmsTemplateListVO sms = new SmsTemplateListVO();
        sms.setCode(code);
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        smsTemplateDTO.setCode(sms.getCode());
        smsTemplateDTO.setType(sms.getType());
        smsTemplateDTO.setOffset(sms.getOffset());
        smsTemplateDTO.setPageSize(sms.getPageSize());
        List<SmsTemplateRE> list = baseClient.getTemplateList(smsTemplateDTO).getRe();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据ID列表批量删除
     * @param ids
     * @return
     */
    @Override
    public Integer batchDeleteById(List<Long> ids){
        return baseClient.batchDeleteSmsTempleById(ids).getRe();
    }

    /**
     * 生成短信
     *
     * @param generateSmsVO
     * @return
     */
    @Override
    public Integer generateSms(GenerateSmsVO generateSmsVO) {

        if (SmsTypeEnum.CHANGE_PASSWORD.getCode().equals(generateSmsVO.getCode()) ||
                SmsTypeEnum.FORGET_PASSWORD.getCode().equals(generateSmsVO.getCode()) ||
                SmsTypeEnum.REGISTER.getCode().equals(generateSmsVO.getCode())) {

            // redis
            RedisComponent redis = new RedisComponent();
            generateSmsVO.setMessage(TelCodeUtil.createCode());
            //手机号 验证码
            redis.set(generateSmsVO.getTelephone(), generateSmsVO.getMessage(),60L,TimeUnit.SECONDS );
        }

        if (generateSmsVO.getRechargeStatus() != null && generateSmsVO.getRechargeStatus() == 0) {
            generateSmsVO.setCode(SmsTypeEnum.RECHARGE_FAIL.getCode());
        }
        SmsTemplateListVO smsTemplateListVO = new SmsTemplateListVO();
        smsTemplateListVO.setCode(generateSmsVO.getCode());
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        smsTemplateDTO.setCode(smsTemplateListVO.getCode());
        smsTemplateDTO.setType(smsTemplateListVO.getType());
        smsTemplateDTO.setOffset(smsTemplateListVO.getOffset());
        smsTemplateDTO.setPageSize(smsTemplateListVO.getPageSize());
        List<SmsTemplateRE> smsTemplateRE = baseClient.getTemplateList(smsTemplateDTO).getRe();
        //查找短信模板存在
        if (CollectionUtils.isEmpty(smsTemplateRE) || smsTemplateRE.size() != 1) {
            return SmsStatusEnum.TEMPLATE_NOT_EXIST.getKey();
        }
        //获取短信内容
        smsTemplateDTO.setCode(generateSmsVO.getCode());
        smsTemplateDTO.setMessage(generateSmsVO.getMessage());
        String content = baseClient.generateSms(smsTemplateDTO).getRe();
        //邮件标题
        generateSmsVO.setEmailTitle(smsTemplateRE.get(0).getTitle());
        //发送邮件
        if (generateSmsVO.getEmil() != null) {
            MailMessage mailMessage = new MailMessage();
            mailMessage.setMailAddress(generateSmsVO.getEmil());
            mailMessage.setTitle(generateSmsVO.getEmailTitle());
            mailMessage.setContent(content);
            mailService.sendSimpleMail(mailMessage);
        }
        System.out.println(generateSmsVO.getTelephone() + ": " +content);


        SmsVO sms = new SmsVO();
        sms.setType(generateSmsVO.getType());
        sms.setTelephone(generateSmsVO.getTelephone());
        sms.setStatus(SmsStatusEnum.SUCCESS.getKey());
        sms.setContent(content);
        // 新增短信记录
        SmsDTO smsDTO = new SmsDTO();
        BeanUtils.copyProperties(sms, smsDTO);
        baseClient.insertSms(smsDTO);

        return SmsStatusEnum.SUCCESS.getKey();
    }

}
