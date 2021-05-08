package com.cogu.shop.controller;

import com.cogu.shop.entity.vo.Category;
import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.entity.vo.User;
import com.cogu.shop.service.CategoryService;
import com.cogu.shop.service.FavoriteService;
import com.cogu.shop.service.GoodsService;
import com.cogu.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cogu on 2018/6/17.
 */
@Controller
public class HtmlController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("/main")
    public ModelAndView indexMain() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Goods> goodsList = goodsService.findHotGoods();
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        modelAndView.addObject("category", categoryList);
        for (Goods goods : goodsList) {
            Category category = categoryService.findCategoryByCid(goods.getGtypeid());
            goods.setCategory(category);
        }
        modelAndView.addObject("goods", goodsList);
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "cid", required = false) Integer cid,
                              @RequestParam(value = "gname", required = false) String gname) {
        ModelAndView modelAndView = new ModelAndView("product");
        List<Category> categoryList = categoryService.findAllParentCategory();
        List<Goods> goodsList = null;
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        modelAndView.addObject("category", categoryList);
        if (request.getSession().getAttribute("u_page") == null) {
            request.getSession().setAttribute("u_page", 0);
        }
        if (request.getSession().getAttribute("u_cid") == null) {
            request.getSession().setAttribute("u_cid", 0);
        }
        if (request.getSession().getAttribute("u_action") == null) {
            request.getSession().setAttribute("u_action", 0);
        }
        if (cid != null) {
            request.getSession().setAttribute("u_cid", cid);
            request.getSession().setAttribute("u_action", 1);
        }
        if (page != null) {
            request.getSession().setAttribute("u_page", page);
            request.getSession().setAttribute("u_action", 0);
        }
        if (gname != null) {
            request.getSession().setAttribute("u_gname", gname);
            request.getSession().setAttribute("u_action", 2);
        }
        switch ((Integer) request.getSession().getAttribute("u_action")) {
            case 0:
                Integer u_page = (Integer) request.getSession().getAttribute("u_page");
                if (u_page >= 5) {
                    modelAndView.addObject("pageOut", 1);
                }
                goodsList = goodsService.findLimitGoods(u_page * 6, 6);
                break;
            case 1:
                Integer u_cid = (Integer) request.getSession().getAttribute("u_cid");
                goodsList = goodsService.findGoodsByGtypeid(u_cid);
                break;
            case 2:
                String u_gname = (String) request.getSession().getAttribute("u_gname");
                goodsList = goodsService.findLikeGoodsByGname(u_gname);
                break;
            default:
        }
        for (Goods goods : goodsList) {
            Category category = categoryService.findCategoryByCid(goods.getGtypeid());
            goods.setCategory(category);
        }
        modelAndView.addObject("goods", goodsList);
        User user = (User) request.getSession().getAttribute("cur_user");
        if (user == null) {
            modelAndView.addObject("flag", 0);
        } else {
            modelAndView.addObject("flag", 1);
            modelAndView.addObject("username", user.getRealname());
        }
        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView product() {
        ModelAndView modelAndView = new ModelAndView("product");
        List<Goods> goodsList = goodsService.findLimitGoods(0, 6);
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        modelAndView.addObject("category", categoryList);
        for (Goods goods : goodsList) {
            Category category = categoryService.findCategoryByCid(goods.getGtypeid());
            goods.setCategory(category);
        }
        modelAndView.addObject("goods", goodsList);
        return modelAndView;
    }

    @PostMapping("/dologin")
    public ModelAndView userLogin(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  HttpServletRequest request) {
        User user = userService.findUser(username.trim(), password.trim());
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            if (request.getSession().getAttribute("cur_user") == null) {
                request.getSession().setAttribute("cur_user", user);
            }
            modelAndView = index(request, null, null, null);
            return modelAndView;
        } else {
            modelAndView.addObject("message", "账号或密码错误");
            modelAndView.setViewName("/login");
            return modelAndView;
        }

    }

    @PostMapping("/save")
    public ModelAndView userSave(@ModelAttribute("User") User user, HttpServletRequest request) {
        Integer result = userService.saveUser(user);
        ModelAndView modelAndView = new ModelAndView();
        if (result == 1) {
            modelAndView = index(request, null, null, null);
            return modelAndView;
        } else {
            modelAndView.setViewName("/register");
            modelAndView.addObject("message", "注册失败");
            return modelAndView;
        }
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        modelAndView.addObject("category", categoryList);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        modelAndView.addObject("category", categoryList);
        return modelAndView;
    }

    @GetMapping("/password")
    public ModelAndView password() {
        ModelAndView modelAndView = new ModelAndView("password");
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        modelAndView.addObject("category", categoryList);
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return index(request, null, null, null);
    }

    @GetMapping("/item/{gid}")
    public ModelAndView goodsItem(@PathVariable("gid") Integer gid,
                                  HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("single");
        Goods goods = goodsService.findGoodsByGid(gid);
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        if (httpServletRequest.getSession().getAttribute("cur_user") != null) { //猜你喜欢
            User user = (User) httpServletRequest.getSession().getAttribute("cur_user");
            List<Integer> fids = favoriteService.findAllFavoriteByUidWithLimit(user.getUid(), 0);
            List<Goods> goodsList = new ArrayList<>();
            for (Integer fid : fids) {
                Goods goods1 = goodsService.findGoodsByGid(fid);
                goodsList.add(goods1);
            }
            modelAndView.addObject("goodsList", goodsList);
        } else {
            List<Goods> goodsList = goodsService.findLimitGoods(0, 4);
            modelAndView.addObject("goodsList", goodsList);
        }
        modelAndView.addObject("category", categoryList);
        modelAndView.addObject("goods", goods);
        return modelAndView;
    }

    @GetMapping("/price/{price}")
    public ModelAndView findGoodsByPrice(@PathVariable("price") String price) {
        ModelAndView modelAndView = new ModelAndView("product");
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
        }
        List<Goods> goodsList = goodsService.findGoodsWithMultLimit(null, null, 1, price);
        for (Goods goods : goodsList) {
            Category category = categoryService.findCategoryByCid(goods.getGtypeid());
            goods.setCategory(category);
        }
        modelAndView.addObject("goods", goodsList);
        modelAndView.addObject("category", categoryList);
        return modelAndView;
    }

}
