package com.liyanxing.users.commonuser.controller;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import com.liyanxing.users.commonuser.service.CommonUserService;
import com.liyanxing.users.commonuser.util.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
    public String login(CommonUser commonUser, RedirectAttributesModelMap model)
    {
        CommonUser selectUser = service.selectAbyName(commonUser.getName());
        if(selectUser == null)
        {
            model.addAttribute("noAccountCommon", "此用户不存在");
            model.addAttribute("status","common");
            return "redirect:/toLogin";
        }

        //重新设置密码
        String s = UserLogin.encryptUserInputPassword(commonUser.getPassword(), selectUser.getSalt());
        commonUser.setPassword(s);

        return UserLogin.userLogin(commonUser, model);
    }
}