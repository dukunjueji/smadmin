package com.uc.training.smadmin.upload.service;

import com.uc.training.smadmin.upload.re.UploadRE;
import com.uc.training.smadmin.utils.FileTypeUtil;
import com.ycc.tools.upload.FileTransferUtils;
import com.zuche.framework.enums.BusinessLineEnum;
import com.zuche.framework.udfs.client.UDFSClient;
import com.zuche.framework.udfs.client.UDFSPermissionEnum;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import com.zuche.framework.udfs.client.upload.UDFSUploadVO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
@Service
public class UploadService {

    private final Logger logger = LoggerFactory.getLogger(UploadService.class);

    /**
     * 上传图片文件
     * @param file
     * @return
     */
    public UploadRE imageUpload(MultipartFile file) {

        UDFSUploadResultVO resultVO;
        UploadRE uploadRE = new UploadRE();

        try {
            // 如果是FileInputStream类型，进行转换
            InputStream in = file.getInputStream();
            byte[] bytes = getByteArrayInputStreamResource(in);
            UDFSUploadVO vo = new UDFSUploadVO();

            //如果客户端没有传名字，则自动设置文件名
            String md5 = DigestUtils.md5Hex(bytes);
            String fileType = FileTypeUtil.getFileTypeByStream(bytes);
            //此处必须传文件的格式作为后缀, 否则RPC调用失败
            vo.setName(md5 + "." + fileType);
            vo.setData(bytes);
            vo.setBusinessLine(BusinessLineEnum.UC);
            vo.setPermission(UDFSPermissionEnum.GLOBAL);
            resultVO =  UDFSClient.upload(vo);

            if(resultVO != null){
                //需要灵活配置，写在配置文件
                String testUdfs = FileTransferUtils.getUrlPrefix();
                //提供完整路径给前端
                uploadRE.setName(testUdfs + resultVO.getOriginalName());
                //提供完整路径给前端
                uploadRE.setOriginalName(resultVO.getOriginalName());
            }

        } catch (IOException e) {
            logger.error("读取源文件失败", e);
        }

        return uploadRE;
    }

    private byte[] getByteArrayInputStreamResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = inputStream.read(b, 0, b.length)) != -1) {
            baos.write(b, 0, len);
        }
        byte[] buffer = baos.toByteArray();
        return buffer;
    }

    /**
     * base64上传文件
     * @param imageText
     * @return
     */
    public UploadRE imageToTextUpload(String imageText) {
        UDFSUploadResultVO resultVO;
        UploadRE uploadRE = new UploadRE();
        try {
            // 如果是FileInputStream类型，进行转换
            byte[] bytes = Base64.decodeBase64(imageText);
            UDFSUploadVO vo = new UDFSUploadVO();

            //如果客户端没有传名字，则自动设置文件名
            String md5 = DigestUtils.md5Hex(bytes);
            String fileType = FileTypeUtil.getFileTypeByStream(bytes);
            //此处必须传文件的格式作为后缀, 否则RPC调用失败
            vo.setName(md5 + "." + fileType);
            vo.setData(bytes);
            vo.setBusinessLine(BusinessLineEnum.FCAR);
            vo.setPermission(UDFSPermissionEnum.GLOBAL);
            resultVO = UDFSClient.upload(vo);

            if(resultVO != null){
                //需要灵活配置，写在配置文件
                String testUdfs = FileTransferUtils.getUrlPrefix();
                //提供完整路径给前端
                uploadRE.setName(testUdfs + resultVO.getOriginalName());
                //提供完整路径给前端
                uploadRE.setOriginalName(resultVO.getOriginalName());
            }

        } catch (Exception e) {
            logger.error("读取源文件失败", e);
        }
        return uploadRE;
    }

    public static InputStream base64ToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            byte[] bytes = Base64.decodeBase64(base64string);
            stream = new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }


}
