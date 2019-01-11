package com.uc.training.base.sms.controller;

import com.uc.training.base.sms.re.SmsRE;
import com.uc.training.base.sms.service.SmsService;
import com.uc.training.base.sms.vo.SmsListVO;
import com.smgoods.common.annotation.AccessLogin;
import com.smgoods.common.base.controller.BaseController;
import com.smgoods.common.vo.PageVO;
import com.smgoods.common.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        int minLength = 2;
        String time = smsListVO.getTime();
        time = time.replace("\"", "");
        time = time.replace("[", "");
        time = time.replace("]", "");
        String nullValue = "null";
        if (!StringUtils.isEmpty(time) && !nullValue.equals(time)){
            String[] times = StringUtils.split(time, ',');
            times[1] = times[1].replace("00:00:00", "23:59:59");
            DateFormat ds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (times.length == minLength) {
                try {
                    smsListVO.setStartDate(ds.parse(times[0]));
                    smsListVO.setEndDate(ds.parse(times[1]));
                } catch (ParseException e) {
                    logger.error("日期格式不正确");
                    return Result.getBusinessException("日期格式不正确", null);
                }
            }
        }
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
