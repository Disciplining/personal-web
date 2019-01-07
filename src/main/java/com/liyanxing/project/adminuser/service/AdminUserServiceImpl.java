package com.liyanxing.project.adminuser.service;

import com.liyanxing.project.adminuser.mapper.AdminUserMapper;
import com.liyanxing.project.adminuser.pojo.AdminUser;
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
}