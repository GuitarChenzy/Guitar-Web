package com.cogu.shop.service;

import com.cogu.shop.controller.entity.Tree;
import com.cogu.shop.entity.vo.Turl;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 16:39
 * @Description:
 */
public interface UrlService {

    /**
     * 获取资源树
     * @return url集合
     */
    List<Tree> findUrlTree();

    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 数值
     * @return 资源集合
     */
    List<Turl> findTurl(Integer page, Integer size);

    /**
     * 获取资源总数
     *
     * @return
     */
    Integer findCount();

    /**
     * 保存
     *
     * @param turl 资源
     * @return 1成功0失败
     */
    Integer saveTurl(Turl turl);

    /**
     *  genxin
     * @param turl url
     * @return 1:0
     */
    Integer updateTurl(Turl turl);

    /**
     * 删除
     *
     * @param fid 资源id
     * @return 1成功0失败
     */
    Integer deleteTurl(Integer fid);

}
