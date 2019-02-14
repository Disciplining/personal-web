package com.liyanxing.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig
{
    /**
     * 这个方法关联一个安全管理器
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager); //关联安全管理器

        /**
         * 设置拦截URL
         */
        Map<String, String> map = new LinkedHashMap<>();

        map.put("/download*", "authc"); //用户登录后才可以下载资源
        map.put("/toMessagePage","roles[" + Roles.COMMON_USER + "]"); //普通用户才可以留言

        //管理员才可以修改资源
        map.put("/add*","roles[" + Roles.ADMINISTRATOR + "]");
        map.put("/delete*","roles[" + Roles.ADMINISTRATOR + "]");
        map.put("/modify*","roles[" + Roles.ADMINISTRATOR + "]");

        map.put("/showMessage","roles[" + Roles.ADMINISTRATOR + "]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        return  shiroFilterFactoryBean;
    }

    /**
     * 获得一个安全管理器
     * 这个方法关联一个realm类
     * @param myRealm
     * @return
     */
    @Bean(name = "manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") MyRealm myRealm)
    {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm); //设置realm

        return manager;
    }

    /**
     * 获得一个realm类
     * @return
     */
    @Bean(name = "realm")
    public MyRealm getRealm()
    {
        return new MyRealm();
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }
}