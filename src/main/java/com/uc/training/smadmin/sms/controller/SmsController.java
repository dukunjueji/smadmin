package com.uc.training.smadmin.sms.controller;

import com.uc.training.common.enums.SmsStatusEnum;
import com.uc.training.common.enums.SmsTemplateTypeEnum;
import com.uc.training.smadmin.sms.vo.SmsVO;
import com.uc.training.smadmin.utils.TelCodeUtil;
import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.sms.model.Sms;
import com.uc.training.smadmin.sms.service.SmsService;
import com.uc.training.smadmin.sms.vo.SmsListVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 13:52
 * @Description:
 */
@Controller
@RequestMapping("api/sms")
public class SmsController extends BaseController {
    @Autowired
    private SmsService smsService;

    /**
     * 获取分页列表
     * @param smsListVO
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getSmsPage.do_", method = RequestMethod.POST)
    public Result<PageVO<SmsVO>> getDemoPage(SmsListVO smsListVO) {
        Result<PageVO<SmsVO>> res;
        // 判断手机号是否合法
        if (!StringUtils.isEmpty(smsListVO.getTelephone()) && !TelCodeUtil.validateTel(smsListVO.getTelephone())) {
            return Result.getBusinessException("手机号码不正确", null);
        }
        try {
            PageVO<SmsVO> pageVO = new PageVO<>();
            pageVO.setPageIndex(smsListVO.getPageIndex());
            pageVO.setPageSize(smsListVO.getPageSize());
            pageVO.setTotal(smsService.querySmsCount(smsListVO));
            pageVO.setDataList(smsService.getList(smsListVO));
            res = Result.getSuccessResult(pageVO);

        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取sms分页失败", null);
        }
        return res;
    }

    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getStatus.do_", method = RequestMethod.GET)
    public Result<Map<Integer, String>> getStatus(){
        return Result.getSuccessResult(SmsStatusEnum.getMap());
    }

    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getType.do_", method = RequestMethod.GET)
    public Result<Map<Integer, String>> getType(){
        return Result.getSuccessResult(SmsTemplateTypeEnum.getMap());
    }

}
