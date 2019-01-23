package com.liyanxing.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Main
{
    public static void main(String[] args)
    {
        String input = "123123";
        String salt = "4a45fad8212a615395138fd85259c97c";
        String ciphertext = new Md5Hash(input,salt,3).toString(); //生成的密文
        System.out.println(ciphertext);
    }
}