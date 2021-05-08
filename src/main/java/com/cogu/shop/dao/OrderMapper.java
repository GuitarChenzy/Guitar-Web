package com.cogu.shop.dao;

import com.cogu.shop.controller.entity.OrderLimit;
import com.cogu.shop.dao.provider.OrderProvider;
import com.cogu.shop.entity.vo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
@Mapper
@Component
public interface OrderMapper {

    @Select("select * from torder where oid = #{oid}")
    Order findOrderByOid(@Param("oid") Long oid);

    @Select("select count(*) from torder")
    Integer findOrderCount();

    @SelectProvider(type = OrderProvider.class, method = "findOrderWithMultLimit")
    List<Order> findOrderWithMultLimit(OrderLimit orderLimit);

    @Select("select * from torder where uid = #{uid} where state <> 3 ORDER BY state")
    List<Order> findOrderByUid(@Param("uid") Integer uid);

    @Select("select * from torder where uid = #{uid} and state <> 3  ORDER BY state limit #{page},5 ")
    List<Order> findOrderByUidWithLimit(@Param("uid") Integer uid, @Param("page") Integer page);

    @Select("select * from torder limit #{page},#{size}")
    List<Order> findAllOrderWithLimit(@Param("page") Integer page, @Param("size") Integer size);

    @Insert("insert into torder(oid,uid,oadress,odatetime,oprice,paytype,state) " +
            "values(#{order.oid},#{order.uid},#{order.oadress}," +
            "now(),#{order.oprice},#{order.paytype},0)")
    Integer saveOrder(@Param("order") Order order) throws RuntimeException;

    @Update("update torder set oadress = #{order.oadress},oprice = #{order.oprice},paytype = #{order.paytype} " +
            "where oid = #{order.oid}")
    Integer updateOrder(@Param("order") Order order) throws RuntimeException;

    @Update("update torder set state = 1,updatetime = now() where oid = #{oid}")
    Integer updateOrderChase(@Param("oid") Long oid) throws RuntimeException;

    @Update("update torder set state = 2,updatetime = now() where oid = #{oid}")
    Integer updateOrderCancel(@Param("oid") Long oid) throws RuntimeException;

    @Update("update torder set state = 3 where oid = #{oid}")
    Integer removeOrder(@Param("oid") Long oid) throws RuntimeException;

    @Delete("delete from torder where oid = #{oid}")
    Integer deleteOrder(@Param("oid") Long oid) throws RuntimeException;

}
