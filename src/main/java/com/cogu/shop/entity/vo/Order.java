package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
@Data
@NoArgsConstructor
public class Order {

    private Long oid;
    private Integer uid;
    private String oadress;
    private Double oprice;
    private String paytype;
    private Integer state;
    private String odatetime;
    private String updatetime;
    private List<OrderInfo> orderInfoList;

}
