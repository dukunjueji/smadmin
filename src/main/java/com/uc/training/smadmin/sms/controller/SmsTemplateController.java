package com.uc.training.smadmin.sms.controller;

import com.uc.training.common.enums.SmsTemplateReplaceEnum;
import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.sms.model.SmsTemplate;
import com.uc.training.smadmin.sms.service.SmsTemplateService;
import com.uc.training.smadmin.sms.vo.SmsTemplateListVO;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public Result<Long> addTemplate(SmsTemplate template){
        // 判空
        if (template == null || StringUtils.isEmpty(template.getCode())|| StringUtils.isEmpty(template.getTitle()) ||
                StringUtils.isEmpty(template.getContent()) || StringUtils.isEmpty(template.getTypeDes())){
            return Result.getBusinessException("短信模板添加失败", null);
        }
        // 编码已存在
        SmsTemplate temp = smsTemplateService.getByCode(template.getCode());
        if (temp != null) {
            return Result.getBusinessException("编码已存在", null);
        }
        template.setCreateEmp(getUid());
        Long id = smsTemplateService.addTemplate(template);
        return Result.getSuccessResult(id);
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
    @RequestMapping(value = "/modifyTemplate", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> modifyTemplate(SmsTemplate template){
        // 判空
        if (template == null || StringUtils.isEmpty(template.getCode())|| StringUtils.isEmpty(template.getTitle()) ||
                StringUtils.isEmpty(template.getContent()) || StringUtils.isEmpty(template.getTypeDes())){
            return Result.getBusinessException("短信模板添加失败", null);
        }
        SmsTemplate t1 = smsTemplateService.getTemplateById(template.getId());
        // 编码已存在
        SmsTemplate temp = smsTemplateService.getByCode(template.getCode());
        if (temp != null && !temp.getCode().equals(t1.getCode())) {
            return Result.getBusinessException("编码已存在", null);
        }
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

    /**
     * 获取替换字符
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getReplaceString.do_", method = RequestMethod.GET)
    public Result<Map<String, String>> getReplaceString(){
        return Result.getSuccessResult(SmsTemplateReplaceEnum.getMap());
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "batchDeleteTemplate.do_", method = RequestMethod.POST)
    public Result<Integer> batchDeleteTemplate(String ids){
        if (ids == null) {
            return Result.getBusinessException("删除失败", null);
        }
        String[] sp = StringUtils.split(ids.substring(1, ids.length()-1), ',');
        if (sp == null || sp.length==0){
            return Result.getBusinessException("删除失败", null);
        }
        List<Long> list = new ArrayList<>();
        for (String s : sp) {
            list.add(Long.parseLong(s));
        }
        return Result.getSuccessResult(smsTemplateService.batchDeleteById(list));
    }

}
