package com.cogu.shop.dao.provider;

import com.cogu.shop.controller.entity.OrderLimit;

/**
 * @Author: Cogu
 * @Date: 2018/7/4 14:43
 * @Description:
 */
public class OrderProvider {

    public String findOrderWithMultLimit(OrderLimit orderLimit) {
        StringBuffer sql = new StringBuffer("select * from torder where 1 = 1 ");
        if (orderLimit.getValue() != null && !orderLimit.getValue().equals("")) {
            sql.append("and uid in (select uid from user where username = '" + orderLimit.getValue() + "') ");
        }
        if (orderLimit.getStartTime() != null && !orderLimit.getStartTime().equals("")) {
            sql.append("and DATE_FORMAT(odatetime, '%Y-%m-%d %H:%i') >= " +
                    "DATE_FORMAT('" + orderLimit.getStartTime() + "','%Y-%m-%d %H:%i') ");
        }
        if (orderLimit.getEndTime() != null && !orderLimit.getEndTime().equals("")) {
            sql.append("and DATE_FORMAT(odatetime, '%Y-%m-%d %H:%i') <= " +
                    "DATE_FORMAT('" + orderLimit.getEndTime() + "','%Y-%m-%d %H:%i') ");
        }
        if (orderLimit.getState() != null) {
            sql.append("and state = " + orderLimit.getState() + "");
        }
        sql.append(" ORDER BY state ");
        return sql.toString();
    }

}
