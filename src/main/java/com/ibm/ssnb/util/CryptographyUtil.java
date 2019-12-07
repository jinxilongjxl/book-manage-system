package com.ibm.ssnb.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {

    public static void main(String[] args) {
        System.out.println(md5("123456", "java"));
    }

    public static String md5(String pwd, String salt) {

        return new Md5Hash(pwd, salt).toString();

    }

}
