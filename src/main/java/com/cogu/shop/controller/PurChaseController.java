package com.cogu.shop.controller;

import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.entity.vo.PurChase;
import com.cogu.shop.entity.vo.User;
import com.cogu.shop.service.PurChaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
@CrossOrigin("*")
@Controller
@RequestMapping("/purchase")
public class PurChaseController {

    @Autowired
    private PurChaseService purChaseService;

    @GetMapping("/get")
    public RestResult<List<PurChase>> getPurchase(@SessionAttribute("cur_user") User user) {
        List<PurChase> purChaseList = purChaseService.findPurChaseByUid(user.getUid(), 1);
        if (purChaseList != null && purChaseList.size() > 0) {
            return new RestResult<>(200, purChaseList, "购物车获取成功");
        } else {
            return new RestResult<>(201, null, "购物车获取失败");
        }
    }

    @GetMapping("/getall")
    public RestResult<List<Goods>> getGoods(@SessionAttribute("cur_user") User user) {
        List<Goods> goodsList = purChaseService.findGoodsinPurChaseByUid(user.getUid(), 1);
        if (goodsList != null && goodsList.size() > 0) {
            return new RestResult<>(200, goodsList, "购物车商品获取成功");
        } else {
            return new RestResult<>(201, null, "购物车内商品为空");
        }
    }

    @GetMapping("/add")
    public String addGoods(@SessionAttribute("cur_user") User user,
                           @RequestParam("gid") Integer gid,
                           @RequestParam("gcount") Integer gcount) {
        PurChase purChase = new PurChase();
        purChase.setUid(user.getUid());
        purChase.setGid(gid);
        purChase.setPcount(gcount);
        Integer result = purChaseService.savePurchase(purChase);
        return "redirect:/";
    }

    @PostMapping("/update")
    public RestResult<Integer> updateGoods(@ModelAttribute("Purchase") PurChase purChase) {
        Integer result = purChaseService.updatePurchase(purChase);
        if (result == 1) {
            return new RestResult<>(200, result, "更新成功");
        } else if (result == 2) {
            return new RestResult<>(201, result, "更新失败：库存不足");
        } else {
            return new RestResult<>(500, result, "更新失败：未知原因");
        }
    }

}
