package com.liyanxing.project.commonuser.service;

import com.liyanxing.project.commonuser.pojo.CommonUser;

import java.util.List;

public interface CommonUserService
{
    /**
     * 查询所有普通用户的所有信息
     * @return
     */
    List<CommonUser> selectAllCommonUser();
}