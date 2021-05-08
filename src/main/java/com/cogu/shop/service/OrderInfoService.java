package com.cogu.shop.service;

import com.cogu.shop.entity.vo.OrderInfo;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 22:59
 * @Description:
 */
public interface OrderInfoService {

    /**
     * 根据订单id获取所有订单项
     *
     * @param oid 订单id
     * @return 订单项集合
     */
    List<OrderInfo> findAllByOid(Long oid);

    /**
     * 插入订单项
     *
     * @param orderInfo 订单项
     * @return 1成功0失败
     */
    Integer saveOrderInfo(OrderInfo orderInfo);

    /**
     * 查询所有订单项
     * @return
     */
    List<OrderInfo> findAllOrderInfo();

}
