package com.cogu.shop.dao.provider;

import com.cogu.shop.entity.vo.Goods;

/**
 * @Author: Cogu
 * @Date: 2018/7/3 18:12
 * @Description:
 */
public class GoodsProvider {

    public String findLikeGoodsByGname(Integer cid, String gname,
                                       Integer gstate, String price) {
        StringBuffer sql = new StringBuffer("select * from goods where 1 = 1");

        if (gname != null && !gname.equals("")) {
            sql.append(" and gname like '%" + gname + "%' ");
        }

        if (cid != null) {
            sql.append(" and gtypeid= " + cid + " ");
        }
        if (gstate != null) {
            sql.append(" and gstate= " + gstate + " ");
        }
        if (price != null && !price.equals("")) {
            String[] p = price.split("-");
            if (p.length == 2) {
                sql.append(" and gprice > " + p[0] + " and gprice < " + p[1] + " ");
            } else {
                sql.append(" and gprice > " + p[0] + " ");
            }
        }
        return sql.toString();
    }

    public String updateGoods(Goods goods) {
        StringBuffer sql = new StringBuffer(
                "update goods set gname = '" + goods.getGname() + "' ");
        if (goods.getGprice() != null && goods.getGprice() != 0) {
            sql.append(",gprice = " + goods.getGprice() + " ");
        }
        if (goods.getGtypeid() != null && goods.getGtypeid() != 0) {
            sql.append(",gtypeid = " + goods.getGtypeid() + " ");
        }
        if (goods.getGcount() != null) {
            sql.append(",gcount = " + goods.getGcount() + " ");
        }
        if (goods.getGdesc() != null && !goods.getGdesc().equals("")) {
            sql.append(",gdesc = '" + goods.getGdesc() + "' ");
        }
        if (goods.getGimage() != null && !goods.getGimage().equals("")) {
            sql.append(",gimage = '" + goods.getGimage() + "' ");
        }
        if (goods.getGstate() != null) {
            sql.append(",gstate = " + goods.getGstate() + " ");
        }
        sql.append(" where gid = " + goods.getGid() + " ");
        return sql.toString();
    }

}
