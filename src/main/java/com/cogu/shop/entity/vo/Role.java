package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 10:00
 * @Description:
 */
@Data
@NoArgsConstructor
public class Role {

    private Integer rid;
    private String rolename;
    private String rdesc;
    private String publictime;
    private String updatetime;
    private Integer rchild;
    private List<Turl> turlList; //一个角色可能多个资源

}
