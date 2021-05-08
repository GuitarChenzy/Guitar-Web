package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.dao.CategoryMapper;
import com.cogu.shop.entity.vo.Category;
import com.cogu.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findCategoryByCid(Integer cid) {
        return categoryMapper.findCategoryByCid(cid);
    }

    @Override
    public Category findCategoryByGtypeid(Integer gtypeid) {
        return categoryMapper.findCategoryByGtypeid(gtypeid);
    }

    @Override
    public List<Category> findAllParentCategory() {
        return categoryMapper.findAllParentCategory();
    }

    @Override
    public List<Category> findAllChildCategoryByParentid(Integer parentid) {
        return categoryMapper.findAllChildCategoryByParentid(parentid);
    }

    @Override
    public List<Category> findAllCategory(Integer page, Integer size) {
        return categoryMapper.findAllCategory(page, size);
    }

    @Override
    public List<Category> findAllChildCategory() {
        return categoryMapper.findAllChildCategory();
    }

    @Override
    public Integer findCount() {
        return categoryMapper.findCount();
    }

    @Override
    public Integer saveCategory(Category category) {
        Integer result = 0;
        try {
            result = categoryMapper.saveCategory(category);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer updateCategory(Category category) {
        Integer result = 0;
        try {
            result = categoryMapper.updateCategory(category);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Integer deleteCategory(Integer cid) {
        Integer result = 0;
        try {
            result = categoryMapper.deleteCategory(cid);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
