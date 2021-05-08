package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.controller.entity.OrderLimit;
import com.cogu.shop.dao.GoodsMapper;
import com.cogu.shop.dao.OrderInfoMapper;
import com.cogu.shop.dao.OrderMapper;
import com.cogu.shop.dao.PurChaseMapper;
import com.cogu.shop.entity.dto.UserOrder;
import com.cogu.shop.entity.vo.*;
import com.cogu.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cogu on 2018/6/15.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private PurChaseMapper purChaseMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    private void DataInit(List<OrderInfo> orderInfoList) {
        for (OrderInfo orderInfo : orderInfoList) {
            Goods goods = goodsMapper.findGoodsByGid(orderInfo.getGid());
            goods.setGcount(orderInfo.getOfcount());
            goods.setGprice(orderInfo.getOfprice());
            orderInfo.setGoods(goods);
        }
    }

    @Override
    public Order findOrderByOid(Long oid) {
        Order order = orderMapper.findOrderByOid(oid);
        if (order != null) {
            List<OrderInfo> orderInfoList = orderInfoMapper.findAllByOid(oid);
            DataInit(orderInfoList);
            order.setOrderInfoList(orderInfoList);
        }
        return order;
    }

    @Override
    public Integer findOrderCount() {
        return orderMapper.findOrderCount();
    }

    @Override
    public List<Order> findOrderWithMultLimit(OrderLimit orderLimit) {

        List<Order> orderList = orderMapper.findOrderWithMultLimit(orderLimit);
        for(Order order : orderList){
            List<OrderInfo> orderInfoList = orderInfoMapper.findAllByOid(order.getOid());
            DataInit(orderInfoList);
            order.setOrderInfoList(orderInfoList);
        }
        return orderList;
    }

    @Override
    public List<Order> findOrderByUid(Integer uid) {
        return orderMapper.findOrderByUid(uid);
    }

    @Override
    public List<Order> findOrderByUidWithLimit(Integer uid, Integer page) {
        return orderMapper.findOrderByUidWithLimit(uid, page * 5);
    }

    @Override
    public List<Order> findAllOrderWithLimit(Integer page, Integer size) {
        List<Order> orderList = orderMapper.findAllOrderWithLimit(page, size);
        for (Order order : orderList) {
            List<OrderInfo> orderInfoList = orderInfoMapper.findAllByOid(order.getOid());
            DataInit(orderInfoList);
            order.setOrderInfoList(orderInfoList);
        }
        return orderList;
    }

    @Override
    @Transactional
    public Integer saveOrder(Order order, Integer... pid) {
        Integer result = 0;
        Boolean flag = true;
        List<PurChase> purChaseList = new ArrayList<>();
        List<Goods> goodsList = new ArrayList<>();
        PurChase purChase;
        Goods goods;
        for (Integer p : pid) { //获取每行购物车
            purChase = purChaseMapper.findPurchaseByPid(p);
            purChaseList.add(purChase);
        }
        for (PurChase purChase1 : purChaseList) { //对比购物车里的商品数和库存数
            goods = goodsMapper.findGoodsByGid(purChase1.getGid());
            goodsList.add(goods);
            if (goods.getGcount() < purChase1.getPcount()) { //库存不足
                flag = false;
            }
        }
        if (!flag) {
            return result;
        }
        try {
            for (int i = 0, total = purChaseList.size(); i < total; i++) {
                goods = goodsList.get(i);
                goods.setGcount(goods.getGcount() - purChaseList.get(i).getPcount());//库存减少
                goodsMapper.updateGoods(goods);//更新库存
                purChaseMapper.updatePurchaseState(pid[i]);//购物车内该商品状态变为等待
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOid(order.getOid());
                orderInfo.setGid(goods.getGid());
                orderInfo.setOfcount(purChaseList.get(i).getPcount());
                orderInfo.setOfprice(goods.getGprice());
                orderInfoMapper.saveOrderInfo(orderInfo); //保存订单项
            }
            result = orderMapper.saveOrder(order);//保存订单
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer updateOrder(UserOrder userOrder) {
        return null;
    }

    @Override
    @Transactional
    public Integer ChaseOrder(Long oid, User user) {
        Integer result = 0;
        List<OrderInfo> orderInfoList = orderInfoMapper.findAllByOid(oid);
        List<PurChase> purChaseList = purChaseMapper.findPurChaseByUid(user.getUid(), 2);//获得等待状态的购物车
        try {
            for (PurChase purChase : purChaseList) {
                for (OrderInfo orderInfo : orderInfoList) {
                    if (purChase.getGid() == orderInfo.getGid()) { // 找到该商品
                        purChaseMapper.endPurchase(purChase.getPid());//该购物车休眠
                    }
                }
            }
            result = orderMapper.updateOrderChase(oid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public Integer CancelOrder(Long oid, User user) {
        Integer result = 0;
        List<OrderInfo> orderInfoList = orderInfoMapper.findAllByOid(oid);
        List<PurChase> purChaseList = purChaseMapper.findPurChaseByUid(user.getUid(), 2);//获得等待状态的购物车
        try {
            for (PurChase purChase : purChaseList) {
                for (OrderInfo orderInfo : orderInfoList) {
                    if (purChase.getGid() == orderInfo.getGid()) { // 找到该商品
                        Goods goods = goodsMapper.findGoodsByGid(purChase.getGid());
                        goods.setGcount(goods.getGcount() + purChase.getPcount()); // 还原库存
                        goodsMapper.updateGoods(goods);
                        purChaseMapper.endPurchase(purChase.getPid());//该购物车休眠
                    }
                }
            }
            result = orderMapper.updateOrderCancel(oid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer removeOrder(Long oid) {
        Integer result = 0;
        try {
            result = orderMapper.removeOrder(oid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public Integer deleteOrder(Long oid) {
        orderInfoMapper.deleteOrderInfo(oid); //先删除订单项
        return orderMapper.deleteOrder(oid); //删除订单
    }
}
