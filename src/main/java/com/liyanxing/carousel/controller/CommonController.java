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
}