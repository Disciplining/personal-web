package com.liyanxing.users.commonuser.controller;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import com.liyanxing.users.commonuser.pojo.CommonUserParame;
import com.liyanxing.users.commonuser.service.CommonUserService;
import com.liyanxing.users.commonuser.util.UserLogin;
import com.liyanxing.users.commonuser.util.UserRegister;
import com.liyanxing.users.commonuser.util.Util;
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

import java.util.HashMap;
import java.util.Map;

/**
 * 处理用户的注册
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
     * @param commonUserParame
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    @Transactional
    public String register(CommonUserParame commonUserParame, BindingResult bindingResult, Model model)
    {
        CommonUser commonUser = new CommonUser();
        Util.parameToPojo(commonUserParame, commonUser);

        String[] passwdAnSalt = UserRegister.encryptPassword(commonUser.getPassword());
        commonUser.setPassword(passwdAnSalt[0]);
        commonUser.setSalt(passwdAnSalt[1]);

        service.insertAcommonUser(commonUser);

        return UserLogin.userLogin(commonUser, model); //注册后立马登录
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
}