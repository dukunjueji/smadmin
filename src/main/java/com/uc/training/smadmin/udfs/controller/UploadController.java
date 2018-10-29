package com.uc.training.smadmin.udfs.controller;

import com.mysql.jdbc.ResultSetRow;
import com.mysql.jdbc.StringUtils;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.udfs.service.UDFSFileService;
import com.uc.training.smadmin.udfs.service.UploadService;
import com.ycc.base.common.Result;
import com.zuche.base.common.web.taglib.util.StringUtil;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
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
@RequestMapping("/api/upload")
public class UploadController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "/file.do_", method = RequestMethod.POST)
    public Result<UDFSUploadResultVO> imageUpload(MultipartFile file) {

        String name = file.getOriginalFilename();

        if (file == null) {
            Result.getBusinessException("上传文件不能为空" , null);
        }
        if (file.getSize() >= 4194304) {
            Result.getBusinessException("上传文件不能超过4M", null);
        }

        return Result.getSuccessResult(uploadService.imageUpload(name,file));
    }

    /**
     * 图片转String上传
     * @param name
     * @param imageText
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "/text.do_", method = RequestMethod.POST)
    public Result<UDFSUploadResultVO> imageToTextUpload(String name, String imageText) {
        return Result.getSuccessResult(uploadService.imageToTextUpload(name, imageText));
    }
}
