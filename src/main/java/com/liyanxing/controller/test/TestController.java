package com.liyanxing.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试控制器
 */
@Controller
public class TestController
{
    @GetMapping("/test")
    @ResponseBody
    public String test()
    {
        return "hello word";
    }
}