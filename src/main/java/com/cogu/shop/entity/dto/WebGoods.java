package com.cogu.shop.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/7/2 10:34
 * @Description:
 */
@Data
@NoArgsConstructor
public class WebGoods {

    private Integer gid;
    private String gname;
    private Double gprice;
    private String gtypename;
    private Integer gcount;
    private String gdesc;
    private String gimage;
    private String gstate;
    private String gdatetime;

}
