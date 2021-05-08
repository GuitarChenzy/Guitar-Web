package com.cogu.shop.dao;

import com.cogu.shop.controller.entity.Data;
import com.cogu.shop.controller.entity.GoodsData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/7 11:17
 * @Description:
 */
@Mapper
@Component
public interface MessageMapper {

    @Select("select sum(ofcount) from orderinfo " +
            "where gid in (" +
            "SELECT gid from goods where gtypeid = #{gtypeid})")
    Integer findOrderInfoSumByGtypeid(@Param("gtypeid") Integer gtypeid);

    @Select("select gname,sum(ofcount) as gnum,gcount " +
            "from orderinfo,goods " +
            "WHERE orderinfo.gid = goods.gid " +
            "GROUP BY orderinfo.gid " +
            "ORDER BY sum(ofcount) desc " +
            "limit 0,9 ")
    List<GoodsData> findGidAndOfcount();

    @Select("select ofcount as value,odatetime as name from orderinfo,torder  " +
            "where gid = #{gid} " +
            "and orderinfo.oid = torder.oid " +
            "GROUP BY odatetime ;")
    List<Data> findOfcountAndOdatetimeByGid(@Param("gid")Integer gid);

}
