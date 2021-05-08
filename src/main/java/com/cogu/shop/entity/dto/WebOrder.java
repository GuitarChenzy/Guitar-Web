package com.cogu.shop.entity.dto;

import com.cogu.shop.entity.vo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/7/3 23:50
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebOrder {

    private Order order;
    private String realname;

}
