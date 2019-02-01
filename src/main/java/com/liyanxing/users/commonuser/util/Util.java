package com.liyanxing.users.commonuser.util;

import com.liyanxing.users.commonuser.pojo.CommonUser;
import com.liyanxing.users.commonuser.pojo.CommonUserParame;

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