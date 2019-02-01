package com.liyanxing.tables.commonuser.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public class UserRegister
{
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
        String[] strings = new String[]{ciphertext, salt};
        return strings;
    }
}