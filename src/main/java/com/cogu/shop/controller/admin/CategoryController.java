package com.cogu.shop.controller.admin;

import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.entity.dto.WebCategory;
import com.cogu.shop.entity.vo.Category;
import com.cogu.shop.service.CategoryService;
import com.cogu.shop.tools.PageBean;
import com.cogu.shop.tools.UrlCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public ModelMap getCategory(@RequestParam("cid") Integer cid,
                                @SessionAttribute("url") List<String> url) {
        ModelMap modelMap = new ModelMap();
        if (!UrlCompare.findUrl(url, "/category/get")) {
            modelMap.addAttribute("code", 403);
            modelMap.addAttribute("message", "权限不够");
            return modelMap;
        }
        List<WebCategory> webCategoryList = new ArrayList<>();
        Category category = categoryService.findCategoryByCid(cid);
        if (category != null) {
            List<Category> categoryList1 = categoryService.findAllParentCategory();
            for (Category category1 : categoryList1) {
                if ((category.getParentid() == category1.getCid()) ||
                        (category.getParentid() == null && category.getCid() == category1.getCid())) {
                    WebCategory webCategory = new WebCategory();
                    webCategory.setCid(category.getCid());
                    webCategory.setCname(category.getCname());
                    webCategory.setF_canme(category1.getCname());
                    webCategoryList.add(webCategory);
                }
            }
        }
        if (category != null) {
            modelMap.addAttribute("code", 200);
            modelMap.addAttribute("rows", webCategoryList);
            return modelMap;
        } else {
            modelMap.addAttribute("code", 500);
            return modelMap;
        }
    }

    @PostMapping("/getAll")
    public ModelMap getAllCategory(@RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "rows", required = false) Integer size) {
        ModelMap modelMap = new ModelMap();
        PageBean pageBean = new PageBean(page, size);
        List<Category> categoryList = categoryService.findAllCategory(pageBean.getPage(), pageBean.getPageSize());
        List<WebCategory> webCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            WebCategory webCategory = new WebCategory();
            webCategory.setCid(category.getCid());
            webCategory.setCname(category.getCname());
            if (category.getParentid() != null) {
                Category category1 = categoryService.findCategoryByGtypeid(category.getParentid());
                webCategory.setF_canme(category1.getCname());
            } else {
                webCategory.setF_canme(category.getCname());
            }
            webCategoryList.add(webCategory);
        }
        Integer count = categoryService.findCount();
        if (webCategoryList.size() > 0) {
            modelMap.addAttribute("rows", webCategoryList);
            modelMap.addAttribute("total", count);
            return modelMap;
        } else {
            return null;
        }
    }

    @PostMapping("/get_parent")
    public RestResult<List<Category>> getAllParentCategory() {
        List<Category> categoryList = categoryService.findAllParentCategory();
        if (categoryList != null && categoryList.size() > 0) {
            return new RestResult<>(200, categoryList, "获取成功");
        } else {
            return new RestResult<>(201, null, "类别为空");
        }
    }

    @PostMapping("/add")
    public RestResult<Category> addCategory(@ModelAttribute("Category") Category category,
                                            @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/category/add")) {
            return new RestResult<>(403,null,"权限不够");
        }
        Integer result = categoryService.saveCategory(category);
        if (result == 1) {
            return new RestResult<>(200, category, "添加成功");
        } else {
            return new RestResult<>(500, null, "添加失败");
        }
    }

    @PostMapping("/update")
    public RestResult<Category> updateCategory(@ModelAttribute("Category") Category category,
                                               @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/category/update")) {
            return new RestResult<>(403,null,"权限不够");
        }
        Integer result = categoryService.updateCategory(category);
        if (result == 1) {
            return new RestResult<>(200, category, "更新成功");
        } else {
            return new RestResult<>(500, null, "更新失败");
        }
    }

    @PostMapping("/delete")
    public RestResult<Integer> deleteCategory(@RequestParam("cid") Integer cid,
                                              @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/category/delete")) {
            return new RestResult<>(403,null,"权限不够");
        }
        Integer result = categoryService.deleteCategory(cid);
        if (result == 1) {
            return new RestResult<>(200, null, "删除成功");
        } else {
            return new RestResult<>(500, null, "删除失败");
        }
    }

}
