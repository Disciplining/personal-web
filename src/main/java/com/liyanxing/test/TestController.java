package com.liyanxing.test;

import com.liyanxing.project.adminuser.service.AdminUserService;
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
    @Qualifier("adminUserServiceImpl")
    private AdminUserService service;

    @GetMapping("/test")
    @ResponseBody
    public String test()
    {
        return service.selectAbyName("管理员1").toString();
    }
}