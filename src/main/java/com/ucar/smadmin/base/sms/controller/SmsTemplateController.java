package com.ucar.smadmin.base.sms.controller;

import com.ucar.smapi.base.sms.re.SmsTemplateRE;
import com.ucar.smadmin.base.sms.service.SmsTemplateService;
import com.ucar.smadmin.base.sms.vo.SmsTemplateListVO;
import com.ucar.smadmin.base.sms.vo.SmsTemplateVO;
import com.ucar.smadmin.common.annotation.AccessLogin;
import com.ucar.smadmin.common.base.controller.BaseController;
import com.ucar.smapi.common.vo.PageVO;
import com.ucar.smapi.common.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 余旭东
 * @Date: 2018/10/19 9:06
 * @Description: 短信模板控制器
 */
@Controller
@RequestMapping("admin/smsTemplate")
public class SmsTemplateController extends BaseController {

    @Autowired
    private SmsTemplateService smsTemplateService;

    /**
     * 新增短信模板
     * @param template
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
    @ResponseBody
    public Result<Long> addTemplate(@Validated SmsTemplateVO template){
        if (StringUtils.isEmpty(template.getCode())) {
            return Result.getBusinessException("编码不能为空", null);
        }
        // 编码已存在
        SmsTemplateRE temp = smsTemplateService.getByCode(template.getCode());
        if (temp != null) {
            return Result.getBusinessException("编码已存在", null);
        }
        template.setCreateEmp(getUid());
        return Result.getSuccessResult(smsTemplateService.addTemplate(template));
    }

    /**
     * 根据ID删除短信模板
     * @param id
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/deleteTemplate.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> deleteTemplate(Long id){
        return Result.getSuccessResult(smsTemplateService.deleteTemplateById(id));
    }

    /**
     * 修改短信模板
     * @param template
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/modifyTemplate.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> modifyTemplate(@Validated SmsTemplateVO template){
        template.setModifyEmp(getUid());
        return Result.getSuccessResult(smsTemplateService.modifyTemplate(template));
    }

    /**
     * 获取分页列表
     * @param smsTemplateListVO
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getSmsTemplatePage.do_", method = RequestMethod.POST)
    public Result<PageVO<SmsTemplateRE>> getDemoPage(@Validated SmsTemplateListVO smsTemplateListVO) {
        Result<PageVO<SmsTemplateRE>> res;
        PageVO<SmsTemplateRE> pageVO = new PageVO<>();
        pageVO.setPageIndex(smsTemplateListVO.getPageIndex());
        pageVO.setPageSize(smsTemplateListVO.getPageSize());
        pageVO.setTotal(smsTemplateService.getTemplateListCount(smsTemplateListVO));
        pageVO.setDataList(smsTemplateService.getTemplateList(smsTemplateListVO));
        res = Result.getSuccessResult(pageVO);
        return res;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "batchDeleteTemplate.do_", method = RequestMethod.POST)
    public Result<Integer> batchDeleteTemplate(String[] ids){
        // 判空
        if (ids == null) {
            return Result.getBusinessException("删除失败", null);
        }
        List<Long> list = new ArrayList<>();
        for (String s : ids) {
            list.add(Long.parseLong(s));
        }
        return Result.getSuccessResult(smsTemplateService.batchDeleteById(list));
    }

}
