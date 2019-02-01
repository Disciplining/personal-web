package com.liyanxing.shiro;

import com.liyanxing.tables.adminuser.pojo.AdminUser;
import com.liyanxing.tables.adminuser.service.AdminUserService;
import com.liyanxing.tables.commonuser.pojo.CommonUser;
import com.liyanxing.tables.commonuser.service.CommonUserService;
import com.liyanxing.shiro.token.AdminUserToken;
import com.liyanxing.shiro.token.CommonUserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MyRealm extends AuthorizingRealm
{
    @Autowired
    @Qualifier("commonUserServiceImpl")
    private CommonUserService commonUserService;

    @Autowired
    @Qualifier("adminUserServiceImpl")
    private AdminUserService adminUserService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Object currentUser = SecurityUtils.getSubject().getPrincipal(); //获得当前用户
        if (currentUser instanceof CommonUser)
        {
            info.addRole(Roles.COMMON_USER);
        }
        else if (currentUser instanceof AdminUser)
        {
            info.addRole(Roles.ADMINISTRATOR);
        }

        return info;
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
        if (authenticationToken instanceof CommonUserToken) //普通用户认证逻辑
        {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //获得用户输入的用户名,这个对象就是login()传递过来的，将它强转以取出封装的用户名
            String userNameInput = token.getUsername();

            CommonUser selectUser = commonUserService.selectAbyName(userNameInput); //根据用户输入的用户名查询数据库中的用户

            //下边就是用户存在的情况，只需要判断密码对不对
            //判断密码对不是只需要返回这样一个对象，第二个参数是数据库中正确的密码
            return new SimpleAuthenticationInfo(selectUser, selectUser.getPassword(), "");
        }
        else if (authenticationToken instanceof AdminUserToken)
        {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //获得用户输入的用户名,这个对象就是login()传递过来的，将它强转以取出封装的用户名
            String userNameInput = token.getUsername();

            AdminUser seelectUser = adminUserService.selectAbyName(userNameInput);

            if (seelectUser == null) //用户不存在
            {
                return null;
            }

            return new SimpleAuthenticationInfo(seelectUser, seelectUser.getPassword(), "");
        }
        else
        {
            return null;
        }
    }
}