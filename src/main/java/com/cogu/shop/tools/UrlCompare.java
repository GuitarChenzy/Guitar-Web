package com.cogu.shop.tools;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 11:29
 * @Description:
 */
public class UrlCompare {

    public static Boolean findUrl(List<String> strings, String str) {
        Boolean flag = false;
        for (String s : strings) {
            if (s.equals(str) || s.equals("/**")) {
                flag = true;
            }
        }
        return flag;
    }

}
