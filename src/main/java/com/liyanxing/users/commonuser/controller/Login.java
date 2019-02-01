package com.liyanxing.users.commonuser.controller;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import com.liyanxing.users.commonuser.service.CommonUserService;
import com.liyanxing.users.commonuser.util.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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