package com.uc.training.smadmin.sms.dao.impl;

import com.uc.training.smadmin.sms.dao.SmsTemplateDao;
import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/19 9:20
 * @Description:
 */
@Repository
public class SmsTemplateDaoImpl extends CarIsIbatisDaoImpl implements SmsTemplateDao {
    /**
     * 添加短信模板
     * @param template
     * @return
     */
    @Override
    public Integer addTemplate(SmsTemplate template) {
        return (Integer) this.insert("insertSmsTemplate", template);
    }

    /**
     * 修改短信模板
     * @param template
     * @return
     */
    @Override
    public Integer modifyTemplate(SmsTemplate template) {
        return this.update("updateSmsTemplateById", template);
    }

    /**
     * 通过ID删除短信模板
     * @return
     */
    @Override
    public Integer deleteTemplate(Long id) {
        return this.deleteObject("deleteById", id);
    }

    /**
     * 通过ID获取模板
     * @param id
     * @return
     */
    @Override
    public SmsTemplate getTemplateById(Long id) {
        return (SmsTemplate) this.queryForObject("getSmsTemplateById", id);
    }

    /**
     * 获取短信模板列表
     * @return
     */
    @Override
    public List<SmsTemplate> getList(SmsTemplateListVO smsTemplateListVO) {
        return this.queryForList("querySmsTemplateList", smsTemplateListVO);
    }

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    @Override
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO) {
        return (Long) this.queryForObject("querySmsTemplateCount");
    }

}
