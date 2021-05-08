package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Cogu on 2018/6/14.
 */
@Data
@NoArgsConstructor
public class PurChase {

    private Integer pid;
    private Integer gid;
    private Integer uid;
    private String pdatetime;
    private Integer pcount;
    private Integer pstate;
    private Goods goods;

    @Override
    public String toString() {
        return "PurChase{" +
                "pid=" + pid +
                ", gid=" + gid +
                ", uid=" + uid +
                ", pdatetime='" + pdatetime + '\'' +
                ", pcount=" + pcount +
                ", pstate=" + pstate +
                '}';
    }

    public void displayGoodsList() {
        System.out.println(goods);
    }

}
