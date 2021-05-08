package com.cogu.shop.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by cogu on 2018/6/8.
 */
@Data
@AllArgsConstructor
public class RestResult<T> {

    private int code;
    private T data;
    private String message;

}
