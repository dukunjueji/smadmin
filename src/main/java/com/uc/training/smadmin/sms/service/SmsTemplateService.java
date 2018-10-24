package com.uc.training.smadmin.sms.service;

import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 8:57
 * @Description:
 */
public interface SmsTemplateService {
    /**
     * 新增短信模板
     * @param smsTemplate
     * @return
     */
    public Integer addTemplate(SmsTemplate smsTemplate);

    /**
     * 通过ID删除短信模板
     * @param id
     * @return
     */
    public Integer deleteTemplateById(Long id);

    /**
     * 修改短信模板
     * @param smsTemplate
     * @return
     */
    public Integer modifyTemplate(SmsTemplate smsTemplate);

    /**
     * 通过ID获取指定的短信模板
     * @param id
     * @return
     */
    public SmsTemplate getTemplateById(Long id);

    /**
     * 获取短信模板列表
     * @return
     */
    public List<SmsTemplate> getTemplateList(SmsTemplateListVO smsTemplateListVO);

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO);

}
