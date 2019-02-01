package com.liyanxing.tables.adminuser.service;

import com.liyanxing.tables.adminuser.mapper.AdminUserMapper;
import com.liyanxing.tables.adminuser.pojo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminUserServiceImpl")
public class AdminUserServiceImpl implements AdminUserService
{
    @Autowired
    @Qualifier("adminUserMapper")
    private AdminUserMapper mapper;

    /**
     * 查询所有管理员用户的所有信息
     *
     * @return
     */
    @Override
    public List<AdminUser> selectAllAdminUser()
    {
        return mapper.selectAllAdminUser();
    }

    /**
     * 根据名称查询一个管理员
     *
     * @param name
     * @return
     */
    @Override
    public AdminUser selectAbyName(String name)
    {
        return mapper.selectAbyName(name);
    }
}