package com.ucar.smadmin.gds.controller;

import com.ucar.smadmin.common.base.controller.BaseController;
import com.ucar.smadmin.common.vo.PageVO;
import com.ucar.smadmin.common.vo.Result;
import com.ucar.smadmin.gds.dto.AdminHotTagListDTO;
import com.ucar.smadmin.gds.dto.HotTagDTO;
import com.ucar.smadmin.gds.re.HotTagRE;
import com.ucar.smadmin.gds.service.HotTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/6
 */
@Controller
@RequestMapping("admin/gds/hotTag")
public class AdminHotTagController extends BaseController {

    @Autowired
    private HotTagService hotTagService;

    /**
     * 后台获取标签信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllHotTag.do_", method = RequestMethod.POST)
    public Result<PageVO<HotTagRE>> getAllBannerList(AdminHotTagListDTO adminHotTagListDTO) {
        PageVO<HotTagRE> pageVO = hotTagService.getAllHotTagList(adminHotTagListDTO);

        return Result.getSuccessResult(pageVO);
    }

    /**
     * 更新标签
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateHotTag.do_", method = RequestMethod.POST)
    public Result updateHotTag(@Validated HotTagDTO hotTagDTO) {
        hotTagDTO.setModifyEmp(getUid());

        return Result.getSuccessResult(hotTagService.updateHotTag(hotTagDTO));
    }

    /**
     * 根据主键id删除标签
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteHotTagById.do_", method = RequestMethod.POST)
    public Result deleteHotTagById(Long id) {
        return Result.getSuccessResult(hotTagService.deleteHotTagById(id));
    }

    /**
     * 新增标签
     * @param hotTag
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertHotTag.do_", method = RequestMethod.POST)
    public Result insertHotTag(@Validated HotTagDTO hotTag) {

        hotTag.setCreateEmp(getUid());
        hotTag.setModifyEmp(getUid());

        return Result.getSuccessResult(hotTagService.insertHotTag(hotTag));
    }

}
