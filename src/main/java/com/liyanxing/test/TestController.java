package com.liyanxing.test;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试控制器
 */
@Controller
public class TestController
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService service;

    @GetMapping("/test")
    public String test(Model model)
    {
//        model.addAttribute("msg","此用户名已被注册!");

        return "login";
    }

    @GetMapping("/getRe")
    @ResponseBody
    public CommonUser getRe()
    {
        return service.selectAbyName("李艳兴");
    }
}