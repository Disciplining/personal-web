package com.liyanxing.project.commonuser.controller;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.pojo.CommonUserParame;
import com.liyanxing.project.commonuser.service.CommonUserService;
import com.liyanxing.project.commonuser.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理用户的注册
 */
@Controller
@RequestMapping("/commonuser")
public class Register
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService service;

    @PostMapping("/register")
    @ResponseBody
    public String register(CommonUserParame user, BindingResult bindingResult)
    {
        CommonUser commonUser = new CommonUser();
        Util.parameToPojo(user, commonUser);

        return commonUser.toString();
    }
}