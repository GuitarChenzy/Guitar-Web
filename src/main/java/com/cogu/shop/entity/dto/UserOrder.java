package com.cogu.shop.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Cogu on 2018/6/17.
 */
@Data
@NoArgsConstructor
public class UserOrder {

    private Double oprice;
    private String oadress;
    private String paytype;

}
