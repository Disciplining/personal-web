package com.liyanxing.controller;

import com.liyanxing.users.adminuser.pojo.AdminUser;
import com.liyanxing.users.commonuser.pojo.CommonUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController
{
    /**
     * 设置首页
     * @return
     */
    @GetMapping("/")
    public String index(Model model)
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Object user = subject.getPrincipal();

            if (user instanceof CommonUser)
            {
                CommonUser loginUser = (CommonUser) user;
                model.addAttribute("user_name",loginUser.getName());
            }
            else if (user instanceof AdminUser)
            {
                AdminUser loginUser = (AdminUser) user;
                model.addAttribute("user_name",loginUser.getName());
            }
        }
        catch (NullPointerException e)
        {
            //空指针异常，没有用户登录
        }

        return "index";
    }

    /**
     * 跳转到用户登录页面
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin()
    {
        return "login";
    }
}