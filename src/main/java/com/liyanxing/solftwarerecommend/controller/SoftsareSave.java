package com.liyanxing.solftwarerecommend.controller;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.solftwarerecommend.service.SoftwareRecommendService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 表单数据会被提交到这个控制器
 */
@Controller("softwareCommendSoftsareSave")
public class SoftsareSave
{
    /**
     *存储用户上传图片的总目录
     */
    @Value("${cbs.imagesPath}")
    private  String mainPicturePath;

    /**
     * 软件推荐的图片应该存入的子目录
     */
    private String softwareRecommendDir = "softwareRecommend/";

    @Autowired
    @Qualifier("softwareRecommendServiceImpl")
    private SoftwareRecommendService softwareRecommendService;

    /**
     * 接收表单数据，处理后存入数据库
     * @param pic
     * @param name
     * @param introduction
     * @param officialWeb
     * @return
     */
    @PostMapping("/addAsoftware")
    @ResponseBody
    public String addAsoftware(MultipartFile pic, String name, String introduction, String officialWeb)
    {
        /*----------------------------------------------------向磁盘存入图片----------------------------------------------------*/
        mainPicturePath = mainPicturePath.substring(mainPicturePath.indexOf(':')+1); // 存储用户上传图片的总目录路径，最后有个“/”.
        String childDirPath = mainPicturePath + softwareRecommendDir; //软件推荐的图片应该存入的子目录路径,最后有个“/”.

        //构建图片文件对象，并随机命名
        String randomStr = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成32位的随机字符串,作为图片的名字。
        String picFormat = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf('.')-1); //图片的格式，带着“.”;
        File picFile = new File(childDirPath + randomStr + picFormat);

        //向磁盘存入图片
        try
        {
            pic.transferTo(picFile);
        }
        catch (IOException e)
        {
            //图片不存在
            //返回页面上传失败信息
        }
        /*------------------------------------------------------------------------------------------------------------------------*/

        /*----------------------------------------------------向数据库存入数据----------------------------------------------------*/
        SoftwareRecommend software = new SoftwareRecommend();
        software.setPic(softwareRecommendDir + randomStr + picFormat); //只将不包括总目录的部分存入数据库，这样，取出来返回给前端即可用，不需要再处理。
        software.setIntroduction(introduction);
        software.setName(name);
        software.setOfficialWeb(officialWeb);

        softwareRecommendService.insertAsoftware(software);
        /*----------------------------------------------------------------------------------------------------------------------*/

        return software.toString();
    }
}