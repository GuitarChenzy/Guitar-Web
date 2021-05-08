package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Cogu on 2018/6/20.
 */
@Data
@NoArgsConstructor
public class Admin {

    private Integer aid;
    private String username;
    private String rolename;
    private String password;
    private String regtime;
    private String logtime;
    private List<Role> roleList; //一个管理员可能多个角色

}
