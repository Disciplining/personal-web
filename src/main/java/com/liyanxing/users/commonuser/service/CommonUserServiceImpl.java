package com.liyanxing.users.commonuser.service;

import com.liyanxing.users.commonuser.mapper.CommonUserMapper;
import com.liyanxing.users.commonuser.pojo.CommonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commonUserServiceImpl")
public class CommonUserServiceImpl implements CommonUserService
{
    @Autowired
    @Qualifier("commonUserMapper")
    private CommonUserMapper mapper;

    /**
     * 查询所有普通用户的所有信息
     *
     * @return
     */
    @Override
    public List<CommonUser> selectAllCommonUser()
    {
        return mapper.selectAllCommonUser();
    }

    /**
     * 根据用户名查询一个用户
     *
     * @param name
     * @return
     */
    @Override
    public CommonUser selectAbyName(String name)
    {
        return mapper.selectAbyName(name);
    }

    /**
     * 插入一个普通用户
     *
     * @param commonUser
     */
    @Override
    public void insertAcommonUser(CommonUser commonUser)
    {
        mapper.insertAcommonUser(commonUser);
    }
}