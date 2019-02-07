package com.liyanxing.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class Util
{
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
            saveDir.mkdir();
        }

        //构建文件对象，并随机命名
        String randomStr = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成32位的随机字符串,作为图片的名字。
        String picFormat = "";
        if(file.getOriginalFilename().indexOf('.') != -1)
        {
            picFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')); //文件的格式，带着“.”;
        }
        File picture = new File(dirPath + randomStr + picFormat);

        System.out.println("格式：" + picFormat);

        try
        {
            file.transferTo(picture);
        }
        catch (IOException e)
        {
            //上传失败
        }

        return randomStr + picFormat;
    }
}