package com.cogu.shop.controller;

import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.entity.vo.Category;
import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.service.CategoryService;
import com.cogu.shop.service.GoodsService;
import com.cogu.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@CrossOrigin("*")
@RestController
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @GetMapping("/nameCheck")
    public RestResult<Integer> userNameCheck(@RequestParam("username") String username) {
        Integer result = userService.findUserByUsername(username.trim());
        if (result == 1) {
            return new RestResult<>(201, result, "用户名已存在");
        } else if (result == 0) {
            return new RestResult<>(200, result, "用户名可用");
        } else {
            return new RestResult<>(500, result, "发生未知错误");
        }
    }

    @GetMapping("/emailCheck")
    public RestResult<Integer> userEmailCheck(@RequestParam("email") String email) {
        Integer result = userService.findEmailCount(email.trim());
        if (result == 1) {
            return new RestResult<>(201, result, "邮箱已存在");
        } else if (result == 0) {
            return new RestResult<>(200, result, "邮箱可用");
        } else {
            return new RestResult<>(500, result, "发生未知错误");
        }
    }

    @GetMapping("/getAll")
    public RestResult<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.findAllCategory(0, 100);
        if (categoryList != null && categoryList.size() > 0) {
            return new RestResult<>(200, categoryList, "获取成功");
        } else {
            return new RestResult<>(200, null, "类别为空");
        }
    }

    @GetMapping("/get")
    public RestResult<List<Goods>> getGoods(@RequestParam("cid") Integer cid) {
        List<Goods> goodsList = goodsService.findGoodsByGtypeid(cid);
        if (goodsList != null && goodsList.size() > 0) {
            return new RestResult<>(200, goodsList, "获取成功");
        } else {
            return new RestResult<>(200, null, "商品为空");
        }
    }

    @GetMapping("/search")
    public RestResult<List<Goods>> searchGoods(@RequestParam("gname") String gname) {
        List<Goods> goodsList = goodsService.findLikeGoodsByGname(gname.trim());
        if (goodsList != null && goodsList.size() > 0) {
            return new RestResult<>(200, goodsList, "获取成功");
        } else {
            return new RestResult<>(200, null, "没有包含" + gname + "的商品");
        }
    }


}
