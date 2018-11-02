package com.uc.training.smadmin.sms.dao.impl;

import com.uc.training.smadmin.sms.dao.SmsTemplateDao;
import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.vo.GenerateSmsVO;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;
import com.uc.training.smadmin.sms.vo.SmsTemplateVO;
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
    private static final String NAMESPACE = "com.uc.training.smadmin.sms.dao.SmsTemplateDao.";
    /**
     * 添加短信模板
     * @param template
     * @return
     */
    @Override
    public Long addTemplate(SmsTemplateVO template) {
        return (Long) this.insert(NAMESPACE + "insertSmsTemplate", template);
    }

    /**
     * 修改短信模板
     * @param template
     * @return
     */
    @Override
    public Integer modifyTemplate(SmsTemplateVO template) {
        return this.update(NAMESPACE + "updateSmsTemplateById", template);
    }

    /**
     * 通过ID删除短信模板
     * @return
     */
    @Override
    public Integer deleteTemplate(Long id) {
        int res = this.deleteObject(NAMESPACE + "deleteById", id);
        return res;
    }

    /**
     * 通过ID获取模板
     * @param id
     * @return
     */
    @Override
    public SmsTemplate getTemplateById(Long id) {
        return (SmsTemplate) this.queryForObject(NAMESPACE + "getSmsTemplateById", id);
    }

    /**
     * 获取短信模板列表
     * @return
     */
    @Override
    public List<SmsTemplate> getList(SmsTemplateListVO smsTemplateListVO) {
        return this.queryForList(NAMESPACE + "querySmsTemplateList", smsTemplateListVO);
    }

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    @Override
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO) {
        return (Long) this.queryForObject(NAMESPACE + "querySmsTemplateCount", smsTemplateListVO);
    }

    /**
     * 通过编码获取短信模板
     * @param code
     * @return
     */
    @Override
    public SmsTemplate getByCode(String code) {
        return (SmsTemplate) this.queryForObject(NAMESPACE + "getByCode", code);
    }

    /**
     * 根据ID列表批量删除
     * @param ids
     * @return
     */
    @Override
    public Integer batchDeleteById(List<Long> ids){
        return this.deleteObject(NAMESPACE + "batchDeleteById", ids);
    }

    /**
     * 生成短信
     *
     * @param generateSmsVO
     * @return
     */
    @Override
    public String generateSMS(GenerateSmsVO generateSmsVO) {
        return (String) this.queryForObject(NAMESPACE + "generateSMS", generateSmsVO);
    }

}
