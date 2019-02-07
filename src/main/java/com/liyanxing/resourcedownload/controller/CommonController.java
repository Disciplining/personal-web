package com.liyanxing.resourcedownload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("resourceDownloadCommonController")
public class CommonController
{
    /**
     * 跳转到资源添加页面
     * @return
     */
    @GetMapping("/toAddResourcePage")
    public String toAddResourcePage()
    {
        return "resourceDownload/addAresource";
    }
}