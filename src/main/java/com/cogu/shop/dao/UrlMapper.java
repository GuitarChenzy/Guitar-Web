package com.cogu.shop.dao;

import com.cogu.shop.entity.vo.Turl;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 10:34
 * @Description:
 */
@Mapper
@Component
public interface UrlMapper {

    @Select("select * from turl")
    List<Turl> findAllTurl();

    @Select("select count(*) from turl")
    Integer findCount();

    @Select("select * from turl where fid in " +
            "(select fid from role_function " +
            "where rid = #{rid})")
    List<Turl> findTurlByrid(@Param("rid") Integer rid);

    @Select("select * from turl limit #{startpage},#{gnumber}")
    List<Turl> findAllTurlWithLimit(@Param("startpage") Integer startpage, @Param("gnumber") Integer gnumber);

    @Insert("insert into turl (fname,furl,publictime) " +
            "values(#{turl.fname},#{turl.furl},now())")
    Integer saveTurl(@Param("turl") Turl Turl);

    @Update("update turl set fname = #{turl.fname}," +
            "furl = #{turl.furl},updatetime = now() " +
            "where fid = #{turl.fid}")
    Integer updateTurl(@Param("turl") Turl Turl);

    @Delete("delete from role_function where fid = #{fid}")
    Integer deleteTurlByFid(@Param("fid") Integer fid);

    @Delete("delete from turl where fid = #{fid}")
    Integer deleteTurl(@Param("fid") Integer fid);

}
