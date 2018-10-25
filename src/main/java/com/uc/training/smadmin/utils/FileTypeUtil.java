package com.uc.training.smadmin.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yiqiang.du@ucarinc.com
 * @version 1.0
 * @description: 通过读取文件流判断文件格式
 * @date 2018/9/19 16:23
 */
public class FileTypeUtil {

    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    private FileTypeUtil() {
    }

    static {
        getAllFileType();  //初始化文件类型信息
    }

    /**
     * @description: 常见文件头信息
     * @author yiqiang.du@ucarinc.com
     * @date 2018/9/19 16:23
     */
    private static void getAllFileType() {
        //JPEG (jpg)
        FILE_TYPE_MAP.put("jpg", "FFD8FF");
        //PNG (png)
        FILE_TYPE_MAP.put("png", "89504E47");
        //GIF (gif)
        FILE_TYPE_MAP.put("gif", "47494638");
        //TIFF (tif)
        FILE_TYPE_MAP.put("tif", "49492A00");
        //Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("bmp", "424D");
        //CAD (dwg)
        FILE_TYPE_MAP.put("dwg", "41433130");
        //HTML (html)
        FILE_TYPE_MAP.put("html", "68746D6C3E");
        //Rich Text Format (rtf)
        FILE_TYPE_MAP.put("rtf", "7B5C727466");
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        //Photoshop (psd)
        FILE_TYPE_MAP.put("psd", "38425053");
        //Email [thorough only] (eml)
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");
        //Outlook Express (dbx)
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");
        //Outlook (pst)
        FILE_TYPE_MAP.put("pst", "2142444E");
        //MS Word
        FILE_TYPE_MAP.put("xls", "D0CF11E0");
        //MS Excel 注意：word 和 excel的文件头一样
        FILE_TYPE_MAP.put("doc", "D0CF11E0");
        //MS Access (mdb)
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");
        //WordPerfect (wpd)
        FILE_TYPE_MAP.put("wpd", "FF575043");
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        //Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("pdf", "255044462D312E");
        //Quicken (qdf)
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");
        //Windows Password (pwl)
        FILE_TYPE_MAP.put("pwl", "E3828596");
        //Wave (wav)
        FILE_TYPE_MAP.put("wav", "57415645");
        FILE_TYPE_MAP.put("avi", "41564920");
        //Real Audio (ram)
        FILE_TYPE_MAP.put("ram", "2E7261FD");
        //Real Media (rm)
        FILE_TYPE_MAP.put("rm", "2E524D46");
        FILE_TYPE_MAP.put("mpg", "000001BA");
        //Quicktime (mov)
        FILE_TYPE_MAP.put("mov", "6D6F6F76");
        //Windows Media (asf)
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");
        //MIDI (mid)
        FILE_TYPE_MAP.put("mid", "4D546864");
    }

    /**
     * @param f
     * @return fileType
     * @discription: 获取图片文件实际类型, 若不是图片则返回null
     * @author: yiqiang.du@ucarinc.com
     */
    public final static String getImageFileType(File f) {
        if (isImage(f)) {
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(f);
                Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
                if (!iter.hasNext()) {
                    return null;
                }
                ImageReader reader = iter.next();
                iis.close();
                return reader.getFormatName();
            } catch (IOException e) {
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * @param file
     * @return fileType
     * @discription: 获取文件类型, 包括图片, 若格式不是已配置的, 则返回null
     * @author: yiqiang.du@ucarinc.com
     */
    public final static String getFileByFile(File file) {
        String filetype = null;
        byte[] b = new byte[50];
        try {
            InputStream is = new FileInputStream(file);
            is.read(b);
            filetype = getFileTypeByStream(b);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filetype;
    }

    /**
     * @param b
     * @return fileType
     * @discription: 获取文件类型, 包括图片, 若格式不是已配置的, 则返回null
     * @author: yiqiang.du@ucarinc.com
     */
    public final static String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_MAP.entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getValue();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * @param file
     * @return true 是 | false 否
     * @discription: 判断文件是否为图片
     * @author: yiqiang.du@ucarinc.com
     */
    public static final boolean isImage(File file) {
        boolean flag = false;
        try {
            BufferedImage bufreader = ImageIO.read(file);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if (width == 0 || height == 0) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (IOException e) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @param b
     * @return fileTypeHex
     * @discription: getFileHexString
     * @author: yiqiang.du@ucarinc.com
     */
    public final static String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
