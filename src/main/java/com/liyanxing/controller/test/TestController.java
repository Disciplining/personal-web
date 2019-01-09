package com.liyanxing.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试控制器
 */
@Controller
public class TestController
{
    @GetMapping("/test")
    public String test()
    {
        return "register";
    }
}