package com.ibm.ssnb.util;

public class BrowserUtil {

    public static boolean checkUserAgent(String userAgent) {

        boolean flg = true;
        userAgent = userAgent.toLowerCase();
        if (userAgent.indexOf("trident") != -1) {
            flg = false;
        }
        return flg;
    }
}
