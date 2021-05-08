package com.cogu.shop.dao.provider;

import com.cogu.shop.entity.vo.Role;

/**
 * @Author: Cogu
 * @Date: 2018/7/6 10:15
 * @Description:
 */
public class RoleProvider {

    public String saveRoleUrl(Integer rid, Integer... fid) {
        StringBuilder stringBuilder = new StringBuilder("insert into role_function " +
                "(rid,fid) values ");
        for (int i = 0, total = fid.length; i < total; i++) {
            if (fid[i] != 0) {
                stringBuilder.append("(" + rid + "," + fid[i] + ")");
                if (i < total - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        return stringBuilder.toString();
    }

    public String updateRole(Role role) {
        StringBuilder stringBuilder = new StringBuilder("update role set ");
        if (role.getRolename() != null && !role.getRolename().equals("")) {
            stringBuilder.append(" rolename = '" + role.getRolename() + "' , ");
        }
        if (role.getRdesc() != null && !role.getRdesc().equals("")) {
            stringBuilder.append(" rdesc = '" + role.getRdesc() + "' , ");
        }
        if (role.getRchild() != null) {
            stringBuilder.append(" rchild = " + role.getRchild() + " , ");
        } else {
            stringBuilder.append(" rchild = 0 ,");
        }
        stringBuilder.append(" updatetime  =now() ");
        stringBuilder.append("  where rid = " + role.getRid() + " ");
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

}
