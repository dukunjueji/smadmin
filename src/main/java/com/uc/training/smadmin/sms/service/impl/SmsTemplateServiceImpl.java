package com.uc.training.smadmin.sms.service.impl;

import com.uc.training.smadmin.sms.dao.SmsTemplateDao;
import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.service.SmsTemplateService;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;
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

    /**
     * 新增短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Long addTemplate(SmsTemplate smsTemplate){
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
    public Integer modifyTemplate(SmsTemplate smsTemplate){
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
}
