package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.dao.GoodsMapper;
import com.cogu.shop.dao.PurChaseMapper;
import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.entity.vo.PurChase;
import com.cogu.shop.service.PurChaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
@Service
public class PurChaseServiceImpl implements PurChaseService {

    @Autowired
    private PurChaseMapper purChaseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PurChase findPurchaseByPid(Integer pid) {
        return purChaseMapper.findPurchaseByPid(pid);
    }

    @Override
    public List<PurChase> findPurChaseByUid(Integer uid, Integer pstate) {
        return purChaseMapper.findPurChaseByUid(uid, pstate);
    }

    @Override
    public List<PurChase> findPurChaseByUidWithLimit(Integer uid, Integer page, Integer pstate) {
        Integer start = page * 5;
        Integer end = 5;
        return purChaseMapper.findPurChaseByUidWithLimit(uid, start, end, pstate);
    }

    @Override
    public List<PurChase> findAllPurchaseByUid(Integer uid) {
        return purChaseMapper.findAllPurchaseByUid(uid);
    }

    @Override
    public List<Goods> findGoodsinPurChaseByUid(Integer uid, Integer pstate) {
        return purChaseMapper.findGoodsinPurChaseBypid(uid, pstate);
    }

    @Override
    public Integer deleteClearPurGoods(Integer uid) {
        Integer result = 0;
        try {
            List<PurChase> purChaseList = findPurChaseByUid(uid, 1);
            result = purChaseMapper.deleteClearPurGoods(uid);//删除未付款，未等待的购物车
            if (result == purChaseList.size()) {
                result = 1;
            } else {
                result = 0;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public Integer savePurchase(PurChase purChase) {
        Integer result = 0;
        try {
            Goods goods = goodsMapper.findGoodsByGid(purChase.getGid());
            if (goods.getGcount() < purChase.getPcount()) { // 判断库存是否够
                result = 2;
                return result;
            }
            Boolean flag = true;
            List<PurChase> purChaseList = purChaseMapper.findPurChaseByUid(purChase.getUid(), 1);
            for (PurChase p : purChaseList) {
                if (p.getGid() == purChase.getGid()) { //判断购物车里是否有还未支付的同样商品
                    purChase.setPid(p.getPid());
                    purChase.setPcount(purChase.getPcount() + p.getPcount());
                    result = purChaseMapper.updatePurchase(purChase);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result = purChaseMapper.savePurchase(purChase);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer updatePurchase(PurChase purChase) {
        Integer result = 0;
        try {
            Goods goods = goodsMapper.findGoodsByGid(purChase.getGid());
            if (goods.getGcount() < purChase.getPcount()) { // 判断库存是否够
                result = 2;
                return result;
            }
            result = purChaseMapper.updatePurchase(purChase);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer deletePurchase(Integer pid) {
        Integer result = 0;
        try {
            result = purChaseMapper.deletePurchase(pid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer updatePurchaseState(Integer pid) {
        Integer result = 0;
        try {
            result = purChaseMapper.updatePurchaseState(pid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer endPurchase(Integer pid) {
        Integer result = 0;
        try {
            result = purChaseMapper.endPurchase(pid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}