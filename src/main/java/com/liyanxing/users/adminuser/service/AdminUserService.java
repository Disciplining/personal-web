package com.liyanxing.users.adminuser.service;

import com.liyanxing.users.adminuser.pojo.AdminUser;

import java.util.List;

public interface AdminUserService
{
    /**
     * 查询所有管理员用户的所有信息
     * @return
     */
    List<AdminUser> selectAllAdminUser();

    /**
     * 根据名称查询一个管理员
     * @param name
     * @return
     */
    AdminUser selectAbyName(String name);
}