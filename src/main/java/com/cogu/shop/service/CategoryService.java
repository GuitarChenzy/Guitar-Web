package com.cogu.shop.service;

import com.cogu.shop.entity.vo.Category;

import java.util.List;

/**
 * Created by COgu on 2018/6/12.
 */
public interface CategoryService {

    /**
     * 根据类别id获取类别
     *
     * @param cid 类别编号
     * @return 类别
     */
    Category findCategoryByCid(Integer cid);

    /**
     * 根据商品类别编号获取类别
     *
     * @param gtypeid 商品类别编号
     * @return 类别
     */
    Category findCategoryByGtypeid(Integer gtypeid);

    /**
     * 获取所有父类别
     *
     * @return 类别集合
     */
    List<Category> findAllParentCategory();

    /**
     * 获取所有子类别
     *
     * @return 类别集合
     */
    List<Category> findAllChildCategory();

    /**
     * 获取所有子类别
     *
     * @return 类别集合
     */
    List<Category> findAllChildCategoryByParentid(Integer parentid);

    /**
     * 获取所有类别
     *
     * @return 类别集合
     */
    List<Category> findAllCategory(Integer page, Integer size);

    /**
     * 获取类别总个数
     *
     * @return 类比个数
     */
    Integer findCount();

    /**
     * 保存类别
     *
     * @param category 类别
     * @return 1成功0失败
     */
    Integer saveCategory(Category category);

    /**
     * 更新类别
     *
     * @param category 类别
     * @return 1成功0失败
     */
    Integer updateCategory(Category category);

    /**
     * 根据类别id删除类别
     *
     * @param cid 类别id
     * @return 1成功0失败
     */
    Integer deleteCategory(Integer cid);


}
