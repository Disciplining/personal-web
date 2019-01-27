package com.liyanxing.project.commonuser.controller;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.service.CommonUserService;
import com.liyanxing.project.commonuser.util.UserLogin;
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

/**
 * 处理用户登录
 */
@Controller("commonUserLogin")
@RequestMapping("/commonuser")
public class Login
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService service;

    @PostMapping("/login")
    public String login(CommonUser commonUser, Model model)
    {
        CommonUser selectUser = service.selectAbyName(commonUser.getName());
        if(selectUser == null)
        {
            model.addAttribute("no_account", "此用户不存在");
            return "login";
        }

        //重新设置密码
        String s = UserLogin.encryptUserInputPassword(commonUser.getPassword(), selectUser.getSalt());
        commonUser.setPassword(s);

        return UserLogin.userLogin(commonUser, model);
    }
}