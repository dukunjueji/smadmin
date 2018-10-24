package com.uc.training.smadmin.sms.dao;

import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/19 9:11
 * @Description: 短信模板DAO类
 */
public interface SmsTemplateDao {

    /**
     * 添加短信模板
     * @param template
     * @return
     */
    public Integer addTemplate(SmsTemplate template);

    /**
     * 修改短信模板
     * @param template
     * @return
     */
    public Integer modifyTemplate(SmsTemplate template);

    /**
     * 通过ID删除短信模板
     * @return
     */
    public Integer deleteTemplate(Long id);

    /**
     * 通过ID获取模板
     * @param id
     * @return
     */
    public SmsTemplate getTemplateById(Long id);

    /**
     * 获取短信模板列表
     * @return
     */
    public List<SmsTemplate> getList(SmsTemplateListVO smsTemplateListVO);

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO);

}
