package com.cogu.shop.tools;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by C0gu on 2018/6/11.
 */
public class MD5Utils {

    public static String EncoderByMd5(String str) {
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            result = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e1) {
            System.out.println(e1.getMessage());
        } catch (UnsupportedEncodingException e2) {
            System.out.println(e2.getMessage());
        }
        return result;
    }


}/* public static void main(String[] args) throws Exception{
        System.out.println(MD5Utils.EncoderByMd5("4QrcOUm6Wau+VuBX8g+IPg=="));
    }
*/
