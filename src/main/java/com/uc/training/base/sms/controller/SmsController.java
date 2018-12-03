package com.uc.training.base.sms.controller;

import com.uc.training.base.sms.re.SmsRE;
import com.uc.training.base.sms.service.SmsService;
import com.uc.training.base.sms.vo.SmsListVO;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result<PageVO<SmsRE>> getDemoPage(SmsListVO smsListVO) {
        Result<PageVO<SmsRE>> res;
        try {
            PageVO<SmsRE> pageVO = new PageVO<>();
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
