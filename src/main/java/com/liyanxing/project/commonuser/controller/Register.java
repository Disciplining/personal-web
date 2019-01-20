package com.liyanxing.project.commonuser.controller;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.pojo.CommonUserParame;
import com.liyanxing.project.commonuser.service.CommonUserService;
import com.liyanxing.project.commonuser.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
@Controller
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
    @ResponseBody
    public String register(CommonUserParame commonUserParame, BindingResult bindingResult)
    {
        CommonUser commonUser = new CommonUser();
        Util.parameToPojo(commonUserParame, commonUser);

        String[] passwdAnSalt = Util.encryptPassword(commonUser.getPassword());
        commonUser.setPassword(passwdAnSalt[0]);
        commonUser.setSalt(passwdAnSalt[1]);

        service.insertAcommonUser(commonUser);

        return commonUser.toString();
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