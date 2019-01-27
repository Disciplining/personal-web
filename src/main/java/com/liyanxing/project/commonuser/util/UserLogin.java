package com.liyanxing.project.commonuser.util;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.shiro.token.CommonUserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;

public class UserLogin
{
    /**
     * 加密用户登录时输入的密码，用于以后比较
     * @param input
     * @param salt
     * @return
     */
    public static String encryptUserInputPassword(String input, String salt)
    {
        String ciphertext = new Md5Hash(input,salt,3).toString(); //生成的密文
        return ciphertext;
    }

    /**
     * 用户登录函数，在controller里调用
     * @param user
     * @param model
     * @return
     */
    public static String userLogin(CommonUser user, Model model)
    {
        Subject subject = SecurityUtils.getSubject(); //获得Subject对象
        UsernamePasswordToken token = new CommonUserToken(user.getName(), user.getPassword()); //将用户输入的用户名写密码封装到一个UsernamePasswordToken对象中

        //用Subject对象执行登录方法，没有抛出任何异常说明登录成功
        try
        {
            subject.login(token); //login()方法会调用 Realm类中执行认证逻辑的方法，并将这个参数传递给doGetAuthenticationInfo()方法
            model.addAttribute("user_name", user.getName());
            return "index";
        }
        catch (IncorrectCredentialsException e) //抛出这个异常说明密码错误
        {
            model.addAttribute("error_passwd", "密码错误");
            return "login";
        }
    }
}