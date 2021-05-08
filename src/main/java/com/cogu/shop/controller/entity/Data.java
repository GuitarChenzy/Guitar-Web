package com.cogu.shop.controller.entity;

import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/7/6 18:29
 * @Description:
 */
@lombok.Data
@NoArgsConstructor
public class Data {

    private String name;
    private Object value;

    public void setValue(Object obj) {
        if (obj == null) {
            this.value = 0;
        } else {
            this.value = obj;
        }
    }

}
