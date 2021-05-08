package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.dao.FavoriteMapper;
import com.cogu.shop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 9:28
 * @Description:
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public Integer saveFavorite(Integer uid, Integer gid) {
        Integer result = 0;
        try {
            result = favoriteMapper.saveFavorite(uid, gid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer deleteFavorite(Integer uid, Integer gid) {
        Integer result = 0;
        try {
            result = favoriteMapper.deleteFavorite(uid, gid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer findFavorite(Integer uid, Integer gid) {
        return favoriteMapper.findFavorite(uid, gid);
    }

    @Override
    public List<Integer> findAllFavoriteByUid(Integer uid) {
        return favoriteMapper.findAllFavoriteByUid(uid);
    }

    @Override
    public List<Integer> findAllFavoriteByUidWithLimit(Integer uid, Integer page) {
        return favoriteMapper.findAllFavoriteByUidWhirLimit(uid, page * 5);
    }

    @Override
    public List<Integer> findAllFavoriteByGid(Integer gid) {
        return favoriteMapper.findAllFavoriteByGid(gid);
    }
}
