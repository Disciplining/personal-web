package com.liyanxing.controller;

import com.liyanxing.users.adminuser.pojo.AdminUser;
import com.liyanxing.users.commonuser.pojo.CommonUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String toLogin(@RequestParam(name = "noAccountCommon", defaultValue = "") String noAccountCommon,
                          @RequestParam(name = "noAccountAdmin", defaultValue = "") String noAccountAdmin,

                          @RequestParam(name = "errorPasswdCommon", defaultValue = "") String errorPasswdCommon,
                          @RequestParam(name = "errorPasswdAdmin", defaultValue = "") String errorPasswdAdmin,

                          @RequestParam(name = "status", defaultValue = "") String status,
                          Model model)
    {
        model.addAttribute("no_account_common", noAccountCommon);
        model.addAttribute("error_passwd_common", errorPasswdCommon);

        model.addAttribute("no_account_admin", noAccountAdmin);
        model.addAttribute("error_passwd_admin", errorPasswdAdmin);

        model.addAttribute("status", status);

        return "login";
    }
}