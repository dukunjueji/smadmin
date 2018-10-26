package com.uc.training.smadmin.udfs.service;

import com.ycc.tools.upload.FileTransferUtils;
import com.uc.training.smadmin.utils.FileTypeUtil;
import com.zuche.framework.enums.BusinessLineEnum;
import com.zuche.framework.udfs.client.UDFSClient;
import com.zuche.framework.udfs.client.UDFSPermissionEnum;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import com.zuche.framework.udfs.client.upload.UDFSUploadVO;
import com.zuche.framework.utils.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;

/**
 * description: 文件上传服务
 *
 * @author yiqiang.du@ucarinc.com
 * @version 1.0
 * @date 2018/9/18 16:32
 */
@Service
public class UDFSFileService {

    private static final Logger logger = LoggerFactory.getLogger(UDFSFileService.class);

    /**
     * 上传文件
     *
     * @param name
     * @param file
     * @return 上传成功返回文件url地址，上传失败返回原因
     *  {"success":true,"message":"图片上传成功","data":{"names":{"ORIGINAL":"R__P__/QQ图片20180911104337.jpg"},"originalName":"R__P__/QQ图片20180911104337.jpg"}}
     */
    public UDFSUploadResultVO upload(String name, MultipartFile file) {
        UDFSUploadResultVO resultVO = null;
        try {
            // 如果是FileInputStream类型，进行转换
            InputStream in = file.getInputStream();
            byte[] bytes = getByteArrayInputStreamResource(in);
            UDFSUploadVO vo = new UDFSUploadVO();

            //如果客户端没有传名字，则自动设置文件名
            String md5 = DigestUtils.md5Hex(bytes);
            String fileType = FileTypeUtil.getFileTypeByStream(bytes);
            vo.setName(md5 + "." + fileType); //此处必须传文件的格式作为后缀, 否则RPC调用失败
            vo.setData(bytes);
            vo.setBusinessLine(BusinessLineEnum.UC);
            vo.setPermission(UDFSPermissionEnum.GLOBAL);
            resultVO =  UDFSClient.upload(vo);

            if(resultVO != null){
                String testUdfs = FileTransferUtils.getUrlPrefix(); //需要灵活配置，写在配置文件
                resultVO.addName("full_path", testUdfs + resultVO.getOriginalName()); //提供完整路径给前端
            }

            if(!StringUtils.isEmpty(name)){
                resultVO.addName("from_name", name); //前端发送改过来的请求标识，用于前端自己分辨该结果属于哪个请求
            }

        } catch (IOException e) {
            logger.error("读取源文件失败", e);
        }

        return resultVO;
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
     *
     * @param name
     * @param base64string
     * @return 上传成功返回文件url地址，上传失败返回原因
     *  {"success":true,"message":"图片上传成功","data":{"names":{"ORIGINAL":"R__P__/QQ图片20180911104337.jpg"},"originalName":"R__P__/QQ图片20180911104337.jpg"}}
     */
    public UDFSUploadResultVO base64Upload(String name, String base64string) {
        UDFSUploadResultVO resultVO = null;
        try {
            // 如果是FileInputStream类型，进行转换
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base64string);
            UDFSUploadVO vo = new UDFSUploadVO();

            //如果客户端没有传名字，则自动设置文件名
            String md5 = DigestUtils.md5Hex(bytes);
            String fileType = FileTypeUtil.getFileTypeByStream(bytes);
            vo.setName(md5 + "." + fileType); //此处必须传文件的格式作为后缀, 否则RPC调用失败
            vo.setData(bytes);
            vo.setBusinessLine(BusinessLineEnum.FCAR);
            vo.setPermission(UDFSPermissionEnum.GLOBAL);
            resultVO = (UDFSUploadResultVO) UDFSClient.upload(vo);

            if(resultVO != null){
                String testUdfs = FileTransferUtils.getUrlPrefix(); //需要灵活配置，写在配置文件
                resultVO.addName("full_path", testUdfs + resultVO.getOriginalName()); //提供完整路径给前端
                resultVO.addName("image_path", resultVO.getOriginalName()); //提供完整路径给前端
            }

            if(!StringUtils.isEmpty(name)){
                resultVO.addName("from_name", name); //前端发送改过来的请求标识，用于前端自己分辨该结果属于哪个请求
            }
        } catch (IOException e) {
            logger.error("读取源文件失败", e);
        }
        return resultVO;
    }

    public static InputStream base64ToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }
}
