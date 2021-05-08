package com.cogu.shop.exception;

import com.cogu.shop.controller.entity.RestResult;

/**
 * @Author: Cogu
 * @Date: 2018/7/11 11:42
 * @Description:
 */
public class ServiceException extends RuntimeException {
    /**
     * 注意要继承自RuntimeException，底层RuntimeException继承了Exception, * spring框架只对抛出的异常是RuntimeException才会进行事务回滚， * 如果是抛出的是Exception，是不会进行事物回滚的
     */
    private Integer code;

    public ServiceException(RestResult resultEnum) {
        //父类的构造方法本身会传message进去
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

