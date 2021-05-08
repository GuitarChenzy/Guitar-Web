package com.cogu.shop.service;

import com.cogu.shop.entity.vo.NumCoin;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 9:16
 * @Description:
 */
public interface NumCoinService {

    /**
     * 插入一条积分兑换商品
     * @param numCoin 积分商品对象
     * @return 1成功0失败
     */
    Integer saveNumCoin(NumCoin numCoin);

}
