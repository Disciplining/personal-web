package com.liyanxing.solftwarerecommend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller("solftwareRecommendCommonController")
public class CommonController
{
    @GetMapping("/toAddAsoftware")
    public String toAddAsoftware()
    {
        return "softwareRecommend/addAsoftware";
    }
}