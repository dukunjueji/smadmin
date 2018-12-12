package com.uc.training.base.sms.service.impl;

import com.uc.training.base.sms.re.SmsTemplateRE;
import com.uc.training.base.sms.service.SmsService;
import com.uc.training.base.sms.service.SmsTemplateService;
import com.uc.training.base.sms.vo.GenerateSmsVO;
import com.uc.training.base.sms.vo.SmsTemplateListVO;
import com.uc.training.base.sms.vo.SmsTemplateVO;
import com.uc.training.base.sms.vo.SmsVO;
import com.uc.training.common.enums.SmsStatusEnum;
import com.uc.training.common.enums.SmsTypeEnum;
import com.uc.training.common.redis.RedisConfigEnum;
import com.uc.training.common.utils.InjectionUtils;
import com.uc.training.common.utils.TelCodeUtil;
import com.uc.training.remote.client.BaseClient;
import com.ycc.tools.middleware.redis.RedisCacheUtils;
import org.apache.commons.collections.CollectionUtils;
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
    private SmsService smsService;

    /**
     * 新增短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Long addTemplate(SmsTemplateVO smsTemplate){
        return BaseClient.addTemplate(smsTemplate);
    }

    /**
     * 通过ID删除短信模板
     * @param id
     * @return
     */
    @Override
    public Integer deleteTemplateById(Long id){
        return BaseClient.deleteTemplateById(id);
    }

    /**
     * 修改短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Integer modifyTemplate(SmsTemplateVO smsTemplate){
        return BaseClient.modifyTemplate(smsTemplate);
    }

    /**
     * 通过ID获取指定的短信模板
     * @param id
     * @return
     */
    @Override
    public SmsTemplateRE getTemplateById(Long id){
        return BaseClient.getTemplateById(id);
    }

    /**
     * 获取短信模板列表
     * @return
     */
    @Override
    public List<SmsTemplateRE> getTemplateList(SmsTemplateListVO smsTemplateListVO){
        return BaseClient.getTemplateList(smsTemplateListVO);
    }

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    @Override
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO) {
        return BaseClient.getTemplateListCount(smsTemplateListVO);
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
        List<SmsTemplateRE> list = BaseClient.getTemplateList(sms);
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
        return BaseClient.batchDeleteSmsTempleById(ids);
    }

    /**
     * 生成短信
     *
     * @param generateSmsVO
     * @return
     */
    @Override
    public Integer generateSms(GenerateSmsVO generateSmsVO) {

        this.smsService = InjectionUtils.getInjectionInstance(SmsService.class);

        if (SmsTypeEnum.CHANGE_PASSWORD.getCode().equals(generateSmsVO.getCode()) ||
                SmsTypeEnum.FORGET_PASSWORD.getCode().equals(generateSmsVO.getCode()) ||
                SmsTypeEnum.REGISTER.getCode().equals(generateSmsVO.getCode())) {

            // redis
            RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);
            generateSmsVO.setMessage(TelCodeUtil.createCode());
            //手机号 验证码
            redis.set(generateSmsVO.getTelephone(), generateSmsVO.getMessage());
        }

        if (generateSmsVO.getRechargeStatus() != null && generateSmsVO.getRechargeStatus() == 0) {
            generateSmsVO.setCode(SmsTypeEnum.RECHARGE_FAIL.getCode());
        }
        SmsTemplateListVO smsTemplateListVO = new SmsTemplateListVO();
        smsTemplateListVO.setCode(generateSmsVO.getCode());
        List<SmsTemplateRE> smsTemplateRE = BaseClient.getTemplateList(smsTemplateListVO);
        //查找短信模板存在
        if (CollectionUtils.isEmpty(smsTemplateRE) || smsTemplateRE.size() != 1) {
            return SmsStatusEnum.TEMPLATE_NOT_EXIST.getKey();
        }
        //获取短信内容
        String content = BaseClient.generateSms(generateSmsVO);
        //邮件标题
        generateSmsVO.setEmailTitle(smsTemplateRE.get(0).getTitle());
        // 发送短信
        Integer status = this.smsService.sendSys(generateSmsVO, content);

        SmsVO sms = new SmsVO();
        sms.setType(generateSmsVO.getType());
        sms.setTelephone(generateSmsVO.getTelephone());
        sms.setStatus(status);
        sms.setContent(content);
        // 新增短信记录
        BaseClient.insertSms(sms);

        return status;
    }

}
