package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Cogu on 2018/6/12.
 */
@Data
@NoArgsConstructor
public class Category {

    private Integer cid;
    private String cname;
    private Integer parentid;
    private List<Category> listc;
    private List<Goods> listg;

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", parentid=" + parentid +
                '}';
    }

    public void displayCategory() {
        System.out.println(listc);
    }

    public void displayGoods() {
        System.out.println(listg);
    }

}
