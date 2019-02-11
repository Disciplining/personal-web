package com.liyanxing.users.adminuser.controller;

import com.liyanxing.users.adminuser.pojo.AdminUser;
import com.liyanxing.shiro.token.AdminUserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 * 管事员登录相关
 */
@Controller("adminUserLogin")
@RequestMapping("/adminUser")
public class Login
{
    /**
     * 管理员登录的表单数据提交到这控制器
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String login(AdminUser user, RedirectAttributesModelMap model)
    {
        UsernamePasswordToken token = new AdminUserToken(user.getName(), user.getPassword());

        Subject subject = SecurityUtils.getSubject();

        try
        {
            subject.login(token);
            return "redirect:/";
        }
        catch (UnknownAccountException e)
        {
            //此用户不存在
            model.addAttribute("noAccountAdmin", "此用户不存在");
            model.addAttribute("status","admin");
            return "redirect:/toLogin";
        }
        catch (IncorrectCredentialsException e)
        {
            //密码不正确
            model.addAttribute("errorPasswdAdmin", "密码错误");
            model.addAttribute("status","admin");
            return "redirect:/toLogin";
        }
    }
}