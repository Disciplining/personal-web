package com.liyanxing.users.commonuser.controller;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import com.liyanxing.users.commonuser.service.CommonUserService;
import com.liyanxing.users.commonuser.util.UserLogin;
import com.liyanxing.users.commonuser.util.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理用户的注册相关内容
 */
@Controller("commonUserRegister")
@RequestMapping("/commonuser")
public class Register
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService service;

    /**
     * 用户的注册的表单将提交到这个控制器
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    @Transactional
    public String register(CommonUser user, BindingResult bindingResult, RedirectAttributesModelMap model)
    {
        String[] passwdAnSalt = UserRegister.encryptPassword(user.getPassword());
        user.setPassword(passwdAnSalt[0]);
        user.setSalt(passwdAnSalt[1]);

        service.insertAcommonUser(user);

        return UserLogin.userLogin(user, model); //注册后立马登录
    }

    /**
     * 接收表单的ajax查询用户名是否已被注册
     * 已被注册返回trure
     * 未被注册返回false
     * @param name
     * @return
     */
    @GetMapping("/hasReg")
    @ResponseBody
    public Map<String,Boolean> hasReg(String name)
    {
        Map<String,Boolean> map = new HashMap<>(1);
        if (service.selectAbyName(name) != null)
        {
            map.put("msg", true);
        }
        else
        {
            map.put("msg", false);
        }

        return map;
    }

    @PostMapping("/changePassword")
    public String changePassword(String oldPassword, String newPassword, Model model)
    {
        if (service.changePassword(oldPassword, newPassword)) //修改密码成功
        {
            return "redirect:/";
        }
        else
        {
            model.addAttribute("error", "旧密码输入错误");
            return "changePassowrd";
        }
    }
}