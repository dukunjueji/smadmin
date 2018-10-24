package com.uc.training.smadmin.demo.controller;

import com.ycc.base.common.Result;
import com.ycc.base.framework.exception.BusinessRuntimeException;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.demo.re.DemoRE;
import com.uc.training.smadmin.demo.model.Demo;
import com.uc.training.smadmin.demo.service.DemoService;
import com.uc.training.smadmin.demo.vo.DemoListVO;
import com.uc.training.smadmin.demo.vo.DemoTestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 示例Controller.
 * <p>
 * 说明：
 * <li>返回值会自动封装上{@link Result}</li>
 * <li>抛出{@link BusinessRuntimeException}异常时,封装Result.status=-1对象,包含异常的code和msg,不会log日志.</li>
 * <li>抛出其他异常时,封装Result.status=-2对象,包含异常的msg,自动log异常日志.</li>
 * <p>
 * All Rights Reserved.
 *
 * @version 1.0 2017-12-20 14:06:29 by 刘庆魁（qk.liu@zuche.com）
 */
@Controller
@RequestMapping(value = "api/demo/demo")
public class DemoController extends BaseController {
    @Autowired
    private DemoService demoService;

    /**
     * 参数验证
     *
     * @version 1.0 2018/10/24 11:09 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param demoTestVO 请求参数
     * @return com.ycc.base.common.Result<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @RequestMapping("validate.do_")
    @ResponseBody
    public Result<Map<String, Object>> validate(@Validated DemoTestVO demoTestVO) {
        Map<String, Object> re = new HashMap<String, Object>();
        re.put("total", 10);
        re.put("datas", new ArrayList<Object>());
        return Result.getSuccessResult(re);
    }

    /**
     * Description: 员工列表展示.
     *
     * @version 1.0 2017-12-20 14:06:29 by 刘庆魁（qk.liu@zuche.com）
     */
    @RequestMapping("list.do_")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {

        Map<String, Object> re = new HashMap<String, Object>();
        re.put("total", 10);
        re.put("datas", new ArrayList<Object>());

        if ("1".equals(request.getParameter("ex"))) {
            //业务类异常, 例如校验, 最终会返回{"code":"001","msg":"参数校验错误","status":-1}
            throw new BusinessRuntimeException("参数校验错误", "001");
        }

        if ("2".equals(request.getParameter("ex"))) {
            //业务类异常, 例如校验, 最终会返回{"msg":"系统级别的异常","status":-2}
            throw new RuntimeException("系统级别的异常");
        }

        return re;//返回普通对象,会自动封装Result,最终返回{"status":0,"msg":null,"code":null,"re":{"datas":[],"total":10}}
    }

    @AccessLogin
    @ResponseBody
    @RequestMapping("listAll.do_")
    public Result<List<Demo>> getList() {
        Result<List<Demo>> res;
        try {
            res = Result.getSuccessResult(demoService.getList());
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取demo分页失败", null);
        }
        return res;
    }

    @AccessLogin
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
