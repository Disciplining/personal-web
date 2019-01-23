package com.liyanxing.shiro;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.service.CommonUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MyRealm extends AuthorizingRealm
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService service;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //获得用户输入的用户名,这个对象就是login()传递过来的，将它强转以取出封装的用户名
        String userNameInput = token.getUsername();

        CommonUser selectUser = service.selectAbyName(userNameInput); //根据用户输入的用户名查询数据库中的用户

        //下边就是用户存在的情况，只需要判断密码对不对
        //判断密码对不是只需要返回这样一个对象，第二个参数是数据库中正确的密码
        return new SimpleAuthenticationInfo(selectUser, selectUser.getPassword(), "");
    }
}