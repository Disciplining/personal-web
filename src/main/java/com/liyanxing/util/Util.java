package com.liyanxing.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class Util
{
    /**
     * 从文件名中获得文件的扩展名
     * @param fileName 文件名
     * @return
     */
    public static String getFormar(final String fileName)
    {
        String format;

        if (fileName.indexOf('.') == -1) //文件名中没有“.” 没有扩展名
        {
            format ="";
        }
        else //文件名中有一个或多个"."
        {
            if (fileName.lastIndexOf('.') != 0) //只要最后一个点不是第一个字符,就有扩展名
            {
                format = fileName.substring(fileName.lastIndexOf('.')); //有扩展名
            }
            else //文件名第的最后一个 “.” 是第一个字符，没有扩展名
            {
                format = "";
            }
        }

        return format;
    }

    /**
     * 将文件存入磁盘
     * @param file 文件数据
     * @param dirPath 要存入的目录
     * @return 文件的名称，带扩展名
     */
    public static String save(MultipartFile file, String dirPath)
    {
        File saveDir = new File(dirPath);
        if (!saveDir.exists())
        {
            saveDir.mkdirs();
        }

        //构建文件对象，并随机命名
        String randomStr = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成32位的随机字符串,作为图片的名字。
        String format = getFormar(file.getOriginalFilename()); //文件的扩展名
        File picture = new File(dirPath + randomStr + format);

        try
        {
            file.transferTo(picture);
        }
        catch (IOException e)
        {
            //上传失败
        }

        return randomStr + format;
    }
}