package com.cogu.shop.service;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 9:16
 * @Description:
 */
public interface FavoriteService {

    /**
     * 根据商品id，用户id添加到收藏表中
     *
     * @param uid 用户id
     * @param gid 商品id
     * @return 1成功0失败
     */
    Integer saveFavorite(Integer uid, Integer gid);

    /**
     * 移除收藏
     *
     * @param uid 用户id
     * @param gid 商品id
     * @return 1成功0失败
     */
    Integer deleteFavorite(Integer uid, Integer gid);

    /**
     * 检验商品是已经收藏
     *
     * @param uid 用户id
     * @param gid 商品id
     * @return
     */
    Integer findFavorite(Integer uid, Integer gid);

    /**
     * 根据用户id得到所有收藏的商品id
     *
     * @param uid 用户id
     * @return 商品id集合
     */
    List<Integer> findAllFavoriteByUid(Integer uid);

    /**
     * 分页根据用户id得到所有收藏的商品id
     *
     * @param uid 用户id
     * @return 商品id集合
     */
    List<Integer> findAllFavoriteByUidWithLimit(Integer uid, Integer page);

    /**
     * 根据商品id得到所有收藏它的用户id
     *
     * @param gid 商品id
     * @return 用户id集合
     */
    List<Integer> findAllFavoriteByGid(Integer gid);

}
