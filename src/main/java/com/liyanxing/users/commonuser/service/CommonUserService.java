package com.liyanxing.users.commonuser.service;

import com.liyanxing.users.commonuser.pojo.CommonUser;

import java.util.List;

public interface CommonUserService
{
    /**
     * 查询所有普通用户的所有信息
     * @return
     */
    List<CommonUser> selectAllCommonUser();

    /**
     * 根据用户名查询一个用户
     * @param name
     * @return
     */
    CommonUser selectAbyName(String name);

    /**
     * 插入一个普通用户
     * @param commonUser
     */
    void insertAcommonUser(CommonUser commonUser);
}