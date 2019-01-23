package com.liyanxing.project.commonuser.util;

import com.liyanxing.project.commonuser.pojo.CommonUser;
import com.liyanxing.project.commonuser.pojo.CommonUserParame;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public class Util
{
    /**
     * 将实体参数对象的数据映射到实体对象上去
     * @param parame
     * @param user
     */
    public static void parameToPojo(CommonUserParame parame, CommonUser user)
    {
        user.setName(parame.getUserName());
        user.setPassword(parame.getPassword());
        if(parame.getSex() == null)
        {
            user.setSex(UserSex.UNKNOW);
        }
        else
        {
            if (parame.getSex().equals("man"))
            {
                user.setSex(UserSex.MAN);
            }
            else if (parame.getSex().equals("man"))
            {
                user.setSex(UserSex.WOMAN);
            }
            else
            {
                user.setSex(UserSex.UNKNOW);
            }
        }
        user.setBirthday(parame.getBirthday());
        user.setEmail(parame.getEmail());
    }
}