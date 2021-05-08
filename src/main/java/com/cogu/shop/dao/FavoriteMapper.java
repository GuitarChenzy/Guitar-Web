package com.cogu.shop.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 9:15
 * @Description:
 */
@Mapper
@Component
public interface FavoriteMapper {

    @Insert("insert into favorite (uid,gid,addtime) " +
            "values(#{uid},#{gid},now()) ")
    Integer saveFavorite(@Param("uid") Integer uid, @Param("gid") Integer gid) throws RuntimeException;

    @Delete("delete from favorite where uid = #{uid} and gid = #{gid}")
    Integer deleteFavorite(@Param("uid") Integer uid, @Param("gid") Integer gid) throws RuntimeException;

    @Select("select count(*) from favorite where uid = #{uid} and gid = #{gid} ")
    Integer findFavorite(@Param("uid") Integer uid, @Param("gid") Integer gid);

    @Select("select gid from favorite where uid = #{uid}")
    List<Integer> findAllFavoriteByUid(@Param("uid") Integer uid);

    @Select("select gid from favorite where uid = #{uid} limit #{page},5")
    List<Integer> findAllFavoriteByUidWhirLimit(@Param("uid") Integer uid, @Param("page") Integer page);

    @Select("select uid from favorite where uid = #{gid}")
    List<Integer> findAllFavoriteByGid(@Param("uid") Integer gid);

}
