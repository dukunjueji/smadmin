package com.uc.training.smadmin.sms.controller;

import com.uc.training.common.enums.SmsStatusEnum;
import com.uc.training.common.enums.SmsTypeEnum;
import com.uc.training.smadmin.sms.vo.SmsVO;
import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.sms.service.SmsService;
import com.uc.training.smadmin.sms.vo.SmsListVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 13:52
 * @Description:
 */
@Controller
@RequestMapping("admin/sms")
public class SmsController extends BaseController {
    @Autowired
    private SmsService smsService;

    /**
     * 获取短信分页列表
     * @param smsListVO
     * @return
     */
    @AccessLogin
    @ResponseBody
    @RequestMapping(value = "getSmsPage.do_", method = RequestMethod.POST)
    public Result<PageVO<SmsVO>> getDemoPage(@Validated SmsListVO smsListVO) {
        if (!StringUtils.isEmpty(smsListVO.getTelephone())) {
            String regex = "^1[3456789]\\d{9}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(smsListVO.getTelephone());
            if (!m.matches()) {
                return Result.getBusinessException("请输入正确的手机号", null);
            }
        }
        Result<PageVO<SmsVO>> res;
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

}
