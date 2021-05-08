package com.cogu.shop.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cogu on 2018/6/14.
 */
public class OrderOid {

    private static long orderNum = 0l;
    private static String date;

    public static Long getOrderOid() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if (date == null || !date.equals(str)) {
            date = str;
            orderNum = 0l;
        }
        orderNum++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;
        return orderNo;
    }

}
