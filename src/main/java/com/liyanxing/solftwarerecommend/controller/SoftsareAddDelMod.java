package com.liyanxing.solftwarerecommend.controller;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.solftwarerecommend.service.SoftwareRecommendService;
import com.liyanxing.util.SavePicture;
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
 * 软件的 添加、删除、修改
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

    /*----------------------------------------------------------------------------*/

    /**
     * 添加一个软件
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
        SoftwareRecommend software = new SoftwareRecommend();
        software.setIntroduction(introduction);
        software.setName(name);
        software.setOfficialWeb(officialWeb);

        service.insertAsoftware(software, pic);

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
        service.deleteAbyId(id);

        return "redirect:/toShowSoftware?currPage=1";
    }

    /**
     * 修改一个软件
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