package com.liyanxing.carousel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("carouselCommonController")
public class CommonController
{
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
}