package com.liyanxing.project.adminuser.controller;

import com.liyanxing.project.adminuser.pojo.AdminUser;
import com.liyanxing.project.adminuser.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminUserController
{
    @Autowired
    @Qualifier("adminUserServiceImpl")
    private AdminUserService service;

    /**
     * 查询所有用户的所有信息
     * @return
     */
    @GetMapping("/selectAllAdminUser")
    @ResponseBody
    public List<AdminUser> selectAllAdminUser()
    {
        return service.selectAllAdminUser();
    }
}