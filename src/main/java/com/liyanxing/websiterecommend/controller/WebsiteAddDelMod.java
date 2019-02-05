package com.liyanxing.websiterecommend.controller;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.websiterecommend.pojo.Website;
import com.liyanxing.websiterecommend.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 网站 添加、删除、修改
 */
@Controller("websiteAddDelMod")
public class WebsiteAddDelMod
{
    @Autowired
    @Qualifier("websiteServiceImpl")
    private WebsiteService service;

    /**
     * 增加一个网站
     * 接收表单数据，处理后存入数据库
     * @param pic
     * @param name
     * @param introduction
     * @param officialWeb
     * @return
     */
    @PostMapping("/addAwebsite")
    public String addAwebsite(MultipartFile pic, String name, String introduction, String officialWeb)
    {
        Website website = new Website();
        website.setIntroduction(introduction);
        website.setName(name);
        website.setOfficialWeb(officialWeb);

        service.insertAwebsite(website, pic);

        return "redirect:/toShowWebsite?currPage=1";
    }

    /**
     * 删除一个网站
     * @param id
     * @return
     */
    @GetMapping("/deleteWebsite")
    public String deleteWebsite(@RequestParam(name = "id") int id)
    {
        service.deleteById(id);

        return "redirect:toShowWebsite?currPage=1";
    }

    /**
     * 修改一个网站
     * @param id
     * @param name
     * @param introduction
     * @param officialWeb
     * @return
     */
    @PostMapping("/modifyWebsite")
    public String modifySoftware(int id, String name, String introduction, String officialWeb)
    {
        Website website = new Website();
        website.setId(id);
        website.setName(name);
        website.setIntroduction(introduction);
        website.setOfficialWeb(officialWeb);

        System.out.println(website.toString());

        service.modifyWebsite(website);

        return "redirect:toShowWebsite?currPage=1";
    }
}