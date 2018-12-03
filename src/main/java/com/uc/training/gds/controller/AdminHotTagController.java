package com.uc.training.gds.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.AdminHotTagListRE;
import com.uc.training.smadmin.gds.service.HotTagService;
import com.uc.training.smadmin.gds.vo.AdminHotTagInsertVO;
import com.uc.training.smadmin.gds.vo.AdminHotTagListVO;
import com.uc.training.smadmin.gds.vo.AdminHotTagUpdateVO;
import com.ycc.base.common.Result;
import org.springframework.beans.BeanUtils;
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
    public Result<PageVO<AdminHotTagListRE>> getAllBannerList(AdminHotTagListVO adminHotTagListVO) {

        PageVO<AdminHotTagListRE> pageVO = new PageVO<>();
        pageVO.setPageIndex(adminHotTagListVO.getPageIndex());
        pageVO.setPageSize(adminHotTagListVO.getPageSize());
        pageVO.setTotal(hotTagService.getAdminHotTagCount(adminHotTagListVO));
        pageVO.setDataList(hotTagService.getAllHotTagList(adminHotTagListVO));

        return Result.getSuccessResult(pageVO);
    }

    /**
     * 更新标签
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateHotTag.do_", method = RequestMethod.POST)
    public Result updateHotTag(@Validated AdminHotTagUpdateVO adminHotTagUpdateVO) {

        HotTag hotTag = new HotTag();
        BeanUtils.copyProperties(adminHotTagUpdateVO, hotTag);

        hotTag.setModifyEmp(getUid());

        return Result.getSuccessResult(hotTagService.updateHotTag(hotTag));
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
     * @param adminHotTagInsertVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertHotTag.do_", method = RequestMethod.POST)
    public Result insertHotTag(@Validated AdminHotTagInsertVO adminHotTagInsertVO) {

        HotTag hotTag = new HotTag();
        BeanUtils.copyProperties(adminHotTagInsertVO, hotTag);

        hotTag.setCreateEmp(getUid());
        hotTag.setModifyEmp(getUid());

        return Result.getSuccessResult(hotTagService.insertHotTag(hotTag));
    }

}
