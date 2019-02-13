package com.liyanxing.carousel.controller;

import com.liyanxing.carousel.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("carouselCommonController")
public class CommonController
{
    @Autowired
    @Qualifier("photoServiceImpl")
    private PhotoService service;

    /**
     * 跳转到 支持我 页面
     * @return
     */
    @GetMapping("/toSupportMePate")
    public String toSupportMePate()
    {
        return "carousel/supportMe";
    }

    /**
     * 跳转到 关于 我页面
     * @return
     */
    @GetMapping("/toAboutMePage")
    public String toAboutMePage()
    {
        return "carousel/abloutMe";
    }

    /**
     * 跳转到 添加照片 页面
     * @return
     */
    @GetMapping("/toAddPhotoPage")
    public String toAddPhotoPage()
    {
        return "carousel/photo/addAphoto";
    }

    /**
     * 跳转到照片展示页面
     * @return
     */
    @GetMapping("/toShowPhotoPage")
    public String toShowPhotoPage(Model model)
    {
        model.addAttribute("data", service.selectAllPhoto());

        return "carousel/photo/showPhoto";
    }
}