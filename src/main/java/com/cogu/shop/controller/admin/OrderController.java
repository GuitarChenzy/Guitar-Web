package com.cogu.shop.controller.admin;

import com.cogu.shop.controller.entity.ExcelData;
import com.cogu.shop.controller.entity.OrderLimit;
import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.entity.dto.WebOrder;
import com.cogu.shop.entity.vo.Order;
import com.cogu.shop.entity.vo.User;
import com.cogu.shop.service.OrderService;
import com.cogu.shop.service.UserService;
import com.cogu.shop.tools.ExportExcelUtils;
import com.cogu.shop.tools.PageBean;
import com.cogu.shop.tools.UrlCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * Created by Cogu on 2018/6/15.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ModelMap getOrder(@ModelAttribute("orderLimit") OrderLimit orderLimit,
                             @SessionAttribute("url") List<String> url) {
        ModelMap modelMap = new ModelMap();
        if (!UrlCompare.findUrl(url, "/order/get")) {
            modelMap.addAttribute("code", 403);
            modelMap.addAttribute("message", "权限不够");
            return modelMap;
        }
        List<WebOrder> webOrderList = new ArrayList<>();
        if (orderLimit.getName().equals("oid")) {
            Long order_id = Long.parseLong(orderLimit.getValue());
            Order order = orderService.findOrderByOid(order_id);
            if (order != null) {
                User user = userService.findUserByUid(order.getUid());
                WebOrder webOrder = new WebOrder(order, user.getRealname());
                webOrderList.add(webOrder);
            }
        } else {
            List<Order> orderList = orderService.findOrderWithMultLimit(orderLimit);
            if (orderList.size() > 0) {
                for (Order order : orderList) {
                    User user = userService.findUserByUid(order.getUid());
                    WebOrder webOrder = new WebOrder(order, user.getRealname());
                    webOrderList.add(webOrder);
                }
            }
        }
        if (webOrderList.size() > 0) {
            modelMap.addAttribute("code", 200);
            modelMap.addAttribute("rows", webOrderList);
            modelMap.addAttribute("total", webOrderList.size());
        } else {
            modelMap.addAttribute("code", 500);
            modelMap.addAttribute("message", "没有该条件的订单");
        }
        return modelMap;
    }

    @PostMapping("/getAll")
    public ModelMap getAllOrder(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "rows", required = false) Integer size) {
        ModelMap modelMap = new ModelMap();
        List<WebOrder> webOrderList = new ArrayList<>();
        PageBean pageBean = new PageBean(page, size);
        List<Order> orderList = orderService.findAllOrderWithLimit(pageBean.getPage(), pageBean.getPageSize());
        for (Order order : orderList) {
            User user = userService.findUserByUid(order.getUid());
            WebOrder webOrder = new WebOrder();
            webOrder.setOrder(order);
            webOrder.setRealname(user.getRealname());
            webOrderList.add(webOrder);
        }
        Integer total = orderService.findOrderCount();
        modelMap.addAttribute("rows", webOrderList);
        modelMap.addAttribute("total", total);
        return modelMap;
    }

    @PostMapping("/delete")
    public RestResult<Integer> deleteOrder(@RequestParam("oid") Long oid,
                                           @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/order/delete")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = orderService.deleteOrder(oid);
        if (result == 1) {
            return new RestResult<>(200, null, "删除成功");
        } else {
            return new RestResult<>(500, null, "删除失败");
        }
    }

    @PostMapping(value = "/excel")
    public void excel(@ModelAttribute("orderLimit") OrderLimit orderLimit,
                      HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("订单报表");
        List<String> titles = new ArrayList();
        titles.add("订单号");
        titles.add("买家账号");
        titles.add("订单总价钱");
        titles.add("订单地址");
        titles.add("支付种类");
        titles.add("下订单时间");
        titles.add("订单结算时间");
        titles.add("订单状态");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        List<Order> orderList = orderService.findOrderWithMultLimit(orderLimit);
        //List<Order> orderList = orderService.findAllOrderWithLimit(0, 100);
        for (Order order : orderList) {
            List<Object> row = new ArrayList();
            row.add(order.getOid());
            row.add(userService.findUserByUid(order.getUid()).getRealname());
            row.add(order.getOprice());
            row.add(order.getOadress());
            row.add(order.getPaytype());
            row.add(order.getOdatetime());
            row.add(order.getUpdatetime());
            Integer state = order.getState();
            if (state == 0) {
                row.add("待支付");
            } else if (state == 1) {
                row.add("已支付");
            } else if (state == 2) {
                row.add("已取消");
            } else {
                row.add("已隐藏");
            }
            rows.add(row);
        }
        data.setRows(rows);
        String fileName = "order" + now() + ".xlsx";
        ExportExcelUtils.exportExcel(response, fileName, data);
    }

}
