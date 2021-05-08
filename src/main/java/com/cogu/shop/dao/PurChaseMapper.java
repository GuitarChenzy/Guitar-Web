package com.cogu.shop.dao;

import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.entity.vo.PurChase;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
@Mapper
@Component
public interface PurChaseMapper {

    @Select("select * from purchase where pid = #{pid}")
    PurChase findPurchaseByPid(@Param("pid") Integer pid);

    @Select("select * from purchase where uid = #{uid} and pstate = #{pstate} ")
    List<PurChase> findPurChaseByUid(@Param("uid") Integer uid, @Param("pstate") Integer pstate);

    @Select("select * from purchase where uid = #{uid} and pstate = #{pstate} limit #{start},#{end}")
    List<PurChase> findPurChaseByUidWithLimit(@Param("uid") Integer uid, @Param("start") Integer start
            , @Param("end") Integer end, @Param("pstate") Integer pstate);

    @Select("select * from purchase where uid = #{uid} ")
    List<PurChase> findAllPurchaseByUid(@Param("uid") Integer uid);

    @Select("SELECT goods.gid,gtypeid,gname,gprice,pcount,gdesc,gimage,gdatetime " +
            "FROM goods,purchase " +
            "WHERE goods.gid = purchase.gid AND purchase.uid = #{uid} and pstate = #{pstate}")
    @Results({
            @Result(property = "gcount", column = "pcount")
    })
    List<Goods> findGoodsinPurChaseBypid(@Param("uid") Integer uid, @Param("pstate") Integer pstate);

    @Delete("delete from purchase where uid = #{uid} and pstate = 1")
    Integer deleteClearPurGoods(@Param("uid") Integer uid) throws RuntimeException;

    @Insert("insert into purchase(uid,gid,pdatetime,pcount,pstate) " +
            "values(#{purchase.uid},#{purchase.gid},now(),#{purchase.pcount},1)")
    Integer savePurchase(@Param("purchase") PurChase purChase) throws RuntimeException;

    @Update("update purchase set pdatetime = now(), pcount = #{purchase.pcount} " +
            "where pid = #{purchase.pid}")
    Integer updatePurchase(@Param("purchase") PurChase purChase) throws RuntimeException;

    @Delete("delete from purchase where pid = #{pid}")
    Integer deletePurchase(@Param("pid") Integer pid) throws RuntimeException;

    @Update("update purchase set pstate = 2 where pid = #{pid}")
    Integer updatePurchaseState(@Param("pid") Integer pid);

    @Update("update purchase set pstate = 0 where pid = #{pid}")
    Integer endPurchase(@Param("pid") Integer pid);

}
