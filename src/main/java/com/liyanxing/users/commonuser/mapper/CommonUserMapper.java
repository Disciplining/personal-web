package com.liyanxing.users.commonuser.mapper;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("commonUserMapper")
public interface CommonUserMapper
{
    /**
     * 查询所有普通用户的所有信息
     * @return
     */
    @Select("select * from `common_user`")
    List<CommonUser> selectAllCommonUser();

    /**
     * 根据用户名查询一个用户
     * @param name
     * @return
     */
    @Select("select * from common_user where `name`=#{name}")
    CommonUser selectAbyName(String name);


    /**
     * 插入一个普通用户
     * @param commonUser
     */
    @Insert("insert into common_user (`name`,`password`,`salt`) values (#{name},#{password},#{salt})")
    void insertAcommonUser(CommonUser commonUser);
}