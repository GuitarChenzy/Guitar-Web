package com.cogu.shop.dao;

import com.cogu.shop.entity.vo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@Mapper
@Component
public interface CategoryMapper {

    @Select("select * from category where cid = #{cid}")
    Category findCategoryByCid(@Param("cid") Integer cid);

    @Select("select DISTINCT cid,cname,parentid from category,goods where cid = #{gtypeid}")
    Category findCategoryByGtypeid(@Param("gtypeid") Integer gtypeid);

    @Select("select * from category where parentid is NULL")
    List<Category> findAllParentCategory();

    @Select("select * from category where parentid = #{parentid}")
    List<Category> findAllChildCategoryByParentid(@Param("parentid") Integer parentid);

    @Select("select * from category where parentid is not NULL")
    List<Category> findAllChildCategory();

    @Select("select * from category limit #{page},#{size}")
    List<Category> findAllCategory(@Param("page") Integer page, @Param("size") Integer size);

    @Select("select count(*) from category")
    Integer findCount();

    @Insert("insert into category (cname,parentid) " +
            "values(#{category.cname},#{category.parentid})")
    Integer saveCategory(@Param("category") Category category);

    @Update("update category set cname = #{category.cname},parentid = #{category.parentid} " +
            "where cid = #{category.cid}")
    Integer updateCategory(@Param("category") Category category);

    @Delete("delete from category where cid = #{cid}")
    Integer deleteCategory(@Param("cid") Integer cid);

}
