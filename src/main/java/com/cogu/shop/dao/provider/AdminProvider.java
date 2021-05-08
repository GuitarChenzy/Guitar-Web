package com.cogu.shop.dao.provider;

import com.cogu.shop.entity.vo.Admin;

/**
 * @Author: Cogu
 * @Date: 2018/7/6 16:07
 * @Description:
 */
public class AdminProvider {

    public String saveAdminRole(Integer aid, Integer... rid) {
        StringBuilder stringBuilder = new StringBuilder("insert into admin_role " +
                "(aid,rid,rstate) values ");
        for (int i = 0, total = rid.length; i < total; i++) {
            stringBuilder.append("(" + aid + "," + rid[i] + ",1)");
            if (i < total - 1) {
                stringBuilder.append(",");
            }
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String updateAdmin(Admin admin) {
        StringBuilder stringBuilder = new StringBuilder("update admin set ");
        if (admin.getRolename() != null && !admin.getRolename().equals("")) {
            stringBuilder.append(" rolename = '" + admin.getRolename() + "' , ");
        }
        if (admin.getPassword() != null && !admin.getPassword().equals("")) {
            stringBuilder.append(" password = '" + admin.getPassword() + "' , ");
        }
        stringBuilder.append(" username =  '" + admin.getUsername() + "' ");
        stringBuilder.append(" where aid = " + admin.getAid() + " ");
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
