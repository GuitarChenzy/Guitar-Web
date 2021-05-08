package com.cogu.shop.service;

import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.entity.vo.PurChase;

import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
public interface PurChaseService {

    /**
     *
     * @param pid
     * @return
     */
    PurChase findPurchaseByPid( Integer pid);

    /**
     *
     * @param uid
     * @param pstate
     * @return
     */
    List<PurChase> findPurChaseByUidWithLimit(Integer uid,Integer page, Integer pstate);

    /**
     *
     * @param uid
     * @param pstate
     * @return
     */
    List<PurChase> findPurChaseByUid(Integer uid,Integer pstate);

    /**
     *
     * @param uid
     * @return
     */
    List<PurChase> findAllPurchaseByUid(Integer uid);

    /**
     *
     * @param uid
     * @param pstate
     * @return
     */
    List<Goods> findGoodsinPurChaseByUid(Integer uid, Integer pstate);

    /**
     *
     * @param uid
     * @return
     */
    Integer deleteClearPurGoods(Integer uid);

    /**
     *
     * @param purChase
     * @return
     */
    Integer savePurchase(PurChase purChase);

    /**
     *
     * @param purChase
     * @return
     */
    Integer updatePurchase(PurChase purChase);

    /**
     *
     * @param pid
     * @return
     */
    Integer deletePurchase(Integer pid);

    /**
     *
     * @param pid
     * @return
     */
    Integer updatePurchaseState(Integer pid);

    /**
     *
     * @param pid
     * @return
     */
    Integer endPurchase(Integer pid);

}
