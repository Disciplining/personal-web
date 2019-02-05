package com.liyanxing.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 将用户上传的图片存入磁盘
 */
public class SavePicture
{
    /**
     * 存入图片
     * @param pic 图片数据
     * @param dirPath 要存入的目录
     * @return 图片的名称，带扩展名
     */
    public static String save(MultipartFile pic, String dirPath)
    {
        File saveDir = new File(dirPath);
        if (!saveDir.exists())
        {
            saveDir.mkdir();
        }

        //构建图片文件对象，并随机命名
        String randomStr = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成32位的随机字符串,作为图片的名字。
        String picFormat = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf('.')-1); //图片的格式，带着“.”;
        File picture = new File(dirPath + randomStr + picFormat);

        try
        {
            pic.transferTo(picture);
        }
        catch (IOException e)
        {
            //图片上传失败
        }

        return randomStr + picFormat;
    }
}