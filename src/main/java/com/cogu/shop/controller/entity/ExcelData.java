package com.cogu.shop.controller.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/11 15:50
 * @Description:
 */
@lombok.Data
@NoArgsConstructor
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4444017239100620999L;
    // 表头
    private List<String> titles;
    // 数据
    private List<List<Object>> rows;
    // 页签名称
    private String name;

}
