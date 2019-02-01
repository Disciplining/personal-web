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
    public String login(AdminUser user, Model model)
    {
        UsernamePasswordToken token = new AdminUserToken(user.getName(), user.getPassword());

        Subject subject = SecurityUtils.getSubject();

        try
        {
            subject.login(token);
            model.addAttribute("user_name", user.getName());
            return "index";
        }
        catch (UnknownAccountException e)
        {
            //此用户不存在
            model.addAttribute("no_account", "此用户不存在");
            return "login";
        }
        catch (IncorrectCredentialsException e)
        {
            //密码不正确
            model.addAttribute("error_passwd", "密码错误");
            return "login";
        }
    }
}