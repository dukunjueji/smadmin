package com.uc.training.smadmin.upload.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.upload.service.UploadService;
import com.uc.training.smadmin.upload.re.UploadRE;
import com.uc.training.smadmin.upload.vo.UploadVO;
import com.ycc.base.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * description: 使用UDFS客户端上传文件
 * wiki: http://wiki.10101111.com/pages/viewpage.action?pageId=173023173
 *
 * @author yiqiang.du@ucarinc.com
 * @version 1.0
 * @date 2018/9/18 16:32
 */
@Controller
@RequestMapping("/admin/upload")
public class UploadController extends BaseController{

    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传
     * @param uploadVO
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "/file.do_", method = RequestMethod.POST)
    public Result<UploadRE> imageUpload(UploadVO uploadVO) {

        if (uploadVO.getFile().isEmpty()) {
            Result.getBusinessException("上传文件不能为空" , null);
        }
        if (uploadVO.getFile().getSize() >= 4194304) {
            Result.getBusinessException("上传文件不能超过4M", null);
        }

        return Result.getSuccessResult(uploadService.imageUpload(uploadVO.getFile()));
    }

    /**
     * 图片转String上传
     * @param uploadVO
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "/text.do_", method = RequestMethod.POST)
    public Result<UploadRE> imageToTextUpload(UploadVO uploadVO) {
        return Result.getSuccessResult(uploadService.imageToTextUpload(uploadVO.getImageText()));
    }
}
