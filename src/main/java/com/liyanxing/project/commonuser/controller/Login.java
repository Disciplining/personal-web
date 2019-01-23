package com.liyanxing.project.commonuser.controller;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.service.CommonUserService;
import com.liyanxing.project.commonuser.util.Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/commonuser")
public class Login
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService service;

    @PostMapping("/login")
    public String login(CommonUser commonUser, Model model)
    {
        CommonUser selectUser = service.selectAbyName(commonUser.getName()); //根据用户输入的用户名查询数据库中的用户
        if(selectUser == null) //用户不存在
        {
            model.addAttribute("no_account", "此用户不存在");
            return "login";
        }

        commonUser.setPassword(Util.encryptUserInputPassword(commonUser.getPassword(), selectUser.getSalt())); //重新设置密码

        Subject subject = SecurityUtils.getSubject(); //获得Subject对象
        UsernamePasswordToken token = new UsernamePasswordToken(commonUser.getName(), commonUser.getPassword()); //将用户输入的用户名与密码封装到一个UsernamePasswordToken对象中
        //用Subject对象执行登录方法，没有抛出任何异常说明登录成功
        try
        {
            subject.login(token); //login()方法会调用 Realm类中执行认证逻辑的方法，并将这个参数传递给doGetAuthenticationInfo()方法
            model.addAttribute("user_name", commonUser.getName());
            return "index";
        }
        catch (IncorrectCredentialsException e) //抛出这个异常说明密码错误
        {
            model.addAttribute("error_passwd", "密码错误");
            return "login";
        }
    }
}