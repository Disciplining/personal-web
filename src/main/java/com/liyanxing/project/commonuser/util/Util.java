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

    /**
     * 用户注册时加密用户的密码
     * 输入密码明文 返回密文与盐值
     * @param password
     * @return 第一个是密文  第二个是盐值
     */
    public static String[] encryptPassword(String password)
    {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        String ciphertext = new Md5Hash(password,salt,3).toString(); //生成的密文
        String[] strings = new String[]{salt, ciphertext};
        return strings;
    }
}