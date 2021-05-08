package com.cogu.shop.controller.admin;

import com.cogu.shop.controller.entity.Data;
import com.cogu.shop.controller.entity.GoodsData;
import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.dao.MessageMapper;
import com.cogu.shop.entity.vo.Category;
import com.cogu.shop.entity.vo.Goods;
import com.cogu.shop.service.CategoryService;
import com.cogu.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 9:32
 * @Description:
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping("/getdata")
    public RestResult<List<Object>> getData() {
        List<Object> objectList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        List<String> stringList1 = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        List<Integer> integerList1 = new ArrayList<>();
        List<Data> dataList = new ArrayList<>();
        List<Data> dataList1 = new ArrayList<>();
        List<Data> dataList2 = new ArrayList<>();
        List<Category> categoryList = categoryService.findAllParentCategory();
        for (Category c : categoryList) {
            List<Category> childcategory = categoryService.findAllChildCategoryByParentid(c.getCid());
            c.setListc(childcategory);
            for (Category category : childcategory) {
                List<Goods> goodsList = goodsService.findGoodsByGtypeid(category.getCid());
                category.setListg(goodsList);
            }
        }
        for (Category category : categoryList) { //父类别
            Data data = new Data();
            data.setName(category.getCname());
            Integer sum = 0;
            for (Category category1 : category.getListc()) { //子类别
                stringList.add(category1.getCname());
                Integer count = messageMapper.findOrderInfoSumByGtypeid(category1.getCid());
                if (count == null) {
                    count = 0;
                }
                Data data1 = new Data();
                data1.setName(category1.getCname());
                data1.setValue(count);
                sum += count;
                dataList1.add(data1);
                Data data2 = new Data();
                data2.setName(category1.getCname());
                data2.setValue(category1.getListg().size());
                dataList2.add(data2);
            }
            data.setValue(sum);
            dataList.add(data);
        }
        List<GoodsData> dataList3 = messageMapper.findGidAndOfcount();
        System.out.println(dataList3);
        for (GoodsData goodsData : dataList3) {
            stringList1.add(goodsData.getGname());
            integerList.add(goodsData.getGnum());
            integerList1.add(goodsData.getGcount());
        }
        objectList.add(stringList);//0 子类别名称
        objectList.add(dataList);//1 父类别 ：销售总数
        objectList.add(dataList1);//2 子类别 ： 销售个数
        objectList.add(dataList2);//3 子类别 ： 商品个数
        objectList.add(stringList1);//4 热销商品名
        objectList.add(integerList);//5 热销商品数目
        objectList.add(integerList1);//6 热销商品库存
        return new RestResult<>(200, objectList, "成功");
    }

    @GetMapping("/getAllGoods")
    public RestResult<List<Goods>> getAllGoods(){
        List<Goods> goodsList = goodsService.findAllGoods();
        return new RestResult<>(200,goodsList,"成功");
    }

    @RequestMapping("/getGid")
    public RestResult<List<Object>> getDataByGid(@RequestParam("gid") Integer gid) {
        List<Object> objectList = new ArrayList<>();
        List<Data> dataList = messageMapper.findOfcountAndOdatetimeByGid(gid);
        objectList.add(dataList);
        return new RestResult<>(200,objectList,"成功");
    }

}
