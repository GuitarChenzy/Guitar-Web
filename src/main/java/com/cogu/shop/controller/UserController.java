package com.cogu.shop.controller;

import com.cogu.shop.entity.dto.UserOrder;
import com.cogu.shop.entity.dto.WebUser;
import com.cogu.shop.entity.vo.*;
import com.cogu.shop.service.*;
import com.cogu.shop.tools.OrderOid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/27 20:52
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PurChaseService purChaseService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ModelAndView userinfo(@SessionAttribute("cur_user") User user,
                                 @RequestParam(value = "state", required = false) Integer state,
                                 @RequestParam(value = "page", required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("user");
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        modelAndView.addObject("category", categoryList);
        if (state == null || state == 0) {
            state = 0;
            modelAndView.addObject("title", "个人信息");
        }
        if (page == null) {
            page = 0;
        }
        if (state == 2) { //订单
            List<Order> orderList = orderService.findOrderByUidWithLimit(user.getUid(), page);
            for (Order order : orderList) {
                List<OrderInfo> orderInfoList = orderInfoService.findAllByOid(order.getOid());
                for (OrderInfo orderInfo : orderInfoList) {
                    Goods goods = goodsService.findGoodsByGid(orderInfo.getGid());
                    goods.setGcount(orderInfo.getOfcount());
                    orderInfo.setGoods(goods);
                }
                order.setOrderInfoList(orderInfoList);
            }
            modelAndView.addObject("title", "订单");
            modelAndView.addObject("order", orderList);
        } else if (state == 1) { //购物车
            List<PurChase> purChaseList = purChaseService.findPurChaseByUidWithLimit(user.getUid(), page, 1);
            if (purChaseList != null && purChaseList.size() > 0) {
                for (PurChase p : purChaseList) {
                    Goods goods = goodsService.findGoodsByGid(p.getGid());
                    goods.setGcount(p.getPcount());
                    p.setGoods(goods);
                }
            }
            modelAndView.addObject("title", "购物车");
            modelAndView.addObject("purchase", purChaseList);
        } else if (state == 3) { //收藏
            List<Integer> gidlist = favoriteService.findAllFavoriteByUidWithLimit(user.getUid(), page);
            List<Goods> goodsList = new ArrayList<>();
            Goods goods;
            for (Integer gid : gidlist) {
                goods = goodsService.findGoodsByGid(gid);
                goodsList.add(goods);
            }
            modelAndView.addObject("title", "收藏夹");
            modelAndView.addObject("goodslist", goodsList);
        }
        modelAndView.addObject("user", user);
        modelAndView.addObject("state", state);
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteGoods(@SessionAttribute("cur_user") User user,
                                    @RequestParam(value = "pid", required = false) Integer pid,
                                    @RequestParam(value = "oid", required = false) Long oid) {
        Integer state = 0;
        if (pid != null) {
            purChaseService.deletePurchase(pid);
            state = 1;
        } else if (oid != null) {
            orderService.removeOrder(oid);
            state = 2;
        }
        return userinfo(user, state, 0);
    }

    @GetMapping("/chase")
    public ModelAndView updateOrder(@RequestParam("oid") Long oid,
                                    @SessionAttribute("cur_user") User user) {
        orderService.ChaseOrder(oid, user);
        return userinfo(user, 2, null);
    }

    @GetMapping("/cancel")
    public ModelAndView deleteOrder(@RequestParam("oid") Long oid,
                                    @SessionAttribute("cur_user") User user) {
        orderService.CancelOrder(oid, user);
        return userinfo(user, 2, 0);
    }

    @GetMapping("/addfav")
    public ModelAndView addFavorite(@SessionAttribute("cur_user") User user,
                                    @RequestParam("gid") Integer gid,
                                    @RequestParam("state") Integer state) {
        ModelAndView modelAndView = new ModelAndView("");
        if (favoriteService.findFavorite(user.getUid(), gid) != 1) {
            favoriteService.saveFavorite(user.getUid(), gid);
        }
        if (state == 1) {
            modelAndView.setViewName("forward:/main");
            return modelAndView;
        } else {
            return userinfo(user, 3, 0);
        }
    }

    @GetMapping("/remove")
    public ModelAndView removeFavorite(@SessionAttribute("cur_user") User user,
                                       @RequestParam("gid") Integer gid) {
        favoriteService.deleteFavorite(user.getUid(), gid);
        return userinfo(user, 3, 0);
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("webUser") WebUser webUser,
                                   @SessionAttribute("cur_user") User user,
                                   HttpServletRequest request) {
        user.setRealname(webUser.getRealname());
        user.setAdress(webUser.getAdress());
        user.setSex(webUser.getSex());
        user.setEmail(webUser.getEmail());
        Integer result = userService.updateUser(user);
        if (result == 1) {//更新Session
            request.getSession().setAttribute("cur_user", user);
        }
        return userinfo(user, null, 0);
    }

    @PostMapping("/add")
    public ModelAndView addOrder(@SessionAttribute("cur_user") User user,
                                 @ModelAttribute("userOrder") UserOrder userOrder,
                                 @RequestParam("pid") Integer... pid) {
        Order order = new Order();
        order.setOid(OrderOid.getOrderOid());
        order.setUid(user.getUid());
        order.setOprice(userOrder.getOprice());
        order.setOadress(userOrder.getOadress());
        order.setPaytype(userOrder.getPaytype());
        orderService.saveOrder(order, pid);
        return userinfo(user, 2, 0);
    }

    @GetMapping("/clear")
    public ModelAndView clearPurchase(@SessionAttribute("cur_user") User user) {
        purChaseService.deleteClearPurGoods(user.getUid());
        return userinfo(user, 1, 0);
    }

}
