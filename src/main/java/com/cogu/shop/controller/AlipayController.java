package com.cogu.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import net.guerlab.sdk.alipay.controller.AlipayAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Cogu
 * @Date: 2018/7/11 0:00
 * @Description: 暂时不实现了
 */
@RequestMapping("/pay/alipay")
public class AlipayController extends AlipayAbstractController {

    @Autowired
    private AlipayClient client;//支付宝请求sdk客户端

    @GetMapping("/app/{orderId}")
    public String app(
            @PathVariable Long orderId,
            HttpServletResponse httpResponse) throws Exception {

        JSONObject data = new JSONObject();
        data.put("out_trade_no", orderId); //商户订单号
        data.put("product_code", "FAST_INSTANT_TRADE_PAY"); //产品码, APP支付 QUICK_MSECURITY_PAY, PC支付 FAST_INSTANT_TRADE_PAY, 移动H5支付 QUICK_WAP_PAY
        data.put("total_amount", "0.01"); //订单金额
        data.put("subject", "测试订单"); //订单标题

        //APP支付
        //AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //PC支付
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //移动H5支付
        //AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setNotifyUrl("http://pay/alipay/notify/1"); //异步通知地址
        request.setBizContent(data.toJSONString()); //业务参数
        return client.sdkExecute(request).getBody();
    }

    @PostMapping("/notify/{orderId}")
    public String notify(
            @PathVariable Long orderId,
            HttpServletRequest request) {
        if (!notify0(request.getParameterMap())) {
            //这里处理验签失败
            System.out.println("失败！");
        }
        request.getParameter("trade_no");//获取请求参数中的商户订单号

        return "success";
    }
}