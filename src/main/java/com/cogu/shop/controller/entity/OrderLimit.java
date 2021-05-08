package com.cogu.shop.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/7/4 14:50
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLimit {

    private String name;
    private String value;
    private Integer state;
    private String startTime;
    private String endTime;

}
