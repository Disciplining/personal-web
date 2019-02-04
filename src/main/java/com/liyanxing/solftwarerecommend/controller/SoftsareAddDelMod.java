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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 软件的 增加、删除、修改
 */
@Controller("softwareCommendSoftsareAddDelMod")
public class SoftsareAddDelMod
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
    private SoftwareRecommendService service;

    /**
     * 增加一个软件
     * 接收表单数据，处理后存入数据库
     * @param pic
     * @param name
     * @param introduction
     * @param officialWeb
     * @return
     */
    @PostMapping("/addAsoftware")
    public String addAsoftware(MultipartFile pic, String name, String introduction, String officialWeb)
    {
        /*----------------------------------------------------向磁盘存入图片----------------------------------------------------*/
        mainPicturePath = mainPicturePath.substring(mainPicturePath.indexOf(':')+1); // 存储用户上传图片的总目录路径，最后有个“/”.
        String childDirPath = mainPicturePath + softwareRecommendDir; //软件推荐的图片应该存入的子目录路径,最后有个“/”.

        //构建图片文件对象，并随机命名
        String randomStr = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成32位的随机字符串,作为图片的名字。
        String picFormat = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf('.')-1); //图片的格式，带着“.”;
        File picture = new File(childDirPath + randomStr + picFormat);

        //向磁盘存入图片
        try
        {
            pic.transferTo(picture);
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

        service.insertAsoftware(software);
        /*----------------------------------------------------------------------------------------------------------------------*/

        return "redirect:/toShowSoftware?currPage=1";
    }

    /**
     * 删除一个软件
     * @param id 要删除的软件的id
     * @return
     */
    @GetMapping("/deleteSoftWare")
    public String deleteSoftWare(@RequestParam(name = "id") int id)
    {
        //获得图片文件
        SoftwareRecommend software = service.selectAbyId(id);
        mainPicturePath = mainPicturePath.substring(mainPicturePath.indexOf(':')+1); // 存储用户上传图片的总目录路径，最后有个“/”.
        File picture = new File(mainPicturePath + software.getPic());

        if (picture.exists())
        {
            picture.delete(); //从磁盘中删除图片文件
        }

        service.deleteAbyId(id); //从数据库中删除这个软件

        return "redirect:/toShowSoftware?currPage=1";
    }

    /**
     * 修改软件信息
     * @param pic
     * @param name
     * @param introduction
     * @param officialWeb
     * @return
     */
    @PostMapping("/modifySoftware")
    public String modifySoftware(int id, String name, String introduction, String officialWeb)
    {
        SoftwareRecommend software = new SoftwareRecommend();
        software.setId(id);
        software.setName(name);
        software.setIntroduction(introduction);
        software.setOfficialWeb(officialWeb);

        service.modifySoftware(software);

        return "redirect:/toShowSoftware?currPage=1";
    }
}