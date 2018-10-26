package com.uc.training.smadmin.udfs.controller;

import com.uc.training.smadmin.udfs.service.UDFSFileService;
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
public class UDFSUploadController {

    private static final Logger logger = LoggerFactory.getLogger(UDFSUploadController.class);

    @Autowired
    private UDFSFileService fileService;

    @RequestMapping(value = "/file.do_", method = RequestMethod.POST)
    @ResponseBody
    public UDFSUploadResultVO imageUpload(String name, MultipartFile file) {
        return fileService.upload(name,file);
    }

    @RequestMapping(value = "/text.do_", method = RequestMethod.POST)
    @ResponseBody
    public UDFSUploadResultVO imageToTextUpload(String name, String imageText) {
        return fileService.base64Upload(name, imageText);
    }
}
