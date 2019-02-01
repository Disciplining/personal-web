package com.liyanxing.solftwarerecommend.controller;

import com.liyanxing.solftwarerecommend.pojo.SoftwareRecommend;
import com.liyanxing.solftwarerecommend.service.SoftwareRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("solftwareRecommendCommonController")
public class CommonController
{
    @Autowired
    @Qualifier("softwareRecommendServiceImpl")
    private SoftwareRecommendService softwareRecommendService;

    @GetMapping("/toAddAsoftware")
    public String toAddAsoftware()
    {
        return "softwareRecommend/addAsoftware";
    }

    @GetMapping("/toShowSoftware")
    public String toShowSoftware(Model model)
    {
        SoftwareRecommend softwareRecommend = softwareRecommendService.selectAbyId(1);

        model.addAttribute("pic", softwareRecommend.getPic());
        model.addAttribute("int", softwareRecommend.getIntroduction());

        return "softwareRecommend/showSoftware";
    }
}