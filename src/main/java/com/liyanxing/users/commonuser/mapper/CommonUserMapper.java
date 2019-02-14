package com.liyanxing.users.commonuser.mapper;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 修改一个用户的密码
     * @param para id：用户的id  password：新密码
     */
    @Update("update `common_user` set `password`=#{password} where `common_user_id`=#{id}")
    void updateApassword(Map<String,Object> para);
}