package com.cogu.shop.controller.admin;

import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.entity.dto.WebGoods;
import com.cogu.shop.entity.vo.Category;
import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.service.CategoryService;
import com.cogu.shop.service.GoodsService;
import com.cogu.shop.tools.PageBean;
import com.cogu.shop.tools.UrlCompare;
import com.cogu.shop.tools.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    private static final String filePath = "F:\\IDEA_project\\myshop\\src\\main\\resources\\static\\image\\goods\\";
    private static final String server_filePath = "/F:/IDEA_project/myshop/target/classes/static/image/goods/";

    private List<WebGoods> changeGoods(List<WebGoods> webGoodsList, List<Goods> goodsList) {
        if (goodsList.size() < 1) {
            return null;
        }
        for (Goods goods : goodsList) {
            WebGoods webGoods = new WebGoods();
            webGoods.setGid(goods.getGid());
            webGoods.setGname(goods.getGname());
            webGoods.setGprice(goods.getGprice());
            webGoods.setGtypename(categoryService.findCategoryByCid(goods.getGtypeid()).getCname());
            webGoods.setGcount(goods.getGcount());
            webGoods.setGdesc(goods.getGdesc());
            webGoods.setGimage(goods.getGimage());
            webGoods.setGdatetime(goods.getGdatetime());
            if (goods.getGstate() == 1) {
                webGoods.setGstate("上架");
            } else {
                webGoods.setGstate("下架");
            }
            webGoodsList.add(webGoods);
        }
        return webGoodsList;
    }

    @GetMapping("/get")
    public ModelMap getGoods(@RequestParam("name") String name,
                             @RequestParam("value") String value,
                             @RequestParam(value = "category", required = false) Integer cid,
                             @RequestParam(value = "checkState", required = false) Integer gstate,
                             @RequestParam(value = "price", required = false) String price,
                             @SessionAttribute("url") List<String> url) {
        ModelMap modelMap = new ModelMap();
        if (!UrlCompare.findUrl(url, "/goods/get")) {
            modelMap.addAttribute("code", 403);
            modelMap.addAttribute("message", "权限不够");
            return modelMap;
        }
        List<WebGoods> webGoodsList = new ArrayList<>();
        List<Goods> goodsList = new ArrayList<>();
        if (name.equals("gid")) {
            Goods goods = goodsService.findGoodsByGid(Integer.valueOf(value));
            if (goods != null) {
                goodsList.add(goods);
            }
        } else {
            goodsList = goodsService.findGoodsWithMultLimit(cid, value, gstate, price);
        }
        webGoodsList = changeGoods(webGoodsList, goodsList);

        if (webGoodsList != null && webGoodsList.size() > 0) {
            modelMap.addAttribute("code", 200);
            modelMap.addAttribute("rows", webGoodsList);
            modelMap.addAttribute("total", webGoodsList.size());
        } else {
            modelMap.addAttribute("code", 500);
            modelMap.addAttribute("message", "无此条件商品");
            modelMap.addAttribute("rows", null);
        }
        return modelMap;
    }

    @PostMapping("/getAll")
    public ModelMap getAllGoods(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "rows", required = false) Integer size) {
        ModelMap modelMap = new ModelMap();
        PageBean pageBean = new PageBean(page, size);
        List<Goods> goodsList = goodsService.findGoodsWithLimit(pageBean.getPage(), pageBean.getPageSize());
        Integer total = goodsService.findGoodsCount();
        List<WebGoods> webGoodsList = new ArrayList<>();
        webGoodsList = changeGoods(webGoodsList, goodsList);
        modelMap.addAttribute("rows", webGoodsList);
        modelMap.addAttribute("total", total);
        return modelMap;
    }

    @PostMapping("/get_parent")
    public RestResult<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.findAllChildCategory();
        if (categoryList != null && categoryList.size() > 0) {
            return new RestResult<>(200, categoryList, "获取成功");
        } else {
            return new RestResult<>(201, null, "类别为空");
        }
    }

    @GetMapping("/getInit")
    public RestResult<Goods> getGoodsInit(@RequestParam("gid") Integer gid) {
        Goods goods = goodsService.findGoodsByGid(gid);
        if (goods != null) {
            return new RestResult<>(200, goods, "获取成功");
        } else {
            return new RestResult<>(200, goods, "没有该商品");
        }
    }

    @PostMapping("/add")
    public RestResult<Goods> addGoods(@ModelAttribute("goods") Goods goods,
                                      @RequestParam("onlinefilename") MultipartFile file,
                                      @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/goods/add")) {
            return new RestResult<>(403, null, "权限不够");
        }
        String fileName = file.getOriginalFilename();
        String gimage = "/image/goods/" + fileName;
        goods.setGimage(gimage);
        Integer result = goodsService.saveGoods(goods);
        try {
            uploadFile.uploadFile(file.getBytes(), filePath, fileName);
            uploadFile.uploadFile(file.getBytes(), server_filePath, fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (result == 1) {
            return new RestResult<>(200, goods, "添加成功");
        } else {
            return new RestResult<>(500, null, "添加失败");
        }
    }

    @PostMapping("/update")
    public RestResult<Goods> updateGoods(@ModelAttribute("goods") Goods goods,
                                         @RequestParam(value = "onlinefilename", required = false) MultipartFile file,
                                         @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/goods/update")) {
            return new RestResult<>(403, null, "权限不够");
        }
        if (file != null) {
            String fileName = file.getOriginalFilename();
            String gimage = "";
            if (!fileName.equals("")) {
                gimage = "/image/goods/" + fileName;
            }
            goods.setGimage(gimage);
            try {
                uploadFile.uploadFile(file.getBytes(), filePath, fileName);
                uploadFile.uploadFile(file.getBytes(), server_filePath, fileName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        Integer result = goodsService.updateGoods(goods);
        if (result == 1) {
            return new RestResult<>(200, goods, "更新成功");
        } else {
            return new RestResult<>(500, null, "更新失败");
        }
    }

    @PostMapping("/delete")
    public RestResult<Integer> deleteGoods(@RequestParam("gid") Integer gid,
                                           @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/goods/delete")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = goodsService.deleteGoods(gid);
        if (result == 1) {
            return new RestResult<>(200, null, "删除成功");
        } else {
            return new RestResult<>(500, null, "删除失败");
        }
    }
}


