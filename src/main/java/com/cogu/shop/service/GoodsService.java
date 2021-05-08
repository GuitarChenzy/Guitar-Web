package com.cogu.shop.service;

import com.cogu.shop.entity.vo.Goods;

import java.util.List;

/**
 * Created by COgu on 2018/6/12.
 */
public interface GoodsService {

    /**
     * 根据商品id获取商品
     *
     * @param gid 商品id
     * @return 商品
     */
    Goods findGoodsByGid(Integer gid);

    /**
     * 根据类别id获取该类别下商品
     *
     * @param cid 类别id
     * @return 商品集合
     */
    List<Goods> findGoodsByGtypeid(Integer cid);

    /**
     * 获取所有商品
     *
     * @return 商品集合
     */
    List<Goods> findAllGoods();

    /**
     * 复杂查询
     *
     * @param cid    类别id
     * @param gname  商品名
     * @param gstate 商品状态
     * @param price  商品价格
     * @return 商品集合
     */
    List<Goods> findGoodsWithMultLimit(Integer cid, String gname, Integer gstate, String price);

    /**
     * 获取商品总数量
     *
     * @return 商品数量
     */
    Integer findGoodsCount();

    /**
     * 分页查询
     *
     * @param startpage 起始数
     * @param gnumber   查询个数
     * @return 商品集合
     */
    List<Goods> findGoodsWithLimit(Integer startpage, Integer gnumber);

    /**
     * 分页查询
     *
     * @param startpage 起始数
     * @param gnumber   查询个数
     * @return 商品集合
     */
    List<Goods> findLimitGoods(Integer startpage, Integer gnumber);

    /**
     * 根据商品名称模糊查询
     *
     * @param gname 关键字
     * @return 商品集合
     */
    List<Goods> findLikeGoodsByGname(String gname);

    /**
     * 获取热销商品
     *
     * @return 商品集合
     */
    List<Goods> findHotGoods();

    /**
     * 保存商品
     *
     * @param goods 商品
     * @return 1成功0失败
     */
    Integer saveGoods(Goods goods);

    /**
     * 更新商品
     *
     * @param goods 商品
     * @return 1成功0失败
     */
    Integer updateGoods(Goods goods);

    /**
     * 根据商品id删除商品
     *
     * @param gid 商品编号
     * @return 1成功0失败
     */
    Integer deleteGoods(Integer gid);

}
