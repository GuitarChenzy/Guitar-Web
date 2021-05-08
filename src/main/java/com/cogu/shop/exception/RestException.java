package com.cogu.shop.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Cogu
 * @Date: 2018/7/11 11:59
 * @Description:
 */
@Data
@NoArgsConstructor
public class RestException {

    private String code;
    private String data;
    private String message;

    public RestException(String code, String data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
