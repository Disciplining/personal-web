package com.liyanxing.controller;

import com.liyanxing.tables.commonuser.pojo.CommonUser;
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
            CommonUser commonUser = (CommonUser) subject.getPrincipal();

            model.addAttribute("user_name",commonUser.getName());
        }
        catch (NullPointerException e)
        {
            //空指针异常，没有用户登录
        }

        return "index";
    }
}