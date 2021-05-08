package com.cogu.shop.service;

import com.cogu.shop.controller.entity.OrderLimit;
import com.cogu.shop.entity.dto.UserOrder;
import com.cogu.shop.entity.vo.Order;
import com.cogu.shop.entity.vo.User;

import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
public interface OrderService {

    /**
     * 根据订单号查询订单
     *
     * @param oid 订单号
     * @return 订单
     */
    Order findOrderByOid(Long oid);

    /**
     * 获取订单总数
     *
     * @return 订单总数
     */
    Integer findOrderCount();

    /**
     * 根据用户id获取该用户所有订单
     *
     * @param uid 用户id
     * @return 用户所有订单
     */
    List<Order> findOrderByUid(Integer uid);

    /**
     * 复杂查询
     * @param orderLimit 插叙条件
     * @return 订单集合
     */
    List<Order> findOrderWithMultLimit(OrderLimit orderLimit);

    /**
     * 分页根据用户id获取该用户所有订单
     *
     * @param uid 用户id
     * @return 用户所有订单
     */
    List<Order> findOrderByUidWithLimit(Integer uid, Integer page);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 个数
     * @return 订单集合
     */
    List<Order> findAllOrderWithLimit(Integer page, Integer size);

    /**
     * 插入一个订单
     *
     * @param Order 订单
     * @return 1成功0失败2库存不足
     */
    Integer saveOrder(Order Order, Integer... pid);

    /**
     * 更新订单信息(订单不会被更新)
     *
     * @param userOrder 订单
     * @return 1成功0失败
     */
    Integer updateOrder(UserOrder userOrder);

    /**
     * 付款
     *
     * @param oid 订单号
     * @return 1成功0失败
     */
    Integer ChaseOrder(Long oid, User user);

    /**
     * 根据订单id取消订单
     *
     * @param oid 订单号
     * @return 1成功0失败
     */
    Integer CancelOrder(Long oid, User user);

    /**
     * 根据订单id移除订单
     *
     * @param oid 订单号
     * @return 1成功0失败
     */
    Integer removeOrder(Long oid);

    /**
     * 根据订单id删除订单
     * @param oid 订单号
     * @return 1成功0失败
     */
    Integer deleteOrder(Long oid);

}
