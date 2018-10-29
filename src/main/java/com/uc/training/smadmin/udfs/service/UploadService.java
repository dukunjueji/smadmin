package com.uc.training.smadmin.udfs.service;

import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
@Service
public class UploadService {

    @Autowired
    private UDFSFileService udfsFileService;

    public UDFSUploadResultVO imageUpload(String name, MultipartFile file) {
        return udfsFileService.upload(name, file);
    }


    public UDFSUploadResultVO imageToTextUpload(String name, String imageText) {
        return udfsFileService.base64Upload(name, imageText);
    }
}
