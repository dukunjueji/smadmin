package com.uc.training.smadmin.bd.controller;

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
import org.w3c.dom.Text;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/23
 */
@Controller
@RequestMapping("/api/banner")
public class BannerUploadController {

    private static final Logger logger = LoggerFactory.getLogger(BannerUploadController.class);

    @Autowired
    private UDFSFileService fileService;

    @ResponseBody
    @RequestMapping(value = "/upload.do_")
    public UDFSUploadResultVO upload(String name,MultipartFile file) {
        System.out.println(file.getName());
        // UDFSUploadResultVO resultVO = fileService.upload(name,file);

        return null;
    }
    @RequestMapping(value = "/text.do_", method = RequestMethod.POST)
    @ResponseBody
    public UDFSUploadResultVO imageToTextUpload(String name, String imageText) {



        return null;
    }

}
