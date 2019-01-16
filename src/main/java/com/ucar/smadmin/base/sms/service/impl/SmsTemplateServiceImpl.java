package com.ucar.smadmin.base.sms.service.impl;

import com.ucar.smadmin.base.sms.service.SmsTemplateService;
import com.ucar.smadmin.base.sms.vo.SmsTemplateListVO;
import com.ucar.smadmin.base.sms.vo.SmsTemplateVO;
import com.ucar.smadmin.common.redis.RedisComponent;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import com.ucar.smapi.base.sms.dto.SmsTemplateDTO;
import com.ucar.smapi.base.sms.re.SmsTemplateRE;
import org.apache.commons.collections.CollectionUtils;
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
    private BaseClient baseClient;
    @Autowired
    private RedisComponent redis;

    //使用MailServiceImpl()实现类，不要直接调用发送方法
    //private static MailService mailService = new MailServiceImpl();

    /**
     * 新增短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Long addTemplate(SmsTemplateVO smsTemplate){
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        BeanUtils.copyProperties(smsTemplate, smsTemplateDTO);
        return baseClient.addTemplate(smsTemplateDTO).getRe();
    }

    /**
     * 通过ID删除短信模板
     * @param id
     * @return
     */
    @Override
    public Integer deleteTemplateById(Long id){
        return baseClient.deleteTemplateById(id).getRe();
    }

    /**
     * 修改短信模板
     * @param smsTemplate
     * @return
     */
    @Override
    public Integer modifyTemplate(SmsTemplateVO smsTemplate){
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        BeanUtils.copyProperties(smsTemplate, smsTemplateDTO);
        return baseClient.modifyTemplate(smsTemplateDTO).getRe();
    }

    /**
     * 通过ID获取指定的短信模板
     * @param id
     * @return
     */
    @Override
    public SmsTemplateRE getTemplateById(Long id){
        return baseClient.getTemplateById(id).getRe();
    }

    /**
     * 获取短信模板列表
     * @return
     */
    @Override
    public List<SmsTemplateRE> getTemplateList(SmsTemplateListVO smsTemplateListVO){
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        smsTemplateDTO.setCode(smsTemplateListVO.getCode());
        smsTemplateDTO.setType(smsTemplateListVO.getType());
        smsTemplateDTO.setOffset(smsTemplateListVO.getOffset());
        smsTemplateDTO.setPageSize(smsTemplateListVO.getPageSize());
        return baseClient.getTemplateList(smsTemplateDTO).getRe();
    }

    /**
     * 查询列表总记录数
     * @param smsTemplateListVO
     * @return
     */
    @Override
    public Long getTemplateListCount(SmsTemplateListVO smsTemplateListVO) {
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        BeanUtils.copyProperties(smsTemplateListVO, smsTemplateDTO);
        return baseClient.getTemplateListCount(smsTemplateDTO).getRe();
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
        SmsTemplateDTO smsTemplateDTO = new SmsTemplateDTO();
        smsTemplateDTO.setCode(sms.getCode());
        smsTemplateDTO.setType(sms.getType());
        smsTemplateDTO.setOffset(sms.getOffset());
        smsTemplateDTO.setPageSize(sms.getPageSize());
        List<SmsTemplateRE> list = baseClient.getTemplateList(smsTemplateDTO).getRe();
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
        return baseClient.batchDeleteSmsTempleById(ids).getRe();
    }

}
