package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 9:35
 * @Description:
 */
@Data
@NoArgsConstructor
public class NumCoin {

    private Integer nid;
    private Integer gid;
    private Integer grecoin;
    private Integer getcoin;
    private Integer nmax;
    private String publictime;
    private String updatetime;

}
