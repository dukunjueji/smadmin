package com.uc.training.smadmin.sms.service;

import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.vo.GenerateSmsVO;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;
import com.uc.training.smadmin.sms.vo.SmsTemplateVO;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 8:57
 * @Description:
 */
interface SmsTemplateService {
    /**
     * 新增短信模板
     * @param smsTemplate
     * @return
     */
    Long addTemplate(SmsTemplateVO smsTemplate);

    /**
     * 通过ID删除短信模板
     * @param id
     * @return
     */
    Integer deleteTemplateById(Long id);

    /**
     * 修改短信模板
     * @param smsTemplate
     * @return
     */
    Integer modifyTemplate(SmsTemplateVO smsTemplate);

    /**
     * 通过ID获取指定的短信模板
     * @param id
     * @return
     */
    SmsTemplate getTemplateById(Long id);

    /**
     * 获取短信模板列表
     * @param smsTemplateListVO
     * @return
     */
    List<SmsTemplate> getTemplateList(SmsTemplateListVO smsTemplateListVO);

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO);

    /**
     * 通过编码获取短信模板
     * @param code
     * @return
     */
    SmsTemplate getByCode(String code);

    /**
     * 根据ID列表批量删除
     * @param ids
     * @return
     */
    Integer batchDeleteById(List<Long> ids);

    /**
     * 生成短信
     * @param generateSmsVO
     * @return
     */
    Integer generateSms(GenerateSmsVO generateSmsVO);

}
