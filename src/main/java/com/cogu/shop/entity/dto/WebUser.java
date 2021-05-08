package com.cogu.shop.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 15:35
 * @Description:
 */
@Data
@NoArgsConstructor
public class WebUser {

    private String realname;
    private String sex;
    private String email;
    private String adress;

}
