package com.uc.training.smadmin.demo.controller;

import com.ycc.base.common.Result;
import com.ycc.base.framework.exception.BusinessRuntimeException;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.demo.re.DemoRE;
import com.uc.training.smadmin.demo.service.DemoService;
import com.uc.training.smadmin.demo.vo.DemoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 示例Controller.
 * 说明：
 * <li>返回值会自动封装上{@link Result}</li>
 * <li>抛出{@link BusinessRuntimeException}异常时,封装Result.status=-1对象,包含异常的code和msg,不会log日志.</li>
 * <li>抛出其他异常时,封装Result.status=-2对象,包含异常的msg,自动log异常日志.</li>
 * <p>
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/25 17:49
 */
@Controller
@RequestMapping(value = "admin/demo/demo")
public class DemoController extends BaseController {
    @Autowired
    private DemoService demoService;

    @ResponseBody
    @AccessLogin(required = false)
    @RequestMapping("requestTest.do_")
    public Result<String> getDemoPage(@RequestBody List<DemoListVO> list) {
        for (DemoListVO demoListVO : list) {
            System.out.println(demoListVO.getName() + demoListVO.getSex());
        }
        return Result.getSuccessResult("success");
    }

    /**
     * 分页查询
     *
     * @author 吴佰川（baichuan.wu@ucarinc.com）创建
     * @version 1.0
     * @date 2018/10/25 17:50
     */
    @ResponseBody
    @RequestMapping("getDemoPage.do_")
    public Result<PageVO<DemoRE>> getDemoPage(DemoListVO demoListVO) {
        Result<PageVO<DemoRE>> res;
        try {
            PageVO<DemoRE> pageVO = new PageVO<DemoRE>();
            pageVO.setPageIndex(demoListVO.getPageIndex());
            pageVO.setPageSize(demoListVO.getPageSize());
            pageVO.setTotal(demoService.getDemoListCount(demoListVO));
            pageVO.setDataList(demoService.getDemoList(demoListVO));
            res = Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("获取demo分页失败！", e);
            res = Result.getBusinessException("获取demo分页失败", null);
        }
        return res;
    }

}
