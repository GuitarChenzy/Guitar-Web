package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Cogu on 2018/6/14.
 */
@Data
@NoArgsConstructor
public class OrderInfo {

    private Integer id;
    private Long oid;
    private Integer gid;
    private Integer ofcount;
    private Double ofprice;
    private Goods goods;

}
