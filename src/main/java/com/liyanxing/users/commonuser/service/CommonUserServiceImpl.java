package com.liyanxing.users.commonuser.service;

import com.liyanxing.users.commonuser.mapper.CommonUserMapper;
import com.liyanxing.users.commonuser.pojo.CommonUser;
import com.liyanxing.users.commonuser.util.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 修改普通用户的密码
     *
     * @param oldPassword
     * @param newPassword
     * @return 密码修改是否成功
     */
    @Override
    public boolean changePassword(String oldPassword, String newPassword)
    {
        CommonUser user = (CommonUser) SecurityUtils.getSubject().getPrincipal(); //获得当前登录的用户

        String encryptOldPassword = UserLogin.encryptUserInputPassword(oldPassword, user.getSalt()); //加密用户输入的旧密码

        if (encryptOldPassword.equals(user.getPassword())) //旧密码对
        {
            Map<String, Object> map = new HashMap<>(2);
            map.put("id", user.getCommonUserId());
            map.put("password", UserLogin.encryptUserInputPassword(newPassword, user.getSalt()));

            mapper.updateApassword(map);

            return true;
        }
        else //不对
        {
            return false;
        }
    }
}