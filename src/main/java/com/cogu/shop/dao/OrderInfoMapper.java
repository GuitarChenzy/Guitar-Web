package com.cogu.shop.dao;

import com.cogu.shop.entity.vo.OrderInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 22:58
 * @Description:
 */
@Mapper
@Component
public interface OrderInfoMapper {

    @Select("select * from orderinfo where oid = #{oid}")
    List<OrderInfo> findAllByOid(@Param("oid") Long oid);

    @Insert("insert into orderinfo (oid,gid,ofcount,ofprice) " +
            "values(#{o.oid},#{o.gid},#{o.ofcount},#{o.ofprice})")
    Integer saveOrderInfo(@Param("o") OrderInfo orderInfo);

    @Delete("delete from orderinfo where oid = #{oid}")
    Integer deleteOrderInfo(@Param("oid") Long oid);

    @Select("select * from orderinfo ")
    List<OrderInfo> findAllOrderInfo();

}
