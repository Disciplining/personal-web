package com.liyanxing.project.commonuser.controller;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 和普通用户相关的控制器
 */
@Controller
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
}