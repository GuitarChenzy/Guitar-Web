package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Cogu on 2018/6/12.
 */
@Data
@NoArgsConstructor
public class Goods {

    private Integer gid;
    private Integer gtypeid;
    private String gname;
    private Double gprice;
    private Integer gcount;
    private String gdesc;
    private String gimage;
    private Integer gstate;
    private String gdatetime;
    private Category category;

    public void dipplayCategory() {
        System.out.println(category);
    }

}
