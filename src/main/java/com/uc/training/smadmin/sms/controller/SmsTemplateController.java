package com.uc.training.smadmin.sms.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.service.SmsTemplateService;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: 余旭东
 * @Date: 2018/10/19 9:06
 * @Description: 短信模板控制器
 */
@Controller
@RequestMapping("api/smsTemplate")
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
    public Result<Integer> addTemplate(SmsTemplate template){

        template.setCreateEmp(getUid());
        return Result.getSuccessResult(smsTemplateService.addTemplate(template));
    }

    /**
     * 根据ID删除短信模板
     * @param id
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/deleteTemplate", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> deleteTemplate(Long id){
        return Result.getSuccessResult(smsTemplateService.deleteTemplateById(id));
    }

    /**
     * 新增短信模板
     * @param template
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/modifyTemplate", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> modifyTemplate(SmsTemplate template){

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
    @RequestMapping(value = "getSmsTemplatePage.do_", method = RequestMethod.GET)
    public Result<PageVO<SmsTemplate>> getDemoPage(SmsTemplateListVO smsTemplateListVO) {
        Result<PageVO<SmsTemplate>> res;
        try {
            PageVO<SmsTemplate> pageVO = new PageVO<SmsTemplate>();
            pageVO.setPageIndex(smsTemplateListVO.getPageIndex());
            pageVO.setPageSize(smsTemplateListVO.getPageSize());
            pageVO.setTotal(smsTemplateService.getTemplateListCount(smsTemplateListVO));
            pageVO.setDataList(smsTemplateService.getTemplateList(smsTemplateListVO));
            res = Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取smsTemplate分页失败", null);
        }
        return res;
    }



}
