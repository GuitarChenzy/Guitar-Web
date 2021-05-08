package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.dao.OrderInfoMapper;
import com.cogu.shop.entity.vo.OrderInfo;
import com.cogu.shop.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/29 9:08
 * @Description:
 */
@Service
public class OderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> findAllByOid(Long oid) {
        return orderInfoMapper.findAllByOid(oid);
    }

    @Override
    public Integer saveOrderInfo(OrderInfo orderInfo) {
        return orderInfoMapper.saveOrderInfo(orderInfo);
    }

    @Override
    public List<OrderInfo> findAllOrderInfo() {
        return orderInfoMapper.findAllOrderInfo();
    }
}
