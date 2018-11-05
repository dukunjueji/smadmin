package com.uc.training.smadmin.sms.service.impl;

import com.uc.training.common.enums.SmsTypeEnum;
import com.uc.training.smadmin.redis.RedisConfigEnum;
import com.uc.training.smadmin.sms.dao.SmsTemplateDao;
import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.service.SmsService;
import com.uc.training.smadmin.sms.service.SmsTemplateService;
import com.uc.training.smadmin.sms.vo.GenerateSmsVO;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;
import com.uc.training.smadmin.sms.vo.SmsTemplateVO;
import com.uc.training.smadmin.utils.TelCodeUtil;
import com.ycc.tools.middleware.redis.RedisCacheUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 8:57
 * @Description:
 */
@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private SmsTemplateDao smsTemplateDao;

    @Autowired
    private SmsService smsService;

    /**
     * 新增短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Long addTemplate(SmsTemplateVO smsTemplate){
        return smsTemplateDao.addTemplate(smsTemplate);
    }

    /**
     * 通过ID删除短信模板
     * @param id
     * @return
     */
    @Override
    public Integer deleteTemplateById(Long id){
        return smsTemplateDao.deleteTemplate(id);
    }

    /**
     * 修改短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Integer modifyTemplate(SmsTemplateVO smsTemplate){
        return smsTemplateDao.modifyTemplate(smsTemplate);
    }

    /**
     * 通过ID获取指定的短信模板
     * @param id
     * @return
     */
    @Override
    public SmsTemplate getTemplateById(Long id){
        return smsTemplateDao.getTemplateById(id);
    }

    /**
     * 获取短信模板列表
     * @return
     */
    @Override
    public List<SmsTemplate> getTemplateList(SmsTemplateListVO smsTemplateListVO){
        return smsTemplateDao.getList(smsTemplateListVO);
    }

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    @Override
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO) {
        return smsTemplateDao.getTemplateListCount(smsTemplateListVO);
    }

    /**
     * 通过编码获取短信模板
     * @param code
     * @return
     */
    @Override
    public SmsTemplate getByCode(String code) {
        return smsTemplateDao.getByCode(code);
    }

    /**
     * 根据ID列表批量删除
     * @param ids
     * @return
     */
    @Override
    public Integer batchDeleteById(List<Long> ids){
        return smsTemplateDao.batchDeleteById(ids);
    }

    /**
     * 生成短信
     *
     * @param generateSmsVO
     * @return
     */
    @Override
    public Integer generateSms(GenerateSmsVO generateSmsVO) {

        // redis
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);

        if (SmsTypeEnum.CHANGE_PASSWORD.getType().equals(generateSmsVO.getCode()) ||
                SmsTypeEnum.FORGET_PASSWORD.getType().equals(generateSmsVO.getCode()) ||
                SmsTypeEnum.REGISTER.getType().equals(generateSmsVO.getCode())) {

            generateSmsVO.setMessage(TelCodeUtil.createCode());
            //手机号 验证码
            redis.set(generateSmsVO.getTelephone(), generateSmsVO.getMessage());
        }
        //获取短信内容
        String content = smsTemplateDao.generateSms(generateSmsVO);
        // 发送短信
        Integer status = smsService.sendSys(content);

        Sms sms = new Sms();
        BeanUtils.copyProperties(generateSmsVO, sms);

        sms.setContent(content);
        sms.setStatus(status);

        smsService.insertSms(sms);

        return status;
    }

}
