package com.cogu.shop.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Cogu on 2018/6/9.
 */

@Data
@NoArgsConstructor
public class User {

    private Integer uid;
    private String username;
    private String realname;
    private String password;
    private String sex;
    private String email;
    private Integer ucoin;
    private String adress;
    private String registertime;
    private String logintime;


}
