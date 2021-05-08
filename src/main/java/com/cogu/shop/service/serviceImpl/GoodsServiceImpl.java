package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.dao.GoodsMapper;
import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods findGoodsByGid(Integer gid) {
        return goodsMapper.findGoodsByGid(gid);
    }

    @Override
    public List<Goods> findGoodsByGtypeid(Integer cid) {
        return goodsMapper.findGoodsByGtypeid(cid);
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsMapper.findAllGoods();
    }

    @Override
    public Integer findGoodsCount() {
        return goodsMapper.findGoodsCount();
    }

    @Override
    public List<Goods> findGoodsWithLimit(Integer startpage, Integer gnumber) {
        return goodsMapper.findAllGoodsWithLimit(startpage, gnumber);
    }

    @Override
    public List<Goods> findLimitGoods(Integer startpage, Integer gnumber) {
        if (startpage < 0 && gnumber < 0) {
            return null;
        }
        return goodsMapper.findLimitGoods(startpage, gnumber);
    }

    @Override
    public List<Goods> findGoodsWithMultLimit(Integer cid, String gname, Integer gstate, String price) {
        return goodsMapper.findGoodsWithMultLimit(cid, gname, gstate, price);
    }

    @Override
    public List<Goods> findLikeGoodsByGname(String gname) {
        if (gname.equals("") || gname == null) {
            return null;
        }
        return goodsMapper.findLikeGoodsByGname(gname);
    }

    @Override
    public List<Goods> findHotGoods() {
        return goodsMapper.findHotGoods();
    }

    @Override
    public Integer saveGoods(Goods goods) {
        Integer result = 0;
        try {
            result = goodsMapper.saveGoods(goods);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer updateGoods(Goods goods) {
        Integer result = 0;
        try {
            result = goodsMapper.updateGoods(goods);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer deleteGoods(Integer gid) {
        Integer result = 0;
        try {
            result = goodsMapper.deleteGoods(gid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
