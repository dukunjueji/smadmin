package com.ucar.smadmin.base.sms.service;

import com.ucar.smadmin.base.sms.vo.SmsTemplateListVO;
import com.ucar.smadmin.base.sms.vo.SmsTemplateVO;
import com.ucar.smapi.base.sms.re.SmsTemplateRE;

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
    SmsTemplateRE getTemplateById(Long id);

    /**
     * 获取短信模板列表
     * @param smsTemplateListVO
     * @return
     */
    List<SmsTemplateRE> getTemplateList(SmsTemplateListVO smsTemplateListVO);

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
    SmsTemplateRE getByCode(String code);

    /**
     * 根据ID列表批量删除
     * @param ids
     * @return
     */
    Integer batchDeleteById(List<Long> ids);


}
