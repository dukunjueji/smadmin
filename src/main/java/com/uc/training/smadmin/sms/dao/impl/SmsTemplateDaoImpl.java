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
    private static final String namespace = "com.uc.training.smadmin.sms.dao.SmsTemplateDao.";
    /**
     * 添加短信模板
     * @param template
     * @return
     */
    @Override
    public Long addTemplate(SmsTemplate template) {
        return (Long) this.insert(namespace + "insertSmsTemplate", template);
    }

    /**
     * 修改短信模板
     * @param template
     * @return
     */
    @Override
    public Integer modifyTemplate(SmsTemplate template) {
        return this.update(namespace + "updateSmsTemplateById", template);
    }

    /**
     * 通过ID删除短信模板
     * @return
     */
    @Override
    public Integer deleteTemplate(Long id) {
        return this.deleteObject(namespace + "deleteById", id);
    }

    /**
     * 通过ID获取模板
     * @param id
     * @return
     */
    @Override
    public SmsTemplate getTemplateById(Long id) {
        return (SmsTemplate) this.queryForObject(namespace + "getSmsTemplateById", id);
    }

    /**
     * 获取短信模板列表
     * @return
     */
    @Override
    public List<SmsTemplate> getList(SmsTemplateListVO smsTemplateListVO) {
        return this.queryForList(namespace + "querySmsTemplateList", smsTemplateListVO);
    }

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    @Override
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO) {
        return (Long) this.queryForObject(namespace + "querySmsTemplateCount");
    }

    /**
     * 通过编码获取短信模板
     * @param code
     * @return
     */
    @Override
    public SmsTemplate getByCode(String code) {
        return (SmsTemplate) this.queryForObject(namespace + "getByCode", code);
    }

}
