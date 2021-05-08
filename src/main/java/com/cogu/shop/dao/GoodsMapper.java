package com.cogu.shop.dao;

import com.cogu.shop.dao.provider.GoodsProvider;
import com.cogu.shop.entity.vo.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@Mapper
@Component
public interface GoodsMapper {

    @Select("select * from goods where gid = #{gid}")
    Goods findGoodsByGid(@Param("gid") Integer gid);

    @Select("select DISTINCT gid,gname,gprice,gtypeid,gcount,gdesc,gimage,gstate,gdatetime " +
            "from goods,category " +
            "where gtypeid = #{cid} and gstate = 1 ")
    List<Goods> findGoodsByGtypeid(@Param("cid") Integer cid);

    @Select("select * from goods where gstate = 1")
    List<Goods> findAllGoods();

    @Select("select count(*) from goods ")
    Integer findGoodsCount();

    @Select("select * from goods limit #{startpage},#{gnumber}")
    List<Goods> findAllGoodsWithLimit(@Param("startpage") Integer startpage, @Param("gnumber") Integer gnumber);

    @Select("select * from goods where gstate = 1 limit #{startpage},#{gnumber}  ")
    List<Goods> findLimitGoods(@Param("startpage") Integer startpage, @Param("gnumber") Integer gnumber);

    @Select("select * from goods where gname like '%${gname}%' and gstate = 1 ")
    List<Goods> findLikeGoodsByGname(@Param("gname") String gname);

    @SelectProvider(type = GoodsProvider.class, method = "findLikeGoodsByGname")
    List<Goods> findGoodsWithMultLimit(Integer cid, String gname, Integer gstate, String price);

    @Select("SELECT goods.* FROM goods " +
            "LEFT JOIN orderinfo ON (goods.gid = orderinfo.gid) " +
            "GROUP BY goods.gid " +
            "ORDER BY sum(ofcount) DESC " +
            "LIMIT 0,9")
    List<Goods> findHotGoods();

    @Insert("insert into goods " +
            "(gname,gprice,gtypeid,gcount,gdesc,gimage,gstate,gdatetime) " +
            "values(#{goods.gname},#{goods.gprice},#{goods.gtypeid}," +
            "#{goods.gcount},#{goods.gdesc},#{goods.gimage},#{goods.gstate},now())")
    Integer saveGoods(@Param("goods") Goods goods) throws RuntimeException;

    @UpdateProvider(type = GoodsProvider.class, method = "updateGoods")
    Integer updateGoods(Goods goods) throws RuntimeException;

    @Delete("delete from goods where gid = #{gid}")
    Integer deleteGoods(@Param("gid") Integer gid) throws RuntimeException;

}
