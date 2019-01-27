package com.liyanxing.project.adminuser.mapper;

import com.liyanxing.project.adminuser.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("adminUserMapper")
public interface AdminUserMapper
{
    /**
     * 查询所有管理员用户的所有信息
     * @return
     */
    @Select("select * from admin_user")
    List<AdminUser> selectAllAdminUser();

    /**
     * 根据名称查询一个管理员
     * @param name
     * @return
     */
    @Select("select * from admin_user where `name`=#{name}")
    AdminUser selectAbyName(String name);
}