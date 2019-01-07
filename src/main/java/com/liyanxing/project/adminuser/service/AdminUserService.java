package com.liyanxing.project.adminuser.service;

import com.liyanxing.project.adminuser.pojo.AdminUser;

import java.util.List;

public interface AdminUserService
{
    /**
     * 查询所有管理员用户的所有信息
     * @return
     */
    List<AdminUser> selectAllAdminUser();
}