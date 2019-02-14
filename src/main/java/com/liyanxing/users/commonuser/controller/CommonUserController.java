package com.liyanxing.users.commonuser.controller;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import com.liyanxing.users.commonuser.service.CommonUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 和普通用户相关的控制器
 */
@Controller("commonUserController")
@RequestMapping("/commonuser")
public class CommonUserController
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService service;

    /**
     * 查询所有普通用户的所有信息
     *
     * @return
     */
    @GetMapping("/selectAllCommonUser")
    @ResponseBody
    public List<CommonUser> selectAllCommonUser()
    {
        return service.selectAllCommonUser();
    }

    /**
     * 跳转到用户注册页面
     * @return
     */
    @GetMapping("/toRegister")
    public String toRegister()
    {
        return "register";
    }

    /**
     * 注销登录
     * @return
     */
    @GetMapping("/logout")
    public String logout()
    {
        SecurityUtils.getSubject().logout(); //注销登录
        return "redirect:/";
    }

    /**
     * 跳转到 密码修改 页面
     * @return
     */
    @GetMapping("/toChangePasswordPage")
    public String toChangePasswordPage()
    {
        return "changePassowrd";
    }
}