package com.cogu.shop.controller.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 22:20
 * @Description:
 */
@Data
@NoArgsConstructor
public class Tree {

    private Integer id;
    private String text;
    private Tree children;

}
